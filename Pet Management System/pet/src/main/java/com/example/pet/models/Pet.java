package com.example.pet.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthDate;
    private String breed;

    @Enumerated(EnumType.STRING)
    private ColorPet colorPet;

    private Long ownerId;

    @ManyToMany
    @JoinTable (
            name = "friends_pet",
            joinColumns= @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Pet> friends;
}