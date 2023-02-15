package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Souvenir {
    String souvenirName;
    Manufacturer manufacturer;
    LocalDate dateOfRelease;
    int price;

    @Override
    public String toString() {
        return "Souvenir: " +
                souvenirName +
                ", " + manufacturer +
                ", Date Of Release: " + dateOfRelease +
                ", Price: " + price + " euro";
    }
}
