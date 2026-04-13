package com.mini.project.v4.model;

public class Student {
    private final String id;
    private String name;
    private String surname;
    private double grade;

    private static final String NAME_REGEX = "^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$";
    private static final String ID_REGEX = "\\d{11}";

    public Student(String id, String name, String surname, double grade) {
        validateId(id);
        validateName(name, "İsim");
        validateName(surname, "Soyisim");
        validateGrade(grade);

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.grade = grade;
    }

    private void validateId(String id) {
        if (id == null || !id.matches(ID_REGEX)) {
            throw new IllegalArgumentException("Hata: Öğrenci ID tam olarak 11 haneli rakam olmalıdır!");
        }
    }

    private void validateName(String text, String fieldName) {
        if (text == null || text.trim().isEmpty() || !text.matches(NAME_REGEX)) {
            throw new IllegalArgumentException(fieldName + " geçersiz, özel karakter veya rakam içeremez!");
        }
    }

    private void validateGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Not değeri 0 ile 100 arasında olmalıdır!");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name, "İsim");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        validateName(surname, "Soyisim");
        this.surname = surname;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        validateGrade(grade);
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID:" + id + " | Ad:" + name + " | Soyad: " + surname + " | Not: " + grade;
    }
}
