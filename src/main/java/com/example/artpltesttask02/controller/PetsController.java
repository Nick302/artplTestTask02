package com.example.artpltesttask02.controller;

import com.example.artpltesttask02.entity.Pets;
import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.service.PetsService;
import com.example.artpltesttask02.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class PetsController {

    private final PetsService petsService;

    @Autowired
    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping(value = "pets")
    public List<Pets> getPetsAll() {
        return petsService.findAllBy();
    }

    @GetMapping(value = "pets/{id}")
    public Pets getPetsAllById(@PathVariable Long id) {
        return petsService.findAllById(id);
    }

    @PostMapping(value = "pets")
    public Pets savePets(@RequestBody Pets pets) {
        return petsService.save(pets);
    }

    @PutMapping("pets/{id}")
    public Pets updatePets(
            @PathVariable("id") Pets petsFromDb,
            @RequestBody Pets pets
    ) {
        BeanUtils.copyProperties(pets, petsFromDb, "id");
        return petsService.save(petsFromDb);
    }

    @DeleteMapping("pets/{id}")
    public void removePets(@PathVariable("id") Pets pets) {
        petsService.delete(pets);
    }




}
