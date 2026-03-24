import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Loeb ja hoiustab andmeid */


// tavaline andmete lugemine failist, nothing fancy
// vajadusel muuta andmete faili?
public class AndmeHoidla {
    private List<Horoskoop> horoskoobid = new ArrayList<>();
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

                    horoskoobid.add(new Horoskoop(tähtkuju, kategooria, sõnum));
                }
            }
        }
    }
    public List<Horoskoop> getHoroskoobid() {
        return horoskoobid;
    }

    // kui vaja, siis nimekiri kõikidest kategooriatest
    public List<String> getOlemasolevadKategooriad() {
        List<String> kategooriad = new ArrayList<>();
        for (Horoskoop h : horoskoobid) {
            String kategooria = h.getKategooria().toLowerCase();
            if (!kategooriad.contains(kategooria)) {
                kategooriad.add(kategooria);
            }
        }
        return kategooriad;
    }
}
