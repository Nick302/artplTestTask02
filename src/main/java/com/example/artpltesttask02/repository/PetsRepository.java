package com.example.artpltesttask02.repository;

import com.example.artpltesttask02.entity.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {
    /**
     * - создавать /редактировать/удалить животных [Вид(из списка-справочника), дата рождениЯ, пол, Кличка(уникальна)] .
     * - получить список своих животных.
     * - получить детали любого животного по id.
     */

    List<Pets> findAllBy();

    Pets findAllById(Long id);

    List<Pets> findAllBySex(String sex);

    List<Pets> findAllByBirthDay(Date date);

    Pets findByNickName(String name);

    @Override
    <S extends Pets> S save(S entity);

    @Override
    void delete(Pets entity);

    boolean existsByNickName(String name); // - Ќе зарегистрированный пользователь должен иметь возможность проверить доступность имени через сервис (валидации).


}
