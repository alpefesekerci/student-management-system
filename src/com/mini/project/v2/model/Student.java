package com.mini.project.v2.model;

public class Student {
        private int id;
        private String name;
        private String surname;
        private double grade;

        public Student(int id, String name, String surname, double grade){
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.grade = grade;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getSurname() {
            return surname;
        }
        public void setSurname(String surname){
            this.surname = surname;
        }
        public double getGrade() {
            return grade;
        }
        public void setGrade(double grade){
            this.grade = grade;
        }
        @Override
        public String toString(){
            return "ID:" + id + " | Ad:" + name + " | Soyad: " + surname + " | Not: " + grade;
        }

    }

