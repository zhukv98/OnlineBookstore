package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	
}
