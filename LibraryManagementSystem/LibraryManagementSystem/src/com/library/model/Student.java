package com.library.model;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private String phone;

    public Student(int studentId, String name, String email, String phone) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    public void displayStudent() {
        System.out.println("ID: " + studentId + " | Name: " + name + " | Email: " + email + " | Phone: " + phone);
    }
}
