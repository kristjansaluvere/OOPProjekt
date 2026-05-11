import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Loeb ja hoiustab andmeid */

public class AndmeHoidla {
    private List<HoroskoobiKirje> horoskoobiKirjed = new ArrayList<>();
    private String failiNimi = "horoskoobid.csv";

    public void loeFailist() throws IOException {
        File fail =  new File(failiNimi);

        // Vahetasin Scanneri BufferedReaderi vastu
        try (BufferedReader br = new BufferedReader(new FileReader(fail, StandardCharsets.UTF_8))) {
            String rida = br.readLine();
            while ((rida = br.readLine()) != null) {
                if (rida.trim().isEmpty()) {
                    continue;
                }
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

    // Kui vaja, siis nimekiri kõikidest kategooriatest
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

    // Ennustuste ajalugu
    private String ajaluguFail = "ajalugu.txt";

    public void salvestaAjalugu(String nimi, String ennustus) throws IOException {
        LocalDateTime aeg = LocalDateTime.now();
        DateTimeFormatter formaat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String t = aeg.format(formaat);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ajaluguFail, StandardCharsets.UTF_8, true))){
            bw.write(nimi + ": [" + t + "] " + ennustus);
            bw.newLine();
        }
    }

    // Tagastab konkreetse kasutaja ennustused
    public String loeAjalugu(String nimi) throws IOException {
        File fail = new File(ajaluguFail);
        if (!fail.exists()) {
            return "Ajalugu on tühi.";
        }
        StringBuilder sisu = new StringBuilder();
        boolean leitud = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fail , StandardCharsets.UTF_8))){
            String rida;
            String f = nimi + ":"; // Kindla kasutaja ennustuse otsimiseks
            while ((rida = br.readLine()) != null) {
                // Õige nime kontroll
                if (rida.startsWith(f)) {
                    String r = rida.replace(f + " ", "");
                    sisu.append("- ").append(r).append("\n");
                    leitud = true;
                }

            }
        }
        if (!leitud) {
            return "Kasutajal nimega '" + nimi + "' puudub varasem ajalugu.";
        }
        return sisu.toString();
    }
}
