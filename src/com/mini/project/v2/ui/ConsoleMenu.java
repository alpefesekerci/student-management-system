package com.mini.project.v2.ui;

import com.mini.project.v2.model.Student;
import com.mini.project.v2.service.StudentManager;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleMenu {
    private Scanner scanner = new Scanner(System.in);
    private StudentManager studentManager = new StudentManager();

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

                        studentManager.addStudent(new Student(id, name, surname, grade));
                        pressEnterToContinue();
                        break;

                    case 2:
                        studentManager.listStudents();

                        System.out.println("Silmek İstediğiniz Öğrencinin ID'si: ");
                        int removeId = scanner.nextInt();
                        scanner.nextLine();
                        studentManager.removeStudent(removeId);
                        pressEnterToContinue();
                        break;

                    case 3:
                        studentManager.listStudents();

                        System.out.println("Güncellenecek Öğrencinin ID'si: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Yeni Ad: ");
                        String newName = scanner.nextLine();
                        System.out.println("Yeni Soyad: ");
                        String newSurname = scanner.nextLine();
                        System.out.println("Yeni Not: ");
                        double newGrade = scanner.nextDouble();
                        scanner.nextLine();
                        studentManager.updateStudent(updateId, newName, newSurname, newGrade);
                        pressEnterToContinue();
                        break;

                    case 4:
                        studentManager.listStudents();
                        pressEnterToContinue();
                        break;

                    case 5:
                        studentManager.calculateAverage();
                        pressEnterToContinue();
                        break;

                    case 0:
                        System.out.println("Sistemden Çıkılıyor...");
                        running = false;
                        break;

                    default:
                        System.out.println("Hatalı Giriş! Lütfen tekrar deneyin.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("KRİTİK HATA: Lütfen harf değil, sadece geçerli bir rakam giriniz!");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("HATA: Listede olmayan bir pozisyona erişmeye çalıştınız!");
            } catch (Exception e) {
                System.out.println("Beklenmeyen bir hata oluştu: " + e.getMessage());
            }
        }
    }
    private void pressEnterToContinue() {
        System.out.println("\n➤ Devam etmek için Enter'a basın...");
        scanner.nextLine();
    }
}



