package com.mini.project.v4.ui;

import com.mini.project.v4.dto.ServiceResult;
import com.mini.project.v4.model.Student;
import com.mini.project.v4.service.StudentManager;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentManager studentManager;

    public ConsoleMenu(StudentManager studentManager) {
        this.studentManager = studentManager;
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
                        System.out.println("Eklenecek Öğrencinin Okul Numarası: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Öğrencinin Adı: ");
                        String name = scanner.nextLine();

                        System.out.println("Öğrencinin Soyadı: ");
                        String surname = scanner.nextLine();

                        System.out.println("Öğrencinin Notu: ");
                        double grade = scanner.nextDouble();
                        scanner.nextLine();

                        ServiceResult<Void> addResult = studentManager.addStudent(id, name, surname, grade);
                        if (addResult.isSuccess()) {
                            System.out.println("✅ " + addResult.getMessage());
                        } else {
                            System.out.println("❌ " + addResult.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 2:
                       displayStudents();
                        System.out.println("Silmek İstediğiniz Öğrencinin ID'si (İptal için 0 giriniz): ");
                        int removeId = scanner.nextInt();
                        scanner.nextLine();

                        if (removeId == 0) {
                            System.out.println("İşlem iptal edildi. Ana menüye dönülüyor...");
                            pressEnterToContinue();
                            break;
                        }

                        ServiceResult<Void> removeResult = studentManager.removeStudent(removeId);
                        if (removeResult.isSuccess()) {
                            System.out.println("✅ " + removeResult.getMessage());
                        } else {
                            System.out.println("❌ " + removeResult.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 3:
                        displayStudents();
                        System.out.println("Güncellenecek Öğrencinin ID'si (İptal için 0 giriniz): ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        if (updateId == 0) {
                            System.out.println("İşlem iptal edildi. Ana menüye dönülüyor...");
                            pressEnterToContinue();
                            break;
                        }

                        System.out.println("Yeni Ad: ");
                        String newName = scanner.nextLine();
                        System.out.println("Yeni Soyad: ");
                        String newSurname = scanner.nextLine();
                        System.out.println("Yeni Not: ");
                        double newGrade = scanner.nextDouble();
                        scanner.nextLine();

                        ServiceResult<Student> updateResult = studentManager.updateStudent(updateId, newName, newSurname, newGrade);
                        if (updateResult.isSuccess()) {
                            System.out.println("✅ " + updateResult.getMessage());
                        } else {
                            System.out.println("❌ " + updateResult.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 4:
                        displayStudents();
                        pressEnterToContinue();
                        break;

                    case 5:
                        ServiceResult<Double> avgResult = studentManager.calculateAverage();
                        if (avgResult.isSuccess()) {
                            System.out.println("Ortalama: " + avgResult.getData());
                        } else {
                            System.out.println("⚠️ Uyarı: " + avgResult.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 0:
                        System.out.println("Sistemden Çıkılıyor, veriler dosyaya kaydediliyor...");

                        studentManager.stopApplication();

                        running = false;
                        break;

                    default:
                        System.out.println("Hatalı Giriş! Lütfen tekrar deneyin.");

                        pressEnterToContinue();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("KRİTİK HATA: Lütfen harf değil, sadece geçerli bir rakam giriniz!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("Beklenmeyen bir hata oluştu: " + e.getMessage());
                pressEnterToContinue();
            }
        }
    }

    private void displayStudents() {
        List<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("\nListede henüz öğrenci yok.");
        } else {
            System.out.println("\n--- Güncel Öğrenci Listesi ---");
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }
    }

    private void pressEnterToContinue() {
        System.out.println("\n➤ Devam etmek için Enter'a basın...");
        scanner.nextLine();
    }
}



