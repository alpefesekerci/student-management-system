package com.mini.project.v2.model;

public class Student {
    private int id;
    private String name;
    private String surname;
    private double grade;

    public Student(int id, String name, String surname, double grade) {
        setId(id);
        setName(name);
        setSurname(surname);
        setGrade(grade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Hata: ID değeri 0'dan büyük pozitif bir sayı olmalıdır! Girilen değer: " + id);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateText(name, "Ad");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        validateText(surname, "Soyad");
        this.surname = surname;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Hata: Not değeri 0 ile 100 arasında olmalıdır! Girilen değer: " + grade);
        }
        this.grade = grade;
    }

    private void validateText(String text, String fieldType) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Hata: " + fieldType + " alanı boş bırakılamaz!");
        }
        if (!text.matches("^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$")) {
            throw new IllegalArgumentException("Hata: " + fieldType + " alanı rakam veya özel karakter içeremez! Girilen değer: " + text);
        }
    }

    @Override
    public String toString() {
        return "ID:" + id + " | Ad:" + name + " | Soyad: " + surname + " | Not: " + grade;
    }

}

