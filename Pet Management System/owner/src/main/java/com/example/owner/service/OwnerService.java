package com.example.owner.service;

import com.example.owner.dto.OwnerDto;
import com.example.owner.mappers.OwnerMapper;
import com.example.owner.models.Owner;
import com.example.owner.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService
{
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Transactional
    public OwnerDto save(OwnerDto ownerDto) {
        Owner owner = ownerMapper.toEntity(ownerDto);
        ownerRepository.save(owner);
        return ownerMapper.toDto(owner);
    }

    @Transactional
    public void deleteById(long id) {
        ownerRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    @Transactional
    public OwnerDto update(long id, OwnerDto updatedOwnerDto) {
        Owner updatedOwner = ownerMapper.toEntity(updatedOwnerDto);
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + id));
        owner.setName(updatedOwner.getName());
        owner.setBirthDate(updatedOwner.getBirthDate());
        owner.setPetsId(updatedOwner.getPetsId());
        ownerRepository.save(owner);
        return ownerMapper.toDto(owner);
    }

    @Transactional
    public OwnerDto getById(long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + id));
        return ownerMapper.toDto(owner);
    }

    @Transactional
    public List<OwnerDto> getAll() {
        Iterable<Owner> ownersIterable = ownerRepository.findAll();
        List<Owner> ownersList = new ArrayList<>();
        ownersIterable.forEach(ownersList::add);
        return ownersList.stream().map(ownerMapper::toDto).collect(Collectors.toList());
    }
}
