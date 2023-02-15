package controllers;

import models.Souvenir;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class Helper {
    Scanner in = new Scanner(System.in);

    protected int readInt(String s) {
        while (true) {
            System.out.println(s);
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer");
            }
        }
    }

    protected LocalDate readDate(String s) {
        while (true) {
            System.out.println(s);
            try {
                return LocalDate.parse(in.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input. Please enter a valid date");
            }
        }
    }

    protected Souvenir findSouvenirForEdit(List<Souvenir> souvenirs, String souvenirNameToEdit, String souvenirManufactureNameEdit) {
        return souvenirs.stream().filter(s -> s.getSouvenirName().equals(souvenirNameToEdit)
                        && s.getManufacturer().getManufacturerName().equals(souvenirManufactureNameEdit))
                .findFirst()
                .orElse(null);
    }

    protected Souvenir findSouvenirByManufactureName(List<Souvenir> souvenirs, String nameOfOneManufacture) {
        return souvenirs.stream().filter(s -> s.getManufacturer().getManufacturerName().equals(nameOfOneManufacture))
                .findFirst()
                .orElse(null);
    }

    protected Souvenir findSouvenirByCountryName(List<Souvenir> souvenirs, String nameOfOneCountry) {
        return souvenirs.stream().filter(s -> s.getManufacturer().getManufacturerCountry().equals(nameOfOneCountry))
                .findFirst()
                .orElse(null);
    }

    protected Souvenir findSouvenirByNameAndYear(List<Souvenir> souvenirs, int oneYear, String nameOfSouvenir) {
        return souvenirs.stream().filter(s -> s.getDateOfRelease().getYear() == oneYear && s.getSouvenirName().equals(nameOfSouvenir))
                .findFirst()
                .orElse(null);
    }
}
