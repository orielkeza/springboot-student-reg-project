package com.studentreg.studentlist.services;

import com.studentreg.studentlist.models.Student;
import com.studentreg.studentlist.repositories.StudentRepository;
//import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    
    public Optional<Student> findStudentById(Long id){ //make it optional to acknowledge that the student might not exist (Optional is a return type)
        return Optional.ofNullable(studentRepository.getById(id));//if value is null it will return that's it's empty
    }
    
    public void deleteStudent (Student student){
        studentRepository.delete(student);
    }
    
    public Student updateStudent (Student student){
        return studentRepository.save(student);
    }
}
