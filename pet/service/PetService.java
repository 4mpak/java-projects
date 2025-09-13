package com.example.pet.service;

import com.example.pet.dto.PetDto;
import com.example.pet.mappers.PetMapper;
import com.example.pet.models.Pet;
import com.example.pet.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService
{
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Transactional
    public PetDto save(PetDto petDto) {
        Pet pet = petMapper.toEntity(petDto, petRepository);
        petRepository.save(pet);
        return petMapper.toDto(pet);
    }

    @Transactional
    public void deleteById(long id) {
        petRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() { petRepository.deleteAll(); }

    @Transactional
    public PetDto update(long id, PetDto updatedPetDto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
        Pet updatedPet = petMapper.toEntity(updatedPetDto, petRepository);
        pet.setName(updatedPet.getName());
        pet.setColorPet(updatedPet.getColorPet());
        pet.setOwnerId(updatedPet.getOwnerId());
        pet.setFriends(updatedPet.getFriends());
        petRepository.save(pet);
        return petMapper.toDto(pet);
    }

    @Transactional
    public PetDto getById(long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
        return petMapper.toDto(pet);
    }

    @Transactional
    public List<PetDto> getAll() {
        Iterable<Pet> petsIterable = petRepository.findAll();
        List<Pet> petsList = new ArrayList<>();
        petsIterable.forEach(petsList::add);
        return petsList.stream().map(petMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public Long getPetOwnerId(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petId));
        return pet.getOwnerId();
    }
}
