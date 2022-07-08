package com.example.artpltesttask02.service;

import com.example.artpltesttask02.entity.Pet;
import com.example.artpltesttask02.entity.Sex;
import com.example.artpltesttask02.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PetsService {
    private final PetsRepository petsRepository;

    @Autowired
    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    /**
     * - создавать /редактировать/удалить животных [Вид(из списка-справочника), дата рождениЯ, пол, Кличка(уникальна)] .
     * - получить список своих животных.
     * - получить детали любого животного по id.
     */

    public List<Pet> findAllBy() {
        return petsRepository.findAllBy();
    }

    public Optional<Pet> findAllById(Long id) {
        return petsRepository.findAllById(id);
    }

    public void delete(Pet entity) {
        petsRepository.delete(entity);
    }

    public <S extends Pet> S save(S entity) {
        return petsRepository.save(entity);
    }



    public PetsRepository getPetsRepository() {
        return petsRepository;
    }

}
