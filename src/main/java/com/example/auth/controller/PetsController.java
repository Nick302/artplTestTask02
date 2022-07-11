package com.example.auth.controller;


import com.example.auth.entity.Pet;
import com.example.auth.service.PetsService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/")
public class PetsController {

    private final PetsService petsService;


    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping(value = "pets")
    public List<Pet> getPetsAll() {
        return petsService.findAll();
    }


    @GetMapping(value = "pets/{id}")
    public Optional<Pet> getPetsAllById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return Optional.ofNullable(petsService.findAllById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException()));
    }


    @PostMapping(value = "pets")
    public Pet savePets(@RequestBody Pet pets) {
        return petsService.save(pets);
    }

    @PutMapping("pets/{id}")
    public Pet updatePets(
            @PathVariable("id") Pet petsFromDb,
            @RequestBody Pet pets
    ) {
        BeanUtils.copyProperties(pets, petsFromDb, "id");
        return petsService.save(petsFromDb);
    }

    @DeleteMapping("pets/{id}")
    public void removePets(@PathVariable("id") Pet pets) {
        petsService.delete(pets);
    }


}
