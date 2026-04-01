import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Loeb ja hoiustab andmeid */

public class AndmeHoidla {
    private List<HoroskoobiKirje> horoskoobiKirjed = new ArrayList<>();
    private String failiNimi = "horoskoobid.csv";

    public void loeFailist() throws FileNotFoundException {
        File fail =  new File(failiNimi);

        try (Scanner scanner = new Scanner(fail, "UTF-8")) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String rida = scanner.nextLine();
                String[] osad = rida.split(";");

                if (osad.length >= 3) {
                    String tähtkuju = osad[0].trim();
                    String kategooria = osad[1].trim();
                    String sõnum = osad[2].trim();

                    horoskoobiKirjed.add(new HoroskoobiKirje(tähtkuju, kategooria, sõnum));
                }
            }
        }
    }
    public List<HoroskoobiKirje> getHoroskoobiKirjed() {
        return horoskoobiKirjed;
    }

    // kui vaja, siis nimekiri kõikidest kategooriatest
    public List<String> getOlemasolevadKategooriad() {
        List<String> kategooriad = new ArrayList<>();
        for (HoroskoobiKirje kirje : horoskoobiKirjed) {
            String kategooria = kirje.getKategooria().toLowerCase();
            if (!kategooriad.contains(kategooria)) {
                kategooriad.add(kategooria);
            }
        }
        return kategooriad;
    }
}
