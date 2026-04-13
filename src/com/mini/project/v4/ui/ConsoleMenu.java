package com.mini.project.v4.ui;

import com.mini.project.v4.dto.ServiceResult;
import com.mini.project.v4.model.Student;
import com.mini.project.v4.service.StudentManager;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentManager studentManager;
    private final ConsoleInputHelper inputHelper;

    public ConsoleMenu(StudentManager studentManager) {
        this.studentManager = studentManager;
        this.inputHelper = new ConsoleInputHelper();
    }

    public void startMenu() {
        System.out.println("Sistem başlatılıyor, veriler dosyadan yükleniyor...");
        studentManager.startApplication();

        boolean running = true;

        while (running) {
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }

            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║       ÖĞRENCİ YÖNETİM SİSTEMİ v4       ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║  [1] Öğrenci Ekle                      ║");
            System.out.println("║  [2] Öğrenci Sil                       ║");
            System.out.println("║  [3] Öğrenci Güncelle                  ║");
            System.out.println("║  [4] Öğrencileri Listele               ║");
            System.out.println("║  [5] Not Ortalamasını Hesapla          ║");
            System.out.println("║                                        ║");
            System.out.println("║  [0] Sistemden Çıkış                   ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("➤ Lütfen bir işlem seçiniz: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        String id = inputHelper.readString("Eklenecek Öğrencinin Okul Numarası: ");
                        String name = inputHelper.readName("Öğrencinin Adı: ");
                        String surname = inputHelper.readName("Öğrencinin Soyadı: ");
                        double grade = inputHelper.readGrade("Öğrencinin Notu: ");

                        ServiceResult<Void> addResult = studentManager.addStudent(id, name, surname, grade);
                        if (addResult.isSuccess()) {
                            System.out.println("✅ " + addResult.getMessage());
                        } else {
                            System.out.println("❌ " + addResult.getMessage());
                        }

                        inputHelper.pressEnterToContinue();
                        break;

                    case 2:
                        if (!displayStudents()) {
                            inputHelper.pressEnterToContinue();
                            break;
                        }

                        String removeId = inputHelper.readString("Silmek İstediğiniz Öğrencinin ID'si (İptal için 0 giriniz): ");

                        if (removeId.equals("0")) {
                            System.out.println("İşlem iptal edildi. Ana menüye dönülüyor...");
                            inputHelper.pressEnterToContinue();
                            break;
                        }

                        ServiceResult<Void> removeResult = studentManager.removeStudent(removeId);
                        if (removeResult.isSuccess()) {
                            System.out.println("✅ " + removeResult.getMessage());
                        } else {
                            System.out.println("❌ " + removeResult.getMessage());
                        }

                        inputHelper.pressEnterToContinue();
                        break;

                    case 3:
                        if (!displayStudents()) {
                            inputHelper.pressEnterToContinue();
                            break;
                        }

                        String updateId = inputHelper.readString("Güncellenecek Öğrencinin ID'si (İptal için 0 giriniz): ");

                        if (updateId.equals("0")) {
                            System.out.println("İşlem iptal edildi. Ana menüye dönülüyor...");
                            inputHelper.pressEnterToContinue();
                            break;
                        }

                        ServiceResult<Student> getResult = studentManager.getStudentById(updateId);

                        if (!getResult.isSuccess()) {
                            System.out.println("❌ " + getResult.getMessage());
                            inputHelper.pressEnterToContinue();
                            break;
                        }

                        Student currentStudent = getResult.getData();
                        System.out.println("\nBulunan Öğrenci: " + currentStudent.getName() + " " + currentStudent.getSurname() + " (Mevcut Not: " + currentStudent.getGrade() + ")");


                        String newName = inputHelper.readName("Yeni Ad: ");
                        String newSurname = inputHelper.readName("Yeni Soyad: ");
                        double newGrade = inputHelper.readGrade("Yeni Not: ");


                        ServiceResult<Student> updateResult = studentManager.updateStudent(updateId, newName, newSurname, newGrade);
                        if (updateResult.isSuccess()) {
                            System.out.println("✅ " + updateResult.getMessage());
                        } else {
                            System.out.println("❌ " + updateResult.getMessage());
                        }

                        inputHelper.pressEnterToContinue();
                        break;

                    case 4:
                        displayStudents();
                        inputHelper.pressEnterToContinue();
                        break;

                    case 5:
                        ServiceResult<Double> avgResult = studentManager.calculateAverage();
                        if (avgResult.isSuccess()) {
                            System.out.println("Ortalama: " + avgResult.getData());
                        } else {
                            System.out.println("⚠️ Uyarı: " + avgResult.getMessage());
                        }

                        inputHelper.pressEnterToContinue();
                        break;

                    case 0:
                        System.out.println("Sistemden Çıkılıyor, veriler dosyaya kaydediliyor...");
                        studentManager.stopApplication();
                        running = false;
                        break;

                    default:
                        System.out.println("Hatalı Giriş! Lütfen tekrar deneyin.");
                        inputHelper.pressEnterToContinue();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Beklenmeyen bir hata oluştu: " + e.getMessage());
                scanner.nextLine();
                inputHelper.pressEnterToContinue();
            }
        }
    }

    private boolean displayStudents() {
        List<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("\nListede henüz öğrenci yok.");
            return false;
        } else {
            System.out.println("\n--- Güncel Öğrenci Listesi ---");
            for (Student student : students) {
                System.out.println(student.toString());
            }
            return true;
        }
    }
}



