package services;

import dao.SouvenirDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Manufacturer;
import models.Souvenir;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SouvenirService {
    private SouvenirDAO souvenirDAO;

    public List<Souvenir> createSouvenirService() {
        return souvenirDAO.create();
    }

    public void showListSouvenirService() throws FileNotFoundException {
        souvenirDAO.show();
    }

    public void showListManufacturerService(List<Manufacturer>manufactures) throws FileNotFoundException {
        souvenirDAO.showManufactures(manufactures);

    }

    public List<Souvenir> saveSouvenirService(Souvenir souvenir) {
        return souvenirDAO.save(souvenir);
    }

    public List<Souvenir> editSouvenirService(List<Souvenir> souvenirs) {
        return souvenirDAO.edit(souvenirs);
    }

    public void getByOneManufacturerService(Map<Manufacturer, List<Souvenir>> byOneManufacturer) throws FileNotFoundException {
        souvenirDAO.geByOneManufacture(byOneManufacturer);
    }

    public void getByOneCountryService(Map<String, List<Souvenir>> byCountry) throws FileNotFoundException {
        souvenirDAO.getByOneCountry(byCountry);
    }

    public void getSouvenirByPriceLessThenService(Map<Manufacturer, List<String>> byPriceLess) throws FileNotFoundException {
        souvenirDAO.getByPriceLess(byPriceLess);
    }

    public void getByManufacturersService(Map<Manufacturer, List<Souvenir>> byManufacturer) throws FileNotFoundException {
        souvenirDAO.getByManufacturers(byManufacturer);
    }


    public void getByNameAndYearService(Map<String, List<String>> byOneYear) throws FileNotFoundException {
        souvenirDAO.getByNameAndYear(byOneYear);
    }

    public void getByYearsService(Map<Integer, List<Souvenir>> mapByYears) throws FileNotFoundException {
        souvenirDAO.getByYears(mapByYears);
    }

    public List<Souvenir> removeSouvenirsByManufactureService(List<Souvenir> souvenirs) {
        return souvenirDAO.removeByManufacture(souvenirs);
    }

}
