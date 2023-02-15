package controllers;

import models.Manufacturer;
import models.Souvenir;
import services.SouvenirService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SouvenirController {
    Scanner in = new Scanner(System.in);
    private SouvenirService souvenirService;
    private Helper helper = new Helper();

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
        String souvenirName = in.nextLine();
        System.out.println("Enter the name of manufacture: ");
        String manufactureName = in.nextLine();
        System.out.println("Enter the country of manufacture: ");
        String manufactureCountry = in.nextLine();

        LocalDate dateOfRelease = helper.readDate("Enter new date of release (yyyy-mm-dd): ");
        int priceSouvenir = helper.readInt("Enter the price of souvenir: ");

        Manufacturer manufacturerSouvenir = new Manufacturer(manufactureName, manufactureCountry);
        Souvenir souvenir = new Souvenir(souvenirName, manufacturerSouvenir, dateOfRelease, priceSouvenir);
        return souvenirService.saveSouvenirService(souvenir);
    }

    //-----------------------Edit a souvenir in souvenirs list--------------------------
    public List<Souvenir> editSouvenirController(List<Souvenir> souvenirs) {
        System.out.println("Enter the name of souvenir to determine the souvenir for edit");
        String souvenirNameToEdit = in.nextLine();
        System.out.println("Enter the name of manufacturer to determine the souvenir for edit");
        String souvenirManufactureNameEdit = in.nextLine();

        Souvenir souvenirToEdit = helper.findSouvenirForEdit(souvenirs, souvenirNameToEdit, souvenirManufactureNameEdit);

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

            souvenirToEdit.setDateOfRelease(helper.readDate("Enter new date of release (yyyy-mm-dd): "));
            souvenirToEdit.setPrice(helper.readInt("Enter the price of souvenir: "));
            return souvenirService.editSouvenirService(souvenirs);
        }


    }
    //        Вывести информацию о сувенирах заданного производителя.
    //-----------------------Get a souvenirs by a given manufacturer--------------------------
    public void getSouvenirByOneManufacturers(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Manufacturer, List<Souvenir>> listByOneManufacturer = new HashMap<>();

        System.out.println("Enter the name of manufacturer to filter by name");
        String nameOfOneManufacture = in.nextLine();

        Souvenir souvenirsByManufactureName = helper.findSouvenirByManufactureName(souvenirs, nameOfOneManufacture);

        if (souvenirsByManufactureName == null) {
            System.out.println("Souvenirs of this manufacture do not exist in this list");

        } else {
            listByOneManufacturer = souvenirs.stream()
                    .filter(m -> m.getManufacturer().getManufacturerName().equals(nameOfOneManufacture))
                    .collect(Collectors.groupingBy(m -> m.getManufacturer()));
            souvenirService.getByOneManufacturerService(listByOneManufacturer);
        }
    }

    //        Вывести информацию о сувенирах, произведенных в заданной стране.
    //-----------------------Get a souvenirs by a given country--------------------------
    public void getSouvenirByOneCountry(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<String, List<Souvenir>> listByCountry = new HashMap<>();

        System.out.println("Enter the name of country to filter by");
        String nameOfOneCountry = in.nextLine();

        Souvenir souvenirsByManufactureCountry = helper.findSouvenirByCountryName(souvenirs, nameOfOneCountry);

        if (souvenirsByManufactureCountry == null) {
            System.out.println("Souvenirs from this country do not exist in this list");

        } else {
            listByCountry = souvenirs.stream()
                    .filter(m -> m.getManufacturer().getManufacturerCountry().equals(nameOfOneCountry))
                    .collect(Collectors.groupingBy(m -> m.getManufacturer().getManufacturerCountry()));

            souvenirService.getByOneCountryService(listByCountry);
        }
    }
    //        Вывести информацию о производителях, чьи цены на сувениры меньше заданной.
    //-----------------------Get a souvenirs by a given price less then--------------------------
    public void getSouvenirByPriceLessThen(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Manufacturer, List<String>> manufacturerListByPrice = new HashMap<>();

        int lessPrice = helper.readInt("Enter a price to show a manufacturers whose souvenir price is less than the entered price:");
        manufacturerListByPrice = souvenirs.stream()
                .filter(s -> s.getPrice() < lessPrice)
                .collect(Collectors.groupingBy(s -> s.getManufacturer(),

                        Collectors.mapping(s -> s.getSouvenirName() + " - Price: " + s.getPrice() + " euro - Year: " + s.getDateOfRelease().getYear(),
                                Collectors.toList())));

        souvenirService.getSouvenirByPriceLessThenService(manufacturerListByPrice);
    }
    //        Вывести информацию по всем производителям и, для каждого производителя вывести информацию о всех сувенирах, которые он производит.
    //-----------------------Get a souvenirs by all manufacturers--------------------------
    public void getSouvenirByManufacturers(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Manufacturer, List<Souvenir>> listByManufacturers = new HashMap<>();

        listByManufacturers = souvenirs.stream()
                .collect(Collectors.groupingBy(m -> m.getManufacturer()));

        souvenirService.getByManufacturersService(listByManufacturers);
    }
    //        Вывести информацию о производителях заданного сувенира, произведенного в заданном году.
    //-----------------------Get a souvenirs by a given name and year--------------------------
    public void getSouvenirByNameAndYear(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<String, List<String>> listByNameAndYear = new HashMap<>();

        int oneYear = helper.readInt("Enter the interested year");

        System.out.println("Enter the name of interested souvenir");
        String nameOfSouvenir = in.nextLine();

        Souvenir souvenirByNameAndYear = helper.findSouvenirByNameAndYear(souvenirs, oneYear, nameOfSouvenir);
        if (souvenirByNameAndYear == null) {
            System.out.println("Souvenirs do not exist");
        } else {
            listByNameAndYear = souvenirs.stream()
                    .filter(s -> s.getDateOfRelease().getYear() == oneYear && s.getSouvenirName().equals(nameOfSouvenir))
                    .collect(Collectors.groupingBy(s -> s.getSouvenirName() + " - " + s.getDateOfRelease().getYear(),

                            Collectors.mapping(s -> s.getManufacturer().getManufacturerName() + "( " + s.getManufacturer().getManufacturerCountry()
                                            + " ) - Price: " + s.getPrice() + " euro",
                                    Collectors.toList())));

        }
        souvenirService.getByNameAndYearService(listByNameAndYear);
    }
    //        Для каждого года вывести список сувениров, произведенных в этом году.
    //-----------------------Get a souvenirs by all years--------------------------
    public void getSouvenirByYears(List<Souvenir> souvenirs) throws FileNotFoundException {
        Map<Integer, List<Souvenir>> mapByYears = new HashMap<>();

        mapByYears = souvenirs.stream()

                .collect(Collectors.groupingBy(s -> s.getDateOfRelease().getYear()));


        souvenirService.getByYearsService(mapByYears);
    }
    //       Удалить заданного производителя и его сувениры.
    //-----------------------Remove a souvenir by a given manufacturer--------------------------
    public List<Souvenir> removeSouvenirsByManufacture(List<Souvenir> souvenirs) {

        System.out.println("Enter the name of manufacturer to remove info from list: ");

        String removeManufactureName = in.nextLine();

        Souvenir souvenirsByManufactureName = helper.findSouvenirByManufactureName(souvenirs, removeManufactureName);

        if (souvenirsByManufactureName == null) {
            System.out.println("This manufacture do not exist in this list");
            return souvenirs;
        } else {

            souvenirs = souvenirs.stream()
                    .filter(s -> !s.getManufacturer().getManufacturerName().equals(removeManufactureName))
                    .collect(Collectors.toList());
            return souvenirService.removeSouvenirsByManufactureService(souvenirs);
        }
    }
}
