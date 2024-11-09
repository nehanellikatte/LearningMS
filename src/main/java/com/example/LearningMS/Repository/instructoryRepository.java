package com.example.LearningMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearningMS.Entity.instructor;

public interface instructoryRepository extends JpaRepository<instructor, Integer> {

}
