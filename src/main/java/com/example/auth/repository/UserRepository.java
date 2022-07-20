package com.example.auth.repository;

import com.example.auth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAll();

    Optional<AppUser> findById(Long id);

    @Override
    <S extends AppUser> S save(S entity);

    @Override
    void delete(AppUser entity);

    Optional<AppUser> findByEmail(String email);

    AppUser findAllByEmail(String email);

    @Modifying
    @Query("UPDATE app_user u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    void updateFailedAttempts(int failAttempts, String email);




}
