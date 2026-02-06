package com.example.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
