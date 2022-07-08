package com.example.artpltesttask02.repository;

import com.example.artpltesttask02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllBy();

    Optional<User> findById(Long id);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(User entity);

    Optional<User> existsUserByUsername(String username);


}
