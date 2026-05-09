package com.studentreg.studentlist;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.util.List;

//import java.util.List;

import com.studentreg.studentlist.models.Student;
//import com.studentreg.studentlist.controllers.StudentController;
import com.studentreg.studentlist.repositories.StudentRepository;
//import com.studentreg.studentlist.services.StudentService;

//import org.example.*;
//import org.junit.*;
//import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudentRepositoryTest {
    
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent(){
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        Student savedStudent = studentRepository.save(student);
        assertNotNull(savedStudent);
        assertEquals(student, savedStudent);
    }

    @Test
    public void testStudent(){
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        studentRepository.save(student);
        Student fetchedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(fetchedStudent);
        assertEquals(student.getId(), fetchedStudent.getId());
    }

    @Test
    public void testDeleteStudent(){
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        studentRepository.save(student);
        studentRepository.delete(student);
        assertThat(studentRepository.findById(student.getId())).isEmpty();
    }

    @Test
    public void testDeleteStudentbyID(){
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());
        Student deletedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNull(deletedStudent);
    }

    @Test
    public void testGetListOfStudents(){
        studentRepository.save(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        studentRepository.save(new Student("Beta",  "Two", "2007-06-07", "j2doe@gmail.com" , 790789788L));
        studentRepository.save(new Student("Charlie",  "Three", "2012-07-08", "j3doe@gmail.com" , 790789789L));
        studentRepository.save(new Student("Delta",  "Four", "2009-05-06", "j4doe@gmail.com" , 799789789L));
        List<Student> students = studentRepository.findAll();
        assertNotNull(students);
    }    

    @Test
    public void testPutFN(){
        Student student = studentRepository.save(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        studentRepository.save(student);
        student.setFName("Beta");
        studentRepository.save(student);
        Student updatedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals(student, updatedStudent);
    }

    @Test
    public void testPutLN(){
        Student student = studentRepository.save(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        student.setLName("Two");
        studentRepository.save(student);
        Student updatedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals(student, updatedStudent);
    }

    @Test
    public void testPutPN(){
        Student student = studentRepository.save(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        student.setPN(788996543L);
        studentRepository.save(student);
        Student updatedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals(student, updatedStudent);
    }

    @Test
    public void testPutEmail(){
        Student student = studentRepository.save(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        student.setEmail("j2doe@gmail.com");
        studentRepository.save(student);
        Student updatedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals(student, updatedStudent);
    }

    @Test
    public void testPutDOB(){
        Student student = studentRepository.save(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        student.setDOB("2001-09-23");
        studentRepository.save(student);
        Student updatedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(updatedStudent);
        assertEquals(student, updatedStudent);
    }
}
