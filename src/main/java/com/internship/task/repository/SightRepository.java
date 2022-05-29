package com.internship.task.repository;

import com.internship.task.entity.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightRepository extends JpaRepository<Sight, Long> {
}
