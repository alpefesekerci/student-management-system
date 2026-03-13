package com.mini.project.v2.service;

import com.mini.project.v2.model.Student;
import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(Student student){
    studentList.add(student);
        System.out.println("Sisteme eklendi: " + student.getName());
    }

    public void removeStudent(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                studentList.remove(student);
                System.out.println("Sistemden silindi: " + student.getName());
                return;
            }
        }
        System.out.println("Hata: " + id + "ID'li öğrenci bulunamadı!");
    }

    public void listStudents() {
        System.out.println("\n--- Güncel Öğrenci Listesi ---");
        if (studentList.isEmpty()) {
            System.out.println("Listede henüz öğrenci yok.");
            return;
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
        public void updateStudent(int id, String newName, String newSurname, double newGrade) {
            for (Student student : studentList) {
                if (student.getId() == id) {
                    student.setName(newName);
                    student.setSurname(newSurname);
                    student.setGrade(newGrade);
                    System.out.println("Öğrenci başarıyla güncellendi: " + student.getName());
                    return;
                }
            }
            System.out.println("Hata: Güncellenecek öğrenci bulunamadı (ID: " + id + ")");
        }

        public void calculateAverage() {
            if (studentList.isEmpty()) {
                System.out.println("Listede öğrenci olmadığı için ortalama hesaplanamaz.");
                return;
            }
            double totalGrade = 0;
            for (Student student : studentList) {
                totalGrade += student.getGrade();
            }
            double averageGrade = totalGrade / studentList.size();
            System.out.println("Ortalama not: " + averageGrade);
        }
    }

