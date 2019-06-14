package pl.sda.gdajava25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public void dodajProdukt (Produkt produkt){
        produktMap.put(produkt.getNazwa(),produkt);
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
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String czas = dateTimeFormatter.format(zamównienie.getDataZamowienia());
                System.out.println("Data złożenia zamówienia : " + czas);
                List<Produkt> produktList = zamównienie.getProduktList();
                System.out.println("Ilość zamówionych produktów : " + produktList.size());
                System.out.print("Zamówione produkty : ");
                for (Produkt produkt : produktList) {
                    System.out.print(produkt.getNazwa() + ",");
                }
                System.out.println();
            }
        }
    }

    public void listujDostawy() {
        Set<Integer> keySet = zamównienieMap.keySet();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
                    System.out.println("Zamówienie opóźnione o " + (czasDostawy - 60) + " sekund");
                } else {
                    System.out.println("Zamówienie zrealizowane na czas.");
                }
            }
        }

    }

    public void listujProdukty() {
        Set<String> keys = produktMap.keySet();
        System.out.println("--------------------------");
        if (keys.size()==0) {
            System.out.println("Nie ma żadnnych produktów w magazynie.");
        } else {
            for (String klucz : keys) {
                System.out.println("Produkt " + produktMap.get(klucz).getNazwa() + ". Ilość : " + produktMap.get(klucz).getIlosc());
            }
        }
    }

    public void zapiszDoPlikuProduktMap() {
        Set<String> keySetProdukt = produktMap.keySet();
        try (PrintWriter printWriter = new PrintWriter("Produkt_Map.txt")) {
            for (String klucz : keySetProdukt) {
                Produkt produkt = produktMap.get(klucz);
                printWriter.println(produkt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void odczytajZPlikuProduktMap(){

        File plik = new File("Produkt_Map.txt");
        if (plik.exists()) {

            try (Scanner scanner = new Scanner(new FileReader("Produkt_Map.txt"));){

                if (!scanner.hasNextLine()) {
                    System.out.println("brak treści");
                }
                scanner.nextLine();

                while (scanner.hasNextLine()) {
                    Produkt produkt = new Produkt();
                    scanner.nextLine();

                    for (int i = 0; i < 4; i++) {
                        String line = scanner.nextLine();
                        String[] lineTab = line.split("=");
                        switch (lineTab[0]) {
                            case "Nazwa":
                                produkt.setNazwa((lineTab[1]));
                                break;
                            case "Cena":
                                produkt.setCena(Double.parseDouble(lineTab[1]));
                                break;
                            case "Ilosc":
                                produkt.setIlosc(Integer.parseInt(lineTab[1]));
                                break;
                            case "CzyDostarczony":
                                produkt.setCzyDostarczony(Boolean.parseBoolean(lineTab[1]));
                                break;
                            default:
                                continue;
                        }
                    }
                    this.dodajProdukt(produkt);
                }

            } catch (FileNotFoundException e) {
                e.getLocalizedMessage();
            }
        } else {
            System.out.println("brak pliku");
        }


    }

    public void zapiszDoPlikuZamówieniaMap() {
        Set<Integer> keySetZamowienie = zamównienieMap.keySet();
        try (PrintWriter printWriter = new PrintWriter("Zamowienia_Map.txt")) {
            for (Integer klucz : keySetZamowienie) {
                Zamównienie zamównienie = zamównienieMap.get(klucz);
                printWriter.println(zamównienie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

  /*  public void odczytajZPlikuZamowiniaMap(){

        File plik = new File("Zamowienia_Map.txt");
        if (plik.exists()) {

            try (Scanner scanner = new Scanner(new FileReader("Zamowienia_Map.txt"));){

                if (!scanner.hasNextLine()) {
                    System.out.println("brak treści");
                }

                while (scanner.hasNextLine()) {
                    Produkt produkt = new Produkt();
                    scanner.nextLine();

                    for (int i = 0; i < 4; i++) {
                        String line = scanner.nextLine();
                        String[] lineTab = line.split("=");
                        switch (lineTab[0]) {
                            case "Nazwa":
                                produkt.setNazwa((lineTab[1]));
                                break;
                            case "Cena":
                                produkt.setCena(Double.parseDouble(lineTab[1]));
                                break;
                            case "Ilosc":
                                produkt.setIlosc(Integer.parseInt(lineTab[1]));
                                break;
                            case "CzyDostarczony":
                                produkt.setCzyDostarczony(Boolean.parseBoolean(lineTab[1]));
                                break;
                            default:
                                continue;
                        }
                    }
                    this.dodajProdukt(produkt);
                }

            } catch (FileNotFoundException e) {
                e.getLocalizedMessage();
            }
        } else {
            System.out.println("brak pliku");
        }


    }*/
}
