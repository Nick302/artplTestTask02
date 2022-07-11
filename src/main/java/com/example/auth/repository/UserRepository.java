package com.example.auth.repository;

import com.example.auth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
