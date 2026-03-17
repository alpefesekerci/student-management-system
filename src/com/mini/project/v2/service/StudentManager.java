package com.mini.project.v2.service;

import com.mini.project.v2.model.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentManager {
    private final List<Student> studentList = new ArrayList<>();

    public boolean addStudent(Student student) {
        for (Student existingStudent : studentList) {
            if (existingStudent.getId() == student.getId()) {
                return false;
            }
        }
        studentList.add(student);
        return true;
    }

    public boolean removeStudent(int id) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return studentList;
    }

    public boolean updateStudent(int id, String newName, String newSurname, double newGrade) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                student.setName(newName);
                student.setSurname(newSurname);
                student.setGrade(newGrade);
                return true;
            }
        }
        return false;
    }

    public double calculateAverage() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double totalGrade = 0;
        for (Student student : studentList) {
            totalGrade += student.getGrade();
        }
        return totalGrade / studentList.size();
    }

    public boolean isListEmpty() {
        return studentList.isEmpty();
    }

    public boolean doesStudentExist(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }
}

