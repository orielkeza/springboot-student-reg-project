package com.studentreg.studentlist.repositories;

import com.studentreg.studentlist.models.Student;
//import org.springframework.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public Student getById(Long id);
    public List<Student> findAll();
}
