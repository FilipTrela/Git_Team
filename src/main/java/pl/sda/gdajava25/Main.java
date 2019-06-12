package pl.sda.gdajava25;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aplikacja zamówienia");
        Magazyn magazyn = new Magazyn();

        String komenda;
        do {
            System.out.println("Podaj komendę:");
            System.out.println(" możliwe opcje: ||dodaj zamówienie|| ||dodaj dostawę|| ||listuj zamówienia|| ||listuj dostawy|| ||listuj produkty|| ||zapisz|| ||wczytaj||");
            komenda = scanner.nextLine();
            komenda.toLowerCase();

            if (komenda.equalsIgnoreCase("quit")) {
                break;
            }

            try {
                switch (komenda) {
                    case "dodaj zamówienie":
                        Zamównienie zamównienie = new Zamównienie();
                        List<Produkt> produktList = new ArrayList<>();
                        System.out.println("Podaj ilość produktów na zamównieniu");
                        int iloscProduktów = Integer.parseInt(scanner.nextLine());
                        for (int i = 1; i >= iloscProduktów; i++) {
                            Produkt produkt = new Produkt();
                            produkt.setCzyDostarczony(false);
                            System.out.println("Podaj nazwe produktu " + i);
                            produkt.setNazwa(scanner.nextLine());
                            System.out.println("Podaj cene produktu " + i);
                            produkt.setCena(Double.parseDouble(scanner.nextLine()));
                            System.out.println("Podaj ilość produktu " + i);
                            produkt.setIlosc(Integer.parseInt(scanner.nextLine()));
                            produktList.add(produkt);
                        }
                        zamównienie.setProduktList(produktList);
                        zamównienie.setDataZamowienia(LocalDateTime.now());
                        int numerZamowienia = magazyn.dodajZamowienie(zamównienie);
                        System.out.println("Zamówienie złożone. Numer zamówienia : " + numerZamowienia);
                        break;
                    case "dodaj dostawę":

                        break;
                    case "listuj zamówienia":
                        magazyn.listujZamówienia();

                        break;
                    case "listuj dostawy":
                        magazyn.listujDostawy();

                        break;
                    case "listuj produkty":
                        magazyn.listujProdukty();
                        break;
                    case "zapisz":


                        break;
                    case "wczytaj":

                        break;
                    default:
                        continue;
                }


            } catch (IllegalArgumentException iae) {
                System.err.println("Błąd, nie ma takiej komendy");
                continue;
            }
        } while (!komenda.equalsIgnoreCase("quit"));


    }
}
