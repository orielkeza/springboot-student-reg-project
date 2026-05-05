package com.studentreg.studentlist.services;

import com.studentreg.studentlist.models.Student;
import com.studentreg.studentlist.repositories.StudentRepository;
//import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent (Student student){
        return studentRepository.save(student);
    }
    
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    
    public Student findStudentById(Long id){
        return studentRepository.getById(id);
    }
    
    public void deleteStudent (Student student){
        studentRepository.delete(student);
    }
    
    public Student updateStudent (Student student){
        return studentRepository.save(student);
    }
}
