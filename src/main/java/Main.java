import controllers.SouvenirController;
import dao.SouvenirDAO;
import dao.SouvenirFileDAO;
import models.Souvenir;
import services.SouvenirService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        SouvenirDAO souvenirDAO = new SouvenirFileDAO();
        SouvenirService souvenirService = new SouvenirService(souvenirDAO);
        SouvenirController souvenirController = new SouvenirController(souvenirService);

        List<Souvenir> souvenirsListMain = new ArrayList<>();

        souvenirsListMain = souvenirController.createListController();

        System.out.println("====Choose a number of action====");
//        Добавлять, редактировать, просматривать всех производителей и все сувениры.
        System.out.println("1. Show a List of souvenirs");
        System.out.println("2. Show a List of manufacturers");
        System.out.println("3. Add a souvenir");
        System.out.println("4. Edit a souvenir");
//        Вывести информацию о сувенирах заданного производителя.
        System.out.println("5. Show a List of souvenirs by one manufacturer");
//        Вывести информацию о сувенирах, произведенных в заданной стране.
        System.out.println("6. Show a List of souvenirs by one country");
//        Вывести информацию о производителях, чьи цены на сувениры меньше заданной.
        System.out.println("7. Show a List of manufacturers by price less then...");
//        Вывести информацию по всем производителям и, для каждого производителя вывести информацию о всех сувенирах, которые он производит.
        System.out.println("8. Show a List of manufacturers with list of souvenirs");
//        Вывести информацию о производителях заданного сувенира, произведенного в заданном году.
        System.out.println("9. Show an info of manufacturers by the given souvenir produced year and his name.");
//        Для каждого года вывести список сувениров, произведенных в этом году.
        System.out.println("10. Show a List of souvenirs by year.");
//       Удалить заданного производителя и его сувениры.
        System.out.println("11. Remove a manufacturer and list of his souvenirs.");

        String pressOne = "You can press 1 to see the changes in list";
        String actionString = "====Choose a new number of action====";
        String option = " ";
        String exit = "exit ";

        while (exit != option) {
            option = in.nextLine();
            switch (option) {

                case "1":
                    souvenirController.showListSouvenirController();
                    System.out.println(actionString);
                    break;
                case "2":
                    souvenirController.showListManufactureController(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "3":
                    souvenirsListMain = souvenirController.saveSouvenirController();
                    System.out.println(pressOne);
                    System.out.println(actionString);
                    break;
                case "4":
                    souvenirsListMain = souvenirController.editSouvenirController(souvenirsListMain);
                    System.out.println(pressOne);
                    System.out.println(actionString);
                    break;
                case "5":
                    souvenirController.getSouvenirByOneManufacturers(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "6":
                    souvenirController.getSouvenirByOneCountry(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "7":
                    souvenirController.getSouvenirByPriceLessThen(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "8":
                    souvenirController.getSouvenirByManufacturers(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "9":
                    souvenirController.getSouvenirByNameAndYear(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "10":
                    souvenirController.getSouvenirByYears(souvenirsListMain);
                    System.out.println(actionString);
                    break;
                case "11":
                    souvenirsListMain = souvenirController.removeSouvenirsByManufacture(souvenirsListMain);
                    System.out.println(pressOne);
                    System.out.println(actionString);
                    break;
                case "0":
                    exit = option;
                    break;

            }
        }


    }
}