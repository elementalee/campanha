package com.campanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha.entity.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha,Long> {
	
}
