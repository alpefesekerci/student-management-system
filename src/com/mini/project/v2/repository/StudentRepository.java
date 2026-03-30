package com.mini.project.v2.repository;

import com.mini.project.v2.model.Student;
import com.mini.project.v2.exception.StudentAlreadyExistsException;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class StudentRepository {
    private final List<Student> studentList = new ArrayList<>();

    public boolean existsById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void save(Student student) {
        if (existsById(student.getId())) {
            throw new StudentAlreadyExistsException("Veritabanı Hatası: " + student.getId() + " ID'li öğrenci zaten mevcut!");
        }
        studentList.add(student);
    }

    public List<Student> findAll() {
        return studentList;
    }

    public void deleteById(int id) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

}
