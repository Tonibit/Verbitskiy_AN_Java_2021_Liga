package com.verbitskiy.springboot_mvc.repository;

import com.verbitskiy.springboot_mvc.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
