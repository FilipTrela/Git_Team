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

    @Override
    public String toString() {
        return "\n#####\nNazwa=" + nazwa + "\n Cena=" + cena +
                "\n Ilosc=" + ilosc + "\n CzyDostarczony=" + czyDostarczony;

    }
}
