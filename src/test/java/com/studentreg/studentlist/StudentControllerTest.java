package com.studentreg.studentlist;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.studentreg.*;
import com.studentreg.studentlist.controllers.StudentController;
import com.studentreg.studentlist.models.Student;
import com.studentreg.studentlist.services.StudentService;

//import org.junit.*;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.*;

//import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//to mock id generations
import org.springframework.test.util.ReflectionTestUtils;

@WebMvcTest(StudentController.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean//Mockito not Mock
    private StudentService studentService;
    
    /*@Autowired
    private ObjectMapper objectMapper;
    */

    /*@Test
    public void testGetStudentById() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.post("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789L));
    } */
    
        
    @Test
    public void testCreateStudent() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        when(studentService.addStudent(any())).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/new/Alpha/One/2007-05-08/j1doe@gmail.com/790789789"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789L));
        verify(studentService).addStudent(any());
    } 

    @Test
    public void testGetAllStudents() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L));
        students.add(new Student("Beta",  "Two", "2007-06-17", "j2doe@gmail.com" , 790789788L));
        ReflectionTestUtils.setField(students.get(0),"id",1L);
        ReflectionTestUtils.setField(students.get(0),"dobLocalD",LocalDate.of(2007,05,8));
        ReflectionTestUtils.setField(students.get(1),"dobLocalD",LocalDate.of(2007,06,17));
        ReflectionTestUtils.setField(students.get(1),"id",2L);
        assertEquals(1L, students.get(0).getId());
        when(studentService.getAllStudent()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].PN").value(790789789L))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Beta"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Two"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].DOB").value("2007-06-17"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("j2doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].PN").value(790789788L));
    }   

    @Test
    public void testDeleteStudentSuccess() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        doNothing().when(studentService).deleteStudent(student);
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.delete("/1/delete"))
        .andExpect(MockMvcResultMatchers.status().isOk());
        /*.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(null))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789L));*/
        verify(studentService).deleteStudent(student);
    } 

    @Test
    public void testUpdatePN() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.updateStudent(student)).thenReturn(student);
        studentService.updateStudent(student);
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.put("/1/phoneNumber/788996543"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(788996543L));
    } 

    @Test
    public void testUpdateEmail() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.updateStudent(student)).thenReturn(student);
        studentService.updateStudent(student);
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.put("/1/email/j2doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j2doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789L));
    } 

    @Test
    public void testUpdateDOB() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.updateStudent(student)).thenReturn(student);
        studentService.updateStudent(student);
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.put("/1/dobLocalD/2001-09-23"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2001-09-23"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789L));
    } 

    @Test
    public void testUpdateLN() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.updateStudent(student)).thenReturn(student);
        studentService.updateStudent(student);
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.put("/1/lastname/Two"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Alpha"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Two"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789l));
    } 

    @Test
    public void testUpdateFN() throws Exception { //throws exception is included to tell java that if something breaks just stop the test and tell me why, i know it breaking is possible so skip the complicated stuff
        Student student = new Student("Alpha",  "One", "2007-05-08", "j1doe@gmail.com" , 790789789L);
        ReflectionTestUtils.setField(student, "id", 1L);
        ReflectionTestUtils.setField(student,"dobLocalD",LocalDate.of(2007,05,8));
        assertEquals(1L, student.getId());
        assertEquals(LocalDate.of(2007,05,8), student.getDOB());
        when(studentService.updateStudent(student)).thenReturn(student);
        studentService.updateStudent(student);
        when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.put("/1/firstname/Beta"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Beta"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("One"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.DOB").value("2007-05-08"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("j1doe@gmail.com"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.PN").value(790789789L));
    } 
    
}
