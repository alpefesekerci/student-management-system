package com.mini.project.v4.service;

import com.mini.project.v4.dto.ServiceResult;
import com.mini.project.v4.model.Student;
import com.mini.project.v4.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

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

    public ServiceResult<Student> getStudentById(String id) {
        Optional<Student> studentOpt = repository.findById(id);

        if (studentOpt.isEmpty()) {
            return new ServiceResult<>(false, "Hata: " + id + " ID'li öğrenci bulunamadı.");
        }

        return new ServiceResult<>(true, "Öğrenci bulundu.", studentOpt.get());
    }

    public ServiceResult<Void> addStudent(String id, String name, String surname, double grade) {
        try {
            if (repository.existsById(id)) {
                return new ServiceResult<>(false, "Bu ID ile zaten bir öğrenci kayıtlı.");
            }

            Student student = new Student(id, name, surname, grade);
            repository.save(student);
            return new ServiceResult<>(true, "Öğrenci başarıyla eklendi.");
        } catch (IllegalArgumentException e) {
            return new ServiceResult<>(false, "Hata: " + e.getMessage());
        }
    }

    public ServiceResult<Void> removeStudent(String id) {
        if (!repository.existsById(id)) {
            return new ServiceResult<>(false, "Hata: Silinmek istenen " + id + " ID'li öğrenci bulunamadı.");
        }

        repository.deleteById(id);
        return new ServiceResult<>(true, "Öğrenci başarıyla silindi!");
    }

    public ServiceResult<Student> updateStudent(String id, String newName, String newSurname, double newGrade) {
        Optional<Student> studentOpt = repository.findById(id);

        if (studentOpt.isEmpty()) {
            return new ServiceResult<>(false, "Hata: Güncellenecek " + id + " ID'li öğrenci bulunamadı.");
        }

        Student student = studentOpt.get();

        try {
            student.setName(newName);
            student.setSurname(newSurname);
            student.setGrade(newGrade);

            return new ServiceResult<>(true, "Başarılı: " + student.getName() + " isimli öğrencinin bilgileri güncellendi.", student);
        } catch (IllegalArgumentException e) {
            return new ServiceResult<>(false, "Hata: " + e.getMessage());
        }
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