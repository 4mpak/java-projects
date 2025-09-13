package com.example.pet.mappers;

import com.example.pet.dto.PetDto;
import com.example.pet.models.Pet;
import com.example.pet.repositories.PetRepository;
import org.mapstruct.*;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "friendsId", source = "friends", qualifiedByName = "friendsToId")
    PetDto toDto(Pet pet);

    @Mapping(target = "friends", source = "friendsId", qualifiedByName = "idToFriends")
    Pet toEntity(PetDto petDto, @Context PetRepository petRepository);

    @Named("friendsToId")
    default List<Long> mapFriendsToIds(List<Pet> friends) {
        if (friends == null) return null;
        return friends.stream().map(Pet::getId).toList();
    }

    @Named("idToFriends")
    default List<Pet> mapIdsToFriends(List<Long> id, @Context PetRepository petRepository) {
        if (id == null || id.isEmpty()) return null;
        Iterable<Pet> petsIterable = petRepository.findAllById(id);
        List<Pet> petsList = new ArrayList<>();
        petsIterable.forEach(petsList::add);
        return petsList;
    }
}