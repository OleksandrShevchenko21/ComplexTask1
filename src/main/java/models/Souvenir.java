package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Souvenir {
    String souvenirName;
    Manufacturer manufacturer;
    int releaseYear;
    int price;

    @Override
    public String toString() {
        return "Souvenir: " +
                 souvenirName +
                ", " + manufacturer +
                ", Year Of Release: " + releaseYear +
                ", Price: " + price+" euro";
    }
}
