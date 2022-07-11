package com.example.auth.repository;


import com.example.auth.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pet, Long> {
    /**
     * - создавать /редактировать/удалить животных [Вид(из списка-справочника), дата рождениЯ, пол, Кличка(уникальна)] .
     * - получить список своих животных.
     * - получить детали любого животного по id.
     */

    List<Pet> findAll();

    Optional<Pet> findAllById(Long id);

    @Override
    <S extends Pet> S save(S entity);

    @Override
    void delete(Pet entity);

}
