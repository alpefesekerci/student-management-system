package com.mini.project.v4.util;

import com.mini.project.v4.model.Student;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileUtil {
    private static final int EXPECTED_CSV_FIELDS = 4;

    public static List<Student> readStudentsFromFile(String FILE_NAME) {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return students;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length == EXPECTED_CSV_FIELDS) {
                    try {
                        String id = data[0].trim();
                        String name = data[1].trim();
                        String surname = data[2].trim();
                        double grade = Double.parseDouble(data[3].trim());
                        students.add(new Student(id, name, surname, grade));
                    } catch (IllegalArgumentException e) {
                        System.err.println("⚠️ Uyarı: " + FILE_NAME + " dosyasındaki " + lineNumber + ". satır atlandı. Sebep: " + e.getMessage());
                    }
                } else {
                    System.err.println("⚠️ Uyarı: " + FILE_NAME + " dosyasındaki " + lineNumber + ". satır atlandı. Sebep: Geçersiz veri formatı (Eksik/Fazla sütun).");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Dosya okunurken hata oluştu: " + e.getMessage());
        }
        return students;
    }

    public static void writeStudentsToFile(List<Student> students, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                bw.write(student.getId() + "," + student.getName() + "," + student.getSurname() + "," + student.getGrade());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Dosyaya yazılırken hata oluştu: " + e.getMessage());
        }
    }
}
