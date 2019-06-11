package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Magazyn {
    Map<String,Produkt> produktMap;
    Map<Integer,Zamównienie> zamównienieMap;
}
