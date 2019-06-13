package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Magazyn {
    private Map<String, Produkt> produktMap = new HashMap<>();
    private Map<Integer, Zamównienie> zamównienieMap = new HashMap<>();
    private int numerZamowienia = 001;

    public int dodajZamowienie(Zamównienie zamównienie) {
        zamównienieMap.put(numerZamowienia, zamównienie);
        return numerZamowienia++;
    }

    public Zamównienie pobierzZamowienie(int numerZamowienia) {
        return zamównienieMap.get(numerZamowienia);
    }

    public void listujZamówienia() {
        Set<Integer> keySet = zamównienieMap.keySet();
        System.out.println("Zamówienia niezrealizowane : ");
        for (Integer key : keySet) {
            Zamównienie zamównienie = zamównienieMap.get(key);
            if (zamównienie.getDataDostarczenia() == null) {
                System.out.println("--------------------------");
                System.out.println("Zamówienie numer : " + key);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String czas = dateTimeFormatter.format(zamównienie.getDataZamowienia());
                System.out.println("Data złożenia zamówienia : "+czas);
                List<Produkt> produktList = zamównienie.getProduktList();
                System.out.println("Ilość zamówionych produktów : "+produktList.size());
                System.out.print("Zamówione produkty : ");
                for(Produkt produkt : produktList){
                    System.out.print(produkt.getNazwa()+",");
                }
                System.out.println();
            }
        }
    }

    public void listujDostawy() {
        Set<Integer> keySet = zamównienieMap.keySet();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Zamówienia zrealizowane : ");
        for (Integer key : keySet) {
            Zamównienie zamównienie = zamównienieMap.get(key);
            if (zamównienie.getDataDostarczenia() != null) {
                System.out.println("-------------------------");
                System.out.println("Zamówienie numer : " + key);
                System.out.println("Faktura numer : " + zamównienie.getNumerFaktury());
                System.out.println("Data zamówienia : " + dateTimeFormatter.format(zamównienie.getDataZamowienia()));
                List<Produkt> produktList = zamównienie.getProduktList();
                int iloscProduktowDostarczonych = 0;
                for (Produkt produkt : produktList) {
                    if (produkt.isCzyDostarczony()) {
                        iloscProduktowDostarczonych++;
                    }
                }
                System.out.println("Ilość produktów dostarczonych : " + iloscProduktowDostarczonych);
                long czasDostawy = zamównienie.czasDostawy();
                if (czasDostawy > 60) {
                    System.out.println("Zamówienie opóźnione o " + (czasDostawy - 60) +" sekund");
                } else {
                    System.out.println("Zamówienie zrealizowane na czas.");
                }
            }
        }

    }

    public void listujProdukty(){
       Set<String> keys =  produktMap.keySet();
       for(String klucz : keys){
           System.out.println("Produkt "+produktMap.get(klucz).getNazwa()+". Ilość : "+produktMap.get(klucz).getIlosc());
       }
    }

    public void zapiszDoPlikuProduktMap() {
        Set<String> keySetProdukt = produktMap.keySet();
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("Produkt_Map.txt", false))) {
            for(String klucz : keySetProdukt){
              Produkt produkt = produktMap.get(klucz);
              printWriter.println(produkt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zapiszDoPlikuZamówieniaMap(){
        Set<Integer> keySetZamowienie = zamównienieMap.keySet();
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("Zamowienia_Map.txt", false))) {
            for(Integer klucz : keySetZamowienie){
               Zamównienie zamównienie = zamównienieMap.get(klucz);
               printWriter.println(zamównienie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
