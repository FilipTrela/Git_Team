package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Zamównienie {
    private int numer;
    private List<Produkt> produktList;
    private LocalDateTime dataZamowienia;
    private LocalDateTime dataDostarczenia = null;
    private String numerFaktury;

    public long czasDostawy(){
        Duration duration = Duration.between(dataZamowienia,dataDostarczenia);
        return duration.getSeconds();
    }

    @Override
    public String toString() {
        return "\n^%^%^%\nNumer=" + numer + "\n DataZamowienia=" + dataZamowienia +
                "\n DataDostarczenia=" + dataDostarczenia + "\n NumerFaktury=" + numerFaktury;
    }
}
