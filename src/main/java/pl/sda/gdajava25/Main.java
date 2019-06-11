package pl.sda.gdajava25;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aplikacja zamówienia");
       Zamównienie zamównienie = new Zamównienie();


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

                        break;
                    case "dodaj dostawę":

                        break;
                    case "listuj zamówienia":

                        break;
                    case "listuj dostawy":

                        break;
                    case "listuj produkty":

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
