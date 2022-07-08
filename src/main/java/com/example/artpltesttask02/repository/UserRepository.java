package com.example.artpltesttask02.repository;

import com.example.artpltesttask02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllBy();

    User findAllById(Long id);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(User entity);
}
