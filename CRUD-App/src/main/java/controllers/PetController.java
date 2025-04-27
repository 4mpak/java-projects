package controllers;

import models.ColorPet;
import models.Owner;
import models.Pet;
import service.PetService;

import java.time.LocalDate;
import java.util.List;

public class PetController {
    private final PetService petService = new PetService();

    public Pet createPet(String name, LocalDate birthDate, String breed, String colorPet, Owner owner, List<Pet> pets) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setBirthDate(birthDate);
        pet.setBreed(breed);
        ColorPet color = ColorPet.valueOf(colorPet);
        pet.setColorPet(color);
        pet.setOwner(owner);
        pet.setPetFriends(pets);
        return (Pet)petService.save(pet);
    }

    public void deletePetById(long id) {
        petService.deleteById(id);
    }

    public void deletePet(Pet pet) {
        petService.delete(pet);
    }

    public void deleteAllPets() {
        petService.deleteAll();
    }

    public Pet updatePet(Pet pet, String newName, String newColorPet, Owner newOwner, List<Pet> newPets) {
        pet.setName(newName);
        ColorPet color = ColorPet.valueOf(newColorPet);
        pet.setColorPet(color);
        pet.setOwner(newOwner);
        pet.setPetFriends(newPets);
        return (Pet)petService.update(pet);
    }

    public Pet getPetById(long id) {
        return (Pet)petService.getById(id);
    }

    public List<Object> getAllPets() {
        return petService.getAll();
    }
}
