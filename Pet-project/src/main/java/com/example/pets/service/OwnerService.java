package com.example.pets.service;

import com.example.pets.models.Owner;
import com.example.pets.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService
{
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void deleteById(long id) {
        ownerRepository.deleteById(id);
    }

    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    public Owner update(long id, Owner updatedOwner) {
        Owner owner = ownerRepository.findById(id).get();
        owner.setName(updatedOwner.getName());
        owner.setPetList(updatedOwner.getPetList());
        return ownerRepository.save(owner);
    }

    public Owner getById(long id) { return ownerRepository.findById(id).get(); }

    public List<Owner> getAll()
    {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }
}
