package com.example.artpltesttask02.controller;

import com.example.artpltesttask02.entity.Pet;
import com.example.artpltesttask02.entity.Sex;
import com.example.artpltesttask02.exception.NotFoundException;
import com.example.artpltesttask02.service.PetsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/")
public class PetsController {

    private final PetsService petsService;

    @Autowired
    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping(value = "pets")
    public List<Pet> getPetsAll() {
        return petsService.findAllBy();
    }


    @GetMapping(value = "pets/{id}")
    public Optional<Pet> getPetsAllById(@PathVariable Long id) {

        return Optional.ofNullable(petsService.findAllById(id).orElseThrow(() -> new NotFoundException("Oshibka")));
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
