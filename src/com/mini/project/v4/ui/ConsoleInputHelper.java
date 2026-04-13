package com.mini.project.v4.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputHelper {
    private final Scanner scanner;

    public ConsoleInputHelper() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("❌ Hatalı giriş! Lütfen sadece tam sayı giriniz.");
                scanner.nextLine();
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("❌ Boş giriş yapamazsınız, lütfen bir not giriniz.");
                continue;
            }

            input = input.replace(",", ".");

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Hatalı giriş! Lütfen sadece rakam giriniz (Ondalık için virgül veya nokta kullanabilirsiniz).");
            }
        }
    }

    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (value == null || value.trim().isEmpty()) {
                System.out.println("❌ Boş giriş yapamazsınız, lütfen bir değer giriniz.");
            } else {
                return value.trim();
            }
        }
    }

    public String readName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine();

            if (value == null || value.trim().isEmpty()) {
                System.out.println("❌ Boş giriş yapamazsınız, lütfen bir değer giriniz.");
            } else if (!value.matches("^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$")) {
                System.out.println("❌ Hatalı giriş! Ad/Soyad alanı rakam veya özel karakter içeremez. Lütfen tekrar giriniz.");
            } else {
                return value;
            }
        }
    }

    public double readGrade(String prompt) {
        while (true) {
            double grade = readDouble(prompt);

            if (grade < 0 || grade > 100) {
                System.out.println("❌ Hatalı giriş! Not değeri 0 ile 100 arasında olmalıdır. Lütfen tekrar giriniz.");
            } else {
                return grade;
            }
        }
    }

    public void pressEnterToContinue() {
        System.out.println("\n➤ Devam etmek için Enter'a basın...");
        scanner.nextLine();
    }
}
