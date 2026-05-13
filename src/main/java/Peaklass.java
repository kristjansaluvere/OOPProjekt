import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Peaklass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AndmeHoidla andmeHoidla = new AndmeHoidla();

        // Kuvab veateate, kui faili, mida avada ei leita.
        try {
            andmeHoidla.loeFailist();
        } catch (IOException e) {
            System.out.println("Viga: faili horoskoobid.csv ei leitud.");
            return;
        }

        // Kuvab veateate, kui avatav fail on tühi.
        if (andmeHoidla.getHoroskoobiKirjed().isEmpty()) {
            System.out.println("Horoskoopide andmed puuduvad või faili sisu on vigane.");
            return;
        }

        System.out.println("Horoskoop: ");

        System.out.print("Sisesta oma nimi: ");
        String nimi = scanner.nextLine().trim();

        kuvaTähtkujud();
        String tähtkuju = küsiTähtkuju(scanner);
        System.out.println();

        int meeleoluValik = küsiMeeleoluValik(scanner);
        String meeleolu = Kasutaja.teisendaMeeleolu(meeleoluValik);

        List<String> kategooriad = andmeHoidla.getOlemasolevadKategooriad();
        kuvaKategooriad(kategooriad);

        String kategooria = küsiKategooria(scanner, kategooriad);

        Kasutaja kasutaja = new Kasutaja(nimi, tähtkuju, meeleolu, kategooria);

        HoroskoobiGenereerija genereerija = new HoroskoobiGenereerija();
        Horoskoop horoskoop = genereerija.genereerija(andmeHoidla.getHoroskoobiKirjed(), kasutaja);

        System.out.println();
        System.out.println("----- Sinu horoskoop -----");
        System.out.println(horoskoop);

        // Ajalugu/eelnevad ennustused
        try {
            andmeHoidla.salvestaAjalugu(kasutaja.getNimi(), horoskoop.getSõnum());
            System.out.println("Horoskoop salvestatud.");
        } catch (IOException e) {
            System.out.println("Viga: ei saanud ajalukku salvestada.");
        }

        System.out.println("\nKas soovite näha varasemaid ennustusi? (jah/ei): ");
        String vastus = scanner.nextLine().trim().toLowerCase();

        if (vastus.equals("jah")) {
            try {
                System.out.println(andmeHoidla.loeAjalugu(kasutaja.getNimi()));
            } catch (IOException e) {
                System.out.println("Viga: ei saanud ajalugu failist lugeda.");
            }
        }
        scanner.close();
    }

    // Tähtkuju valikud
    private static void kuvaTähtkujud() {
        String[] tähtkujud = {"Jäär", "Sõnn", "Kaksikud", "Vähk", "Lõvi", "Neitsi", "Kaalud", "Skorpion",
                "Ambur", "Kaljukits", "Veevalaja", "Kalad"};
        System.out.println("Vali oma tähtkuju:");
        for (int i = 0; i < tähtkujud.length; i++) {
            System.out.println((i+1) + " - " + tähtkujud[i]);
        }
    }

    private static String küsiTähtkuju(Scanner scanner) {
        String[] tähtkujud =  {"Jäär", "Sõnn", "Kaksikud", "Vähk", "Lõvi", "Neitsi", "Kaalud", "Skorpion",
                "Ambur", "Kaljukits", "Veevalaja", "Kalad"};
        while (true) {
            System.out.println("Sisesta valik (1-12): ");
            String sisend = scanner.nextLine().trim();

            try {
                int valik = Integer.parseInt(sisend);
                if (valik >= 1 && valik <= 12) {
                    return tähtkujud[valik - 1];
                } else {
                    System.out.println("Viga: vali õige number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Viga: peab sisestama numbri. Proovi uuesti.");
            }
        }
    }

    // Küsime kasutajalt meeleolu nii kaua, kui sisestatakse õige valik.
    private static int küsiMeeleoluValik(Scanner scanner) {
        while (true) {
            System.out.println("Vali oma tänane meeleolu:");
            System.out.println("1 - Rõõmus");
            System.out.println("2 - Õnnetu");
            System.out.println("3 - Neutraalne");
            System.out.print("Sisesta valik (1-3): ");

            String sisend = scanner.nextLine().trim();

            try {
                int valik = Integer.parseInt(sisend);

                if (valik >= 1 && valik <= 3) {
                    return valik;
                } else {
                    System.out.println("Viga: vali number vahemikus 1 kuni 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Viga: peab sisestama numbri! Proovi uuesti.");
            }
        }
    }

    // Kuvame ekraanile kõik failis olevate ennustuste kategooriad.
    private static void kuvaKategooriad(List<String> kategooriad) {
        System.out.println("Võimalikud kategooriad:");
        for (int i = 0; i < kategooriad.size(); i++) {
            System.out.println((i + 1) + " - " + kategooriad.get(i));
        }
    }

    // Küsime kasutajalt soovitud kategooriat nii kaua, kui sisestatakse õige valik.
    private static String küsiKategooria(Scanner scanner, List<String> kategooriad) {
        while (true) {
            System.out.print("Vali kategooria numbri järgi: ");

            String sisend = scanner.nextLine().trim();

            try {
                int valik = Integer.parseInt(sisend);

                if (valik >= 1 && valik <= kategooriad.size()) {
                    return kategooriad.get(valik - 1);
                } else {
                    System.out.println("Viga: Sellise numbriga kategooriat ei ole nimekirjas.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Viga: sisesta õige kategooria number.");
            }

        }
    }


}