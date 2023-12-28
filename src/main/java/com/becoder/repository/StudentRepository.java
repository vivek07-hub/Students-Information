package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
