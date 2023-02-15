package dao;

import models.Manufacturer;
import models.Souvenir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SouvenirFileDAO implements SouvenirDAO {
    List<Souvenir> souvenirList = new ArrayList<>();

    public SouvenirFileDAO() throws FileNotFoundException {

        souvenirList.add(new Souvenir("Cup", new Manufacturer("Boeing", "USA"), LocalDate.of(2018, 10, 15), 2));
        souvenirList.add(new Souvenir("Pen", new Manufacturer("Boeing", "USA"), LocalDate.of(2019, 3, 15), 3));
        souvenirList.add(new Souvenir("T-shirt", new Manufacturer("Siemens", "USA"), LocalDate.of(2018, 5, 15), 4));
        souvenirList.add(new Souvenir("Cup", new Manufacturer("Channel", "France"), LocalDate.of(2020, 12, 15), 5));
        souvenirList.add(new Souvenir("Cup", new Manufacturer("Kyivstar", "Ukraine"), LocalDate.of(2020, 9, 15), 6));
        souvenirList.add(new Souvenir("Notebook", new Manufacturer("Adidas", "Germany"), LocalDate.of(2022, 1, 15), 7));

    }

    @Override
    public List<Souvenir> create() {
        return souvenirList;
    }

    @Override
    public void show() throws FileNotFoundException {
        File f2 = new File("List of SOUVENIRS.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f2), true));

        System.out.println("List is shown in file (List of SOUVENIRS.txt)");
        for (Souvenir s : souvenirList) {
            out.println(s);
        }
        out.flush();

    }

    @Override
    public void showManufactures(List<Manufacturer> manufacturers) throws FileNotFoundException {
        File f22 = new File("List of MANUFACTURERS.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f22), true));

        System.out.println("List is shown in file (List of MANUFACTURERS.txt)");
        for (Manufacturer m : manufacturers) {
            out.println(m);
        }
        out.flush();
    }

    @Override
    public List<Souvenir> save(Souvenir souvenir) {
        souvenirList.add(souvenir);
        System.out.println("New souvenir has been added");
        return souvenirList;
    }

    @Override
    public List<Souvenir> edit(List<Souvenir> souvenirs) {
        System.out.println("The souvenir has been edited");
        return souvenirs;
    }

    @Override
    public void geByOneManufacture(Map<Manufacturer, List<Souvenir>> byOneManufacturer) throws FileNotFoundException {
        File f31 = new File("List of SOUVENIR by One Manufacturer.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f31), true));

        System.out.println("List is shown in file (List of SOUVENIR by One Manufacturer.txt)");
        for (Map.Entry<Manufacturer, List<Souvenir>> entry : byOneManufacturer.entrySet()) {
            out.println(entry.getKey() + ": ");
            for (Souvenir s:entry.getValue()){
                out.println(s);
            };
        }
        out.flush();
    }

    @Override
    public void getByOneCountry(Map<String, List<Souvenir>> byCountry) throws FileNotFoundException {

        File f3 = new File("List of SOUVENIR by Country.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f3), true));

        System.out.println("List is shown in file (List of SOUVENIR by Country.txt)");
        for (Map.Entry<String, List<Souvenir>> entry : byCountry.entrySet()) {
            out.println(entry.getKey() + ": ");
            for (Souvenir s:entry.getValue()){
                out.println(s);
            };
        }
        out.flush();
    }

    @Override
    public void getByPriceLess(Map<Manufacturer, List<String>> byPriceLess) throws FileNotFoundException {
        File f4 = new File("List of MANUFACTURE by price less then .txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f4), true));

        System.out.println("List is shown in file (List of MANUFACTURE by price less then .txt)");
        for (Map.Entry<Manufacturer, List<String>> entry : byPriceLess.entrySet()) {
            out.println(entry.getKey() + ": ");
            for (String s : entry.getValue()) {
                out.println(s);
            }
        }
        out.flush();
    }

    @Override
    public void getByManufacturers(Map<Manufacturer, List<Souvenir>> byManufacturer) throws FileNotFoundException {
        File f3 = new File("List of SOUVENIR by Manufacturers.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f3), true));

        System.out.println("List is shown in file (List of SOUVENIR by Manufacturers.txt)");
        for (Map.Entry<Manufacturer, List<Souvenir>> entry : byManufacturer.entrySet()) {
            out.println(entry.getKey() + ": ");
            for (Souvenir s:entry.getValue()){
                out.println(s);
            };
        }
        out.flush();
    }

    public void getByNameAndYear(Map<String, List<String>> byOneYear) throws FileNotFoundException {
        File f6 = new File("List of SOUVENIR by Name and Year.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f6), true));

        System.out.println("List is shown in file (List of SOUVENIR by Name and Year.txt)");
        for (Map.Entry<String, List<String>> entry : byOneYear.entrySet()) {
            out.println(entry.getKey() + ": ");
            for (String s:entry.getValue()){
                out.println(s);
            };

        }
        out.flush();
    }

    @Override
    public void getByYears(Map<Integer, List<Souvenir>> mapByYears) throws FileNotFoundException {
        File f7 = new File("List of SOUVENIR by Years.txt");
        PrintWriter out = new PrintWriter(new PrintWriter(new FileOutputStream(f7), true));

        System.out.println("List is shown in file (List of SOUVENIR by Years.txt)");
        for (Map.Entry<Integer, List<Souvenir>> entry : mapByYears.entrySet()) {
            out.println(entry.getKey() + ": ");
            for (Souvenir s:entry.getValue()){
                out.println(s);
            };

        }
        out.flush();
    }

    @Override
    public List<Souvenir> removeByManufacture(List<Souvenir> souvenirs) {
        System.out.println("Info of manufacture has been removed");
        souvenirList = souvenirs;
        return souvenirs;
    }

    @Override
    public void close() throws Exception {

    }
}
