package com.example.artpltesttask02.repository;

import com.example.artpltesttask02.entity.Pet;
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

    List<Pet> findAllBy();

    Optional<Pet> findAllById(Long id);

    @Override
    <S extends Pet> S save(S entity);

    @Override
    void delete(Pet entity);

}
