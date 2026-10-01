package com.library.service;

import java.util.ArrayList;
import java.util.List;
import com.library.exception.StudentNotFoundException;
import com.library.model.Student;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) { students.add(student); }
    public List<Student> getAllStudents() { return students; }

    public Student findStudentById(int id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getStudentId() == id) return student;
        }
        throw new StudentNotFoundException("Student with ID " + id + " not found.");
    }

    public void updateStudent(int id, String name, String email, String phone) throws StudentNotFoundException {
        Student student = findStudentById(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
    }

    public void deleteStudent(int id) throws StudentNotFoundException {
        Student student = findStudentById(id);
        students.remove(student);
    }
}
