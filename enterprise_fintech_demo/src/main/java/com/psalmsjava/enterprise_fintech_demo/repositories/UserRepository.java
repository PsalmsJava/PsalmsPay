package com.psalmsjava.enterprise_fintech_demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psalmsjava.enterprise_fintech_demo.entities.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // Since email is unique, we'll find users by email
    Optional<UserEntity> findByEmail(String email);
}
