package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Magazyn {
    private Map<String, Produkt> produktMap = new HashMap<>();
    private Map<Integer, Zamównienie> zamównienieMap = new HashMap<>();
    private int numerZamowienia = 0;

    public int dodajZamowienie(Zamównienie zamównienie) {
        zamównienieMap.put(numerZamowienia, zamównienie);
        return numerZamowienia++;
    }

    public void dodajProdukt(Produkt produkt){
        produktMap.put(produkt.getNazwa(),produkt);
    }
}
