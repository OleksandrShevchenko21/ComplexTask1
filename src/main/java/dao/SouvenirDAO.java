package dao;

import models.Manufacturer;
import models.Souvenir;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


public interface SouvenirDAO {
    public List<Souvenir> create();

    public List<Souvenir> save(Souvenir souvenir);

    public List<Souvenir> edit(List<Souvenir> souvenirs);

    public void show() throws FileNotFoundException;

    void showManufactures(List<Manufacturer>manufacturers) throws FileNotFoundException;

    void geByOneManufacture(Map<Manufacturer, List<Souvenir>> byOneManufacturer) throws FileNotFoundException;

    void getByManufacturers(Map<Manufacturer, List<Souvenir>> byManufacturer) throws FileNotFoundException;

    void getByOneCountry(Map<String, List<Souvenir>> byCountry) throws FileNotFoundException;

    void getByPriceLess(Map<Manufacturer, List<String>> byPriceLess) throws FileNotFoundException;

    void getByNameAndYear(Map<String, List<String>> byOneYear) throws FileNotFoundException;

    void close() throws Exception;

    void getByYears(Map<Integer, List<Souvenir>> mapByYears) throws FileNotFoundException;

    public List<Souvenir> removeByManufacture(List<Souvenir> souvenirs);

}
