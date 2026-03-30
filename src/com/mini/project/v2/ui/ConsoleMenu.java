package com.mini.project.v2.ui;

import com.mini.project.v2.model.Student;
import com.mini.project.v2.service.StudentManager;
import com.mini.project.v2.exception.InvalidGradeException;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentManager studentManager = new StudentManager();

    public void startMenu() {
        boolean running = true;

        while (running) {
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }

            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║       ÖĞRENCİ YÖNETİM SİSTEMİ v2       ║");
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

                        try {
                            studentManager.addStudent(id, name, surname, grade);
                            System.out.println("Öğrenci başarıyla eklendi!");
                        } catch (IllegalArgumentException | InvalidGradeException e) {
                            System.out.println("❌ " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("❌ Beklenmeyen bir hata oluştu: " + e.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 2:
                        if (studentManager.isListEmpty()) {
                            System.out.println("Uyarı: Sistemde silinecek öğrenci bulunmuyor!");
                            pressEnterToContinue();
                            break;
                        }

                        displayStudents();
                        System.out.println("Silmek İstediğiniz Öğrencinin ID'si (İptal için 0 giriniz): ");
                        int removeId = scanner.nextInt();
                        scanner.nextLine();

                        if (removeId == 0) {
                            System.out.println("İşlem iptal edildi. Ana menüye dönülüyor...");
                            pressEnterToContinue();
                            break;
                        }

                        try {
                            studentManager.removeStudent(removeId);
                            System.out.println("Öğrenci başarıyla silindi!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ " + e.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 3:
                        if (studentManager.isListEmpty()) {
                            System.out.println("Uyarı: Sistemde güncellenecek öğrenci bulunmuyor!");
                            pressEnterToContinue();
                            break;
                        }

                        displayStudents();
                        System.out.println("Güncellenecek Öğrencinin ID'si (İptal için 0 giriniz): ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        if (updateId == 0) {
                            System.out.println("İşlem iptal edildi. Ana menüye dönülüyor...");
                            pressEnterToContinue();
                            break;
                        }

                        if (!studentManager.doesStudentExist(updateId)) {
                            System.out.println("Hata: " + updateId + " ID'li öğrenci bulunamadı! İşlem iptal edildi.");
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

                        try {
                            boolean isUpdated = studentManager.updateStudent(updateId, newName, newSurname, newGrade);
                            if (isUpdated) {
                                System.out.println("Başarılı: Öğrenci bilgileri güncellendi.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Doğrulama Hatası: " + e.getMessage());
                        }

                        pressEnterToContinue();
                        break;

                    case 4:
                        displayStudents();
                        pressEnterToContinue();
                        break;

                    case 5:
                        if (studentManager.isListEmpty()) {
                            System.out.println("Uyarı: Listede öğrenci olmadığı için ortalama hesaplanamaz.");
                        } else {
                            double avg = studentManager.calculateAverage();
                            System.out.println("Sınıfın Not Ortalaması: " + avg);
                        }
                        pressEnterToContinue();
                        break;

                    case 0:
                        System.out.println("Sistemden Çıkılıyor...");
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
            } catch (IndexOutOfBoundsException e) {
                System.out.println("HATA: Listede olmayan bir pozisyona erişmeye çalıştınız!");
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



