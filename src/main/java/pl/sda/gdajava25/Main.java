package pl.sda.gdajava25;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                        for (int i = 1; i <= iloscProduktów; i++) {
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
                        System.out.println("Podaj numer zamówienia: ");
                        int numerZamowieniaDostawa = scanner.nextInt();
                        scanner.nextLine();
                        Zamównienie zamównienieDostawa=magazyn.getZamównienieMap().get(numerZamowieniaDostawa);
                        System.out.println("Zamówienie zawiera " +zamównienieDostawa.getProduktList().size()+" produkty");
                        for (Produkt produkt: zamównienieDostawa.getProduktList()) {
                            if (!produkt.isCzyDostarczony()) {
                                System.out.println("Czy w dostawie znajduje się produkt: " + produkt.getNazwa() + ", cena "
                                        + produkt.getCena() + ", ilość " + produkt.getIlosc());
                                String czyZawiera = scanner.nextLine().toLowerCase();
                                if (czyZawiera.equals("tak")) {
                                    produkt.setCzyDostarczony(true);
                                    magazyn.dodajProdukt(produkt);
                                } else {
                                    produkt.setCzyDostarczony(false);
                                    System.out.println("Oznaczam produkt jako niedostarczony");
                                }
                            } else {
                                System.out.println("Produkt dostarczono wcześniej");

                            }
                        }

                        System.out.println("Zamówienie zostało zrealizowane, podaj numer faktury:");
                        String numerfaktury = scanner.nextLine();
                        zamównienieDostawa.setNumerFaktury(numerfaktury);
                        System.out.println("Faktura dopisana do zamówienia");
                        System.out.println("Czy chcesz wprowadzić datę zamówinia ręcznie?");
                        String czyDataRecznie = scanner.nextLine().toLowerCase();
                        if (czyDataRecznie.equals("tak")){
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            System.out.println("Podaj datę dostawy (format yyyy-MM-dd HH:mm):");
                            String dataDostawyString=scanner.nextLine();
                            dataDostawyString+=":00";
                            LocalDateTime dataDostawy = LocalDateTime.parse(dataDostawyString, dateTimeFormatter);
                            zamównienieDostawa.setDataDostarczenia(dataDostawy);
                        }else{
                            zamównienieDostawa.setDataDostarczenia(LocalDateTime.now());
                            System.out.println("Data dostarczenia zaktualizowanę na obecną automatycznie.");
                        }

                        if(zamównienieDostawa.czasDostawy()>60){
                            System.out.println("dostawa przypyła ze spóźnieniem");
                        }


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
                        magazyn.zapiszDoPlikuProduktMap();
                        magazyn.zapiszDoPlikuZamówieniaMap();
                        break;
                    case "wczytaj":
                        magazyn.odczytajZPlikuProduktMap();

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
