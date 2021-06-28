package com.demo.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
 
}
