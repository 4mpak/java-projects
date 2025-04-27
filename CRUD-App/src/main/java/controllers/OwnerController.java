package controllers;

import models.Owner;
import models.Pet;
import service.OwnerService;
import java.time.LocalDate;
import java.util.List;

public class OwnerController {
    private final OwnerService ownerService = new OwnerService();

    public Owner createowner(String name, LocalDate birthDate, List<Pet> pets) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setBirthDate(birthDate);
        owner.setPetList(pets);
        return (Owner)ownerService.save(owner);
    }

    public void deleteOwnerById(long id) {
        ownerService.deleteById(id);
    }

    public void deleteOwner(Owner owner) {
        ownerService.delete(owner);
    }

    public void deleteAllOwners() {
        ownerService.deleteAll();
    }

    public Owner updateOwner(Owner owner, String newName, List<Pet> newPets) {
        owner.setName(newName);
        owner.setPetList(newPets);
        return (Owner)ownerService.update(owner);
    }

    public Owner getOwnerById(long id) {
        return (Owner)ownerService.getById(id);
    }

    public List<Object> getAllOwners() {
        return ownerService.getAll();
    }
}
