package com.accenture.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.entity.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable>{

}
