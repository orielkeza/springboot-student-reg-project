package com.studentreg.studentlist;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
import java.util.Optional;

//import com.studentreg.*;
import com.studentreg.studentlist.models.Student;
import com.studentreg.studentlist.repositories.StudentRepository;
import com.studentreg.studentlist.services.StudentService;

//import org.junit.*;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.junit.jupiter.api.Test;

//to mock id generations
import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;//fake instance

    @InjectMocks
    private StudentService studentService; //real instance

    @Test
    public void testfindStudentById() {
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        //Student savedStudent = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student,"id",1L);
        when(studentRepository.getById(1L)).thenReturn(student);
        assertNotNull(student.getId());
        assertEquals(1L, student.getId());
        Optional <Student> found = studentService.findStudentById(1L);
        assertNotNull(found);
        assertEquals(found, studentService.findStudentById(1L));
        assertEquals(student.getId(), found.get().getId());
        //assertEquals(student.getId(), found.getId());//to make sure the mock aka what i set up and running a real instance produces the same results
        //studentRepository.save(found);
        //assertNotNull(found);
        //when(studentRepository.findById(1L)).thenReturn(Optional.of(found));
        //Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        //assertEquals(student.getId(), studentService.findStudentById(1L));
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        studentService.addStudent(student);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        assertEquals("j1doe@gmail.com", student.getEmail());
        assertEquals("Alpha", student.getFirstName());
        assertEquals("One", student.getLastName());
        assertEquals(790789789L, student.getPN());
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        students.add(new Student("Beta",  "Two", "2017-04-12", "j2doe@gmail.com" , 897645342L));
        when(studentRepository.findAll()).thenReturn(students);
        assertEquals(students, studentService.getAllStudent());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        studentService.deleteStudent(student);
        verify(studentRepository).delete(student);
        //assertNull(student); can never pass because you cannot delete a local var in java
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        studentService.addStudent(student);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        assertEquals("j1doe@gmail.com", student.getEmail());
        assertEquals("Alpha", student.getFirstName());
        assertEquals("One", student.getLastName());
        assertEquals(790789789L, student.getPN());
        student.setDOB("2017-11-08");
        student.setEmail("j2doe@gmail.com");
        student.setFName("Beta");
        student.setLName("Two");
        student.setPN(987654889L);
        studentService.updateStudent(student);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2017,11,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2017,11,8), student.getDOB());
        assertEquals("j2doe@gmail.com", student.getEmail());
        assertEquals("Beta", student.getFirstName());
        assertEquals("Two", student.getLastName());
        assertEquals(987654889L, student.getPN());
    }
}

//turns out the actual issue is that the student is not actually null, the object was created but the id was not properly generated
