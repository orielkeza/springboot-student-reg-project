package com.studentreg.studentlist.controllers;

import com.studentreg.studentlist.models.Student;
import com.studentreg.studentlist.services.StudentService;
import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import jakarta.*;


@RestController //RestController not Controller, the other does work, but rest is better suited for data 
public class StudentController {

    @Autowired
    private StudentService studentService;
 
    //fetch all students with endpoint
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteStudents(@PathVariable Student student) {
        studentService.deleteStudent(student);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    //   @PutMapping("/{phoneNumber}") or{dobLoaclD}, etc., paths look the same and cannot be differentiated at runtime

    //REST API usually has one update endpoint that accepts the entire object, aka. only one PUT

   /*@PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        student.set
        return ResponseEntity.ok(studentService.updateStudent(student));
    }*/
    

    @PutMapping("/phoneNumber")
    public ResponseEntity<Student> updateStudentPN(@PathVariable Long phoneNumber, @RequestBody Student student){
        student.setPN(phoneNumber);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }
    
    @PutMapping("/dobLocalD")
    public ResponseEntity<Student> updateStudentDOB(@PathVariable String dobString, @RequestBody Student student){
        student.setDOB(dobString);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @PutMapping("/firstName")
    public ResponseEntity<Student> updateStudentFName(@PathVariable String firstName, @RequestBody Student student){
        student.setFName(firstName);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @PutMapping("/lastName")
    public ResponseEntity<Student> updateStudentLName(@PathVariable String lastName, @RequestBody Student student){
        student.setLName(lastName);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @PutMapping("/email")
    public ResponseEntity<Student> updateStudentEmail(@PathVariable String email, @RequestBody Student student){
        student.setEmail(email);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }


}
