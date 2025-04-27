package util;

import controllers.OwnerController;
import controllers.PetController;
import models.Pet;
import models.Owner;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OwnerController ownerController = new OwnerController();
        PetController petController = new PetController();

        Owner sigma = ownerController.createowner(
                "Алина Рамазанова",
                LocalDate.of(2005, 9, 23),
                List.of()
        );
        System.out.println("Самый крутой владелец: " + sigma.getName());

        Pet cat1 = petController.createPet(
                "Кокоджамбик",
                LocalDate.of(2020, 5, 10),
                "Чебоксарский",
                "red",
                sigma,
                List.of()
        );
        System.out.println("Мой первый котик: " + cat1.getName());

        Pet cat2 = petController.createPet(
                "Луиджи",
                LocalDate.of(2019, 7, 22),
                "Субкультурный",
                "yellow",
                sigma,
                List.of(cat1)
        );
        System.out.println("Мой второй котик: " + cat2.getName());

        Pet cat3 = petController.createPet(
                "Обдолбыш",
                LocalDate.of(2021, 2, 28),
                "Челябинский",
                "green",
                sigma,
                List.of(cat1, cat2)
        );
        System.out.println("Мой третий котик: " + cat3.getName());

        petController.updatePet(cat1, cat1.getName(), cat1.getColorPet().name(), sigma, List.of(cat2, cat3));
    }
}
