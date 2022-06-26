package com.sgone.capstone.dataloader.repository;

import com.sgone.capstone.project.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderTripRepository extends JpaRepository<Trip, Long> {
}
