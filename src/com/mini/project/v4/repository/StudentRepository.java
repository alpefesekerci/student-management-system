package com.mini.project.v4.repository;

import com.mini.project.v4.model.Student;
import com.mini.project.v4.util.FileUtil;

import java.util.*;

public class StudentRepository {
    private static final String FILE_NAME = "students.txt";

    private final Map<String, Student> studentMap = new HashMap<>();

    public boolean existsById(String id) {
        return studentMap.containsKey(id);
    }

    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    public List<Student> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(studentMap.values()));
    }

    public void deleteById(String id) {
        studentMap.remove(id);
    }

    public void loadData() {
        List<Student> loadedStudents = FileUtil.readStudentsFromFile(FILE_NAME);
        loadedStudents.forEach(student -> studentMap.put(student.getId(), student));
    }

    public void saveData() {
        FileUtil.writeStudentsToFile(new ArrayList<>(studentMap.values()), FILE_NAME);    }
}



