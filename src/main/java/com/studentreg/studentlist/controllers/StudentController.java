package com.studentreg.studentlist.controllers;

import com.studentreg.studentlist.models.Student;
import com.studentreg.studentlist.services.StudentService;

//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

//import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;


//import jakarta.*;

//no @RequestMapping means that every method needs to have it's url path spelled out for it

@RestController //RestController not Controller, the other does work, but rest is better suited for data 
//@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
 
    //fetch all students with endpoint
    @GetMapping("/students/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Boolean> deleteStudents(@PathVariable Long id) {
        Student student = studentService.findStudentById(id).orElse(null);
        studentService.deleteStudent(student);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/new/{firstName}/{lastName}/{dobString}/{email}/{phoneNumber}")
    public ResponseEntity<Student> createStudent(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String dobString, @PathVariable String email, @PathVariable Long phoneNumber){
        Student newStudent = new Student(firstName,  lastName, dobString, email , phoneNumber);
        newStudent.setDOB(dobString);
        return ResponseEntity.ok(studentService.addStudent(newStudent));
    }

    //   @PutMapping("/{phoneNumber}") or{dobLoaclD}, etc., paths look the same and cannot be differentiated at runtime

    //REST API usually has one update endpoint that accepts the entire object, aka. only one PUT

   /*@PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        student.set
        return ResponseEntity.ok(studentService.updateStudent(student));
    }*/
    

    @PutMapping("/{id}/phoneNumber/{phoneNumber}")
    public ResponseEntity<Student> updateStudentPN(@PathVariable Long phoneNumber, @PathVariable Long id){
        Student student = studentService.findStudentById(id).orElse(null);
        student.setPN(phoneNumber);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }
    
    @PutMapping("/{id}/dobLocalD/{dobString}")
    public ResponseEntity<Student> updateStudentDOB(@PathVariable String dobString, @PathVariable Long id){
        Student student = studentService.findStudentById(id).orElse(null);
        student.setDOB(dobString);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @PutMapping("/{id}/firstname/{firstName}")
    public ResponseEntity<Student> updateStudentFName(@PathVariable Long id, @PathVariable String firstName){
        Student student = studentService.findStudentById(id).orElse(null);
        student.setFName(firstName);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @PutMapping("/{id}/lastname/{lastName}")
    public ResponseEntity<Student> updateStudentLName(@PathVariable Long id, @PathVariable String lastName){
        Student student = studentService.findStudentById(id).orElse(null);
        student.setLName(lastName);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @PutMapping("/{id}/email/{email}")
    public ResponseEntity<Student> updateStudentEmail(@PathVariable String email, @PathVariable Long id){
        Student student = studentService.findStudentById(id).orElse(null);
        student.setEmail(email);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }


}