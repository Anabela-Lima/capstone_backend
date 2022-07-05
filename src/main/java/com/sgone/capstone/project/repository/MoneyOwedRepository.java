package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.MoneyOwed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyOwedRepository extends JpaRepository<MoneyOwed, Long> {
}
