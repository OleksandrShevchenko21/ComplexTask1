package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
    String manufacturerName;
    String manufacturerCountry;

    @Override
    public String toString() {
        return
                manufacturerName +
                        "( " + manufacturerCountry + " )";
    }
}
