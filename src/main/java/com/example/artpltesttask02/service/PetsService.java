package com.example.artpltesttask02.service;

import com.example.artpltesttask02.entity.Pets;
import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.repository.PetsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Data
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

   public List<Pets> findAllBy(){
        return petsRepository.findAllBy();
    }

    public Pets findAllById(Long id){
       return petsRepository.findAllById(id);
    }

    public List<Pets> findAllBySex(String sex){
       return findAllBySex(sex);
    }

    public List<Pets> findAllByBirthDay(Date date){
       return findAllByBirthDay(date);
    }

    public Pets findByNickName(String name){
       return petsRepository.findByNickName(name);
    }

    public void delete(Pets entity){
        petsRepository.delete(entity);
    }

    public <S extends Pets> S save(S entity){
        return petsRepository.save(entity);
    }

    public boolean existsByNickName(String name) // - Ќе зарегистрированный пользователь должен иметь возможность проверить доступность имени через сервис (валидации).
    {
        return existsByNickName(name);
    }
}
