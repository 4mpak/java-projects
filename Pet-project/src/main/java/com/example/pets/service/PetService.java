package com.example.pets.service;

import com.example.pets.models.Pet;
import com.example.pets.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetService
{
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public void deleteById(long id) {
        petRepository.deleteById(id);
    }

    public void deleteAll() {
        petRepository.deleteAll();
    }

    @Transactional
    public Pet update(long id, Pet updatedPet) {
        Pet pet = petRepository.findById(id).get();
        pet.setName(updatedPet.getName());
        pet.setColorPet(updatedPet.getColorPet());
        pet.setOwner(updatedPet.getOwner());
        pet.setPetFriends(updatedPet.getPetFriends());
        return petRepository.save(pet);
    }

    public Pet getById(long id) { return petRepository.findById(id).get(); }

    public List<Pet> getAll()
    {
        List<Pet> pets = new ArrayList<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }
}
