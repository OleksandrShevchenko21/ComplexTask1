package controllers;

import models.Manufacturer;
import models.Souvenir;
import services.SouvenirService;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SouvenirController {
    Scanner in = new Scanner(System.in);
    private SouvenirService souvenirService;

    public SouvenirController(SouvenirService souvenirService) {
        this.souvenirService = souvenirService;
    }

    //-----------------------Creating List of Souvenirs--------------------------
    public List<Souvenir> createListController() {
        return souvenirService.createSouvenirService();
    }

    //-----------------------Show List of Souvenirs--------------------------
    public void showListSouvenirController() throws FileNotFoundException {

        souvenirService.showListSouvenirService();

    }

    //-----------------------Show List of Manufactures--------------------------
    public void showListManufactureController(List<Souvenir> souvenirs) throws FileNotFoundException {
        List<Manufacturer> manufacturers = new ArrayList<>();

        manufacturers = souvenirs.stream()
                .map(s -> s.getManufacturer())
                .distinct()
                .collect(Collectors.toList());
        souvenirService.showListManufacturerService(manufacturers);

    }

    //-----------------------Add a souvenir to souvenirs list--------------------------
    public List<Souvenir> saveSouvenirController() {

        System.out.println("Enter the name of souvenir: ");
//        String souvenirName = "Cup";
//        Manufacturer manufacturerSouvenir = new Manufacturer("Toyota", "Japan");
//        int releaseYearSouvenir = 2020;
//        int priceSouvenir = 5;
        String souvenirName = in.nextLine();
        System.out.println("Enter the name of manufacture: ");
        String manufactureName = in.nextLine();
        System.out.println("Enter the country of manufacture: ");
        String manufactureCountry = in.nextLine();

        int releaseYearSouvenir = readInt("Enter the year of release: ");
        int priceSouvenir = readInt("Enter the price of souvenir: ");

        Manufacturer manufacturerSouvenir = new Manufacturer(manufactureName, manufactureCountry);
        Souvenir souvenir = new Souvenir(souvenirName, manufacturerSouvenir, releaseYearSouvenir, priceSouvenir);
        return souvenirService.saveSouvenirService(souvenir);
    }

    //-----------------------Edit a souvenir in souvenirs list--------------------------
    public List<Souvenir> editSouvenirController(List<Souvenir> souvenirs) {
        System.out.println("Enter the name of souvenir to determine the souvenir for edit");
        String souvenirNameToEdit = in.nextLine();
        System.out.println("Enter the name of manufacturer to determine the souvenir for edit");
        String souvenirManufactureNameEdit = in.nextLine();

        Souvenir souvenirToEdit = findSouvenirForEdit(souvenirs, souvenirNameToEdit, souvenirManufactureNameEdit);

        if (souvenirToEdit == null) {
            System.out.println("Souvenir doesn't exist");
            return souvenirs;
        } else {
            System.out.println("Enter the new name of souvenir: ");
            souvenirToEdit.setSouvenirName(in.nextLine());

            System.out.println("Enter the new name of manufacture: ");
            String manufactureName = in.nextLine();
            System.out.println("Enter the country of manufacture: ");
            String manufactureCountry = in.nextLine();
            souvenirToEdit.setManufacturer(new Manufacturer(manufactureName, manufactureCountry));

            souvenirToEdit.setReleaseYear(readInt("Enter the year of release: "));
            souvenirToEdit.setPrice(readInt("Enter the price of souvenir: "));
        }


        return souvenirService.editSouvenirService(souvenirs);

    }

    private Souvenir findSouvenirForEdit(List<Souvenir> souvenirs, String souvenirNameToEdit, String souvenirManufactureNameEdit) {
        return souvenirs.stream().filter(s -> s.getSouvenirName().equals(souvenirNameToEdit)
                        && s.getManufacturer().getManufacturerName().equals(souvenirManufactureNameEdit))
                .findFirst()
                .orElse(null);
    }

    //-----------------------Get a souvenirs by a given manufacturer--------------------------
    public void getSouvenirByOneManufacturers(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Manufacturer, List<Souvenir>> listByOneManufacturer = new HashMap<>();

        System.out.println("Enter the name of manufacturer to filter by name");
        String nameOfOneManufacture = in.nextLine();

        Souvenir souvenirsForFilter = findSouvenirForFilter(souvenirs, nameOfOneManufacture);

        if (souvenirsForFilter == null) {
            System.out.println("Souvenirs do not exist");

        } else {
            listByOneManufacturer = souvenirs.stream()
                    .filter(m -> m.getManufacturer().getManufacturerName().equals(nameOfOneManufacture))
                    .collect(Collectors.groupingBy(m -> m.getManufacturer()));
        }
        souvenirService.getByOneManufacturerService(listByOneManufacturer);
    }

    private Souvenir findSouvenirForFilter(List<Souvenir> souvenirs, String nameOfOneManufacture) {
        return souvenirs.stream().filter(s -> s.getManufacturer().getManufacturerName().equals(nameOfOneManufacture))
                .findFirst()
                .orElse(null);
    }

    //-----------------------Get a souvenirs by a given country--------------------------
    public void getSouvenirByOneCountry(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<String, List<Souvenir>> listByCountry = new HashMap<>();

        System.out.println("Enter the name of country to filter by");
        String nameOfOneCountry = in.nextLine();

        listByCountry = souvenirs.stream()
                .filter(m -> m.getManufacturer().getManufacturerCountry().equals(nameOfOneCountry))
                .collect(Collectors.groupingBy(m -> m.getManufacturer().getManufacturerCountry()));

        souvenirService.getByOneCountryService(listByCountry);
    }

    //-----------------------Get a souvenirs by a given price less then--------------------------
    public void getSouvenirByPriceLessThen(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Manufacturer, List<String>> manufacturerListByPrice = new HashMap<>();

        int lessPrice = readInt("Enter a price to show a manufacturers whose souvenir price is less than the entered price:");
        manufacturerListByPrice = souvenirs.stream()
                .filter(s -> s.getPrice() < lessPrice)
                .collect(Collectors.groupingBy(s -> s.getManufacturer(),

                        Collectors.mapping(s -> s.getSouvenirName() + " - Price: " + s.getPrice() + " euro - Year: " + s.getReleaseYear(),
                                Collectors.toList())));

        souvenirService.getSouvenirByPriceLessThenService(manufacturerListByPrice);
    }

    //-----------------------Get a souvenirs by all manufacturers--------------------------
    public void getSouvenirByManufacturers(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Manufacturer, List<Souvenir>> listByManufacturers = new HashMap<>();

        listByManufacturers = souvenirs.stream()
                .collect(Collectors.groupingBy(m -> m.getManufacturer()));

        souvenirService.getByManufacturersService(listByManufacturers);
    }

    //-----------------------Get a souvenirs by a given name and year--------------------------
    public void getSouvenirByNameAndYear(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<String, List<String>> listByNameAndYear = new HashMap<>();

        int oneYear = readInt("Enter the interested year");

        System.out.println("Enter the name of interested souvenir");
        String nameOfSouvenir = in.nextLine();

        Souvenir souvenirByNameAndYear = findSouvenirByNameAndYear(souvenirs, oneYear, nameOfSouvenir);
        if (souvenirByNameAndYear == null) {
            System.out.println("Souvenirs do not exist");
        } else {
            listByNameAndYear = souvenirs.stream()
                    .filter(s -> s.getReleaseYear() == oneYear && s.getSouvenirName().equals(nameOfSouvenir))
                    .collect(Collectors.groupingBy(s -> s.getSouvenirName() + " - " + s.getReleaseYear(),

                            Collectors.mapping(s -> s.getManufacturer().getManufacturerName() + "( " + s.getManufacturer().getManufacturerCountry()
                                            + " ) - Price: " + s.getPrice() + " euro",
                                    Collectors.toList())));

        }
        souvenirService.getByNameAndYearService(listByNameAndYear);
    }

    private Souvenir findSouvenirByNameAndYear(List<Souvenir> souvenirs, int oneYear, String nameOfSouvenir) {
        return souvenirs.stream().filter(s -> s.getReleaseYear() == oneYear && s.getSouvenirName().equals(nameOfSouvenir))
                .findFirst()
                .orElse(null);
    }

    //-----------------------Get a souvenirs by all years--------------------------
    public void getSouvenirByYears(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Integer, List<Souvenir>> mapByYears = new HashMap<>();

        mapByYears = souvenirs.stream()

                .collect(Collectors.groupingBy(s -> s.getReleaseYear()));


        souvenirService.getByYearsService(mapByYears);
    }

    //-----------------------Remove a souvenir by a given manufacturer--------------------------
    public List<Souvenir> removeSouvenirsByManufacture(List<Souvenir> souvenirs) {

        System.out.println("Enter the name of manufacturer to remove info from list: ");

        String removeManufactureName = in.nextLine();

        souvenirs = souvenirs.stream()
                .filter(s -> !s.getManufacturer().getManufacturerName().equals(removeManufactureName))
                .collect(Collectors.toList());
        return souvenirService.removeSouvenirsByManufactureService(souvenirs);
    }


    public int readInt(String s) {
        while (true) {
            System.out.println(s);
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer");
            }
        }
    }


}
