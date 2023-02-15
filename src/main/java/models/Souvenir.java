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
//    int releaseYear;
LocalDate dateOfRelease;
    int price;

    @Override
    public String toString() {
        return "Souvenir: " +
                 souvenirName +
                ", " + manufacturer +
//                ", Year Of Release: " + releaseYear +
                ", Date Of Release: " + dateOfRelease +
                ", Price: " + price+" euro";
    }
}
