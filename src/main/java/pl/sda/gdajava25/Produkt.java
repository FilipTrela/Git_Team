package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produkt {
    private String nazwa;
    private double cena;
    private int ilosc;
    private boolean  czyDostarczony;

}
