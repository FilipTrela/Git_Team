package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Zamównienie {
    private int numer;
    private List<Produkt> produktList;
    private LocalDateTime dataZamowienia;
    private LocalDateTime dataDostarczenia;
    private String numerFaktury;
}
