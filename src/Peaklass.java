import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Peaklass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AndmeHoidla andmeHoidla = new AndmeHoidla();

        // Kuvab veateate, kui faili, mida avada ei leita.
        try {
            andmeHoidla.loeFailist();
        } catch (FileNotFoundException e) {
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

        System.out.print("Sisesta oma tähtkuju: ");
        String tähtkuju = scanner.nextLine().trim();

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
    }

    // Küsime kasutajalt meeleolu nii kaua, kui sisestatakse õige valik.
    private static int küsiMeeleoluValik(Scanner scanner) {
        while (true) {
            System.out.println("Vali oma tänane meeleolu:");
            System.out.println("1 - Rõõmus");
            System.out.println("2 - Õnnetu");
            System.out.println("3 - Neutraalne");
            System.out.print("Sisesta valik (1-3): ");

            if (scanner.hasNextInt()) {
                int valik = scanner.nextInt();
                scanner.nextLine();

                if (valik >= 1 && valik <= 3) {
                    return valik;
                }
            } else {
                scanner.nextLine();
            }

            // Kui kasutaja sisestab vale valiku, siis kuvame teate.
            System.out.println("Vigane valik. Proovi uuesti.");
            System.out.println();
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

            if (scanner.hasNextInt()) {
                int valik = scanner.nextInt();
                scanner.nextLine();

                if (valik >= 1 && valik <= kategooriad.size()) {
                    return kategooriad.get(valik - 1);
                }
            } else {
                scanner.nextLine();
            }

            System.out.println("Vigane kategooria valik. Proovi uuesti.");
        }
    }
}