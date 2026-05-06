package com.studentreg.studentlist.models;

import java.time.LocalDate; //to store dates

import jakarta.persistence.*; //JPA

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //student id number
    private String firstName;
    private String lastName;
    //private String dobString; //date of birth as a string
    private LocalDate dobLocalD; //date of birth as a number
    private String email;
    private Long phoneNumber;

    public Student() {} //default ctor

    public Student(String firstName, String lastName, String dobString, String email, Long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        //this.dobString = dobString;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //assume string is inserted as yyyy-mm-dd
    public void setDOB(String dobString){
        dobLocalD = LocalDate.parse(dobString);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setFName(String firstName){
        this.firstName = firstName;
    }

    public void setLName (String lastName){
        this.lastName = lastName;
    }

    public LocalDate getDOB(){
        return dobLocalD;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Long getPN() {
        return phoneNumber;
    }

    public void setPN(Long phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
}
