package com.mini.project.v4.service;

import com.mini.project.v4.dto.ServiceResult;
import com.mini.project.v4.model.Student;
import com.mini.project.v4.repository.StudentRepository;
import java.util.List;

public class StudentManager {

    private final StudentRepository repository;

    public StudentManager(StudentRepository repository) {
        this.repository = repository;
    }

    public void startApplication() {
        repository.loadData();
    }

    public void stopApplication() {
        repository.saveData();
    }

    private boolean isInvalidText(String text) {
        return text == null || text.trim().isEmpty() || !text.matches("^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$");
    }

    public ServiceResult<Void> addStudent(int id, String name, String surname, double grade) {
        if (id <= 0 ) return new ServiceResult<>(false, "Hata: ID değeri 0'dan büyük pozitif bir sayı olmalıdır!");
        if (isInvalidText(name)) return new ServiceResult<>(false, "Hata: Ad alanı boş bırakılamaz, rakam veya özel karakter içeremez!");
        if (isInvalidText(surname)) return new ServiceResult<>(false, "Hata: Soyad alanı boş bırakılamaz, rakam veya özel karakter içeremez!");
        if (grade < 0 || grade > 100 ) return new ServiceResult<>(false, "Hata: Not değeri 0 ile 100 arasında olmalıdır!");

        if (repository.existsById(id)) {
            return new ServiceResult<>(false, "Servis Hatası: " + id + " ID'li öğrenci zaten sisteme kayıtlı!");
        }

        Student newStudent = new Student(id, name, surname, grade);
        repository.save(newStudent);
        return new ServiceResult<>(true, "Öğrenci başarıyla eklendi!");
    }

    public ServiceResult<Void> removeStudent(int id) {
        if (!repository.existsById(id)) {
            return new ServiceResult<>(false, "Hata: Silinmek istenen " + id + " ID'li öğrenci bulunamadı.");
        }

        repository.deleteById(id);
        return new ServiceResult<>(true, "Öğrenci başarıyla silindi!");
    }

    public ServiceResult<Student> updateStudent(int id, String newName, String newSurname, double newGrade) {
        Student student = repository.findById(id);

        if (student == null) {
            return new ServiceResult<>(false, "Hata: Güncellenecek " + id + " ID'li öğrenci bulunamadı.");
        }

        if (isInvalidText(newName)) return new ServiceResult<>(false, "Hata: Yeni ad geçersiz, özel karakter veya rakam içeremez!");
        if (isInvalidText(newSurname)) return new ServiceResult<>(false, "Hata: Yeni soyad geçersiz, özel karakter veya rakam içeremez!");
        if (newGrade < 0 || newGrade > 100) return new ServiceResult<>(false, "Hata: Yeni not değeri 0 ile 100 arasında olmalıdır!");

        student.setName(newName);
        student.setSurname(newSurname);
        student.setGrade(newGrade);

        return new ServiceResult<>(true, "Başarılı: " + student.getName() + " isimli öğrencinin bilgileri güncellendi.", student);
    }

    public ServiceResult<Double> calculateAverage() {
        List<Student> students = repository.findAll();

        if (students.isEmpty()) {
            return new ServiceResult<>(false, "Listede öğrenci olmadığı için ortalama hesaplanamaz.");
        }

        double total = 0;
        for (Student student : students) {
            total += student.getGrade();
        }
        double average = total / students.size();

        return new ServiceResult<>(true, "Ortalama hesaplandı", average);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}