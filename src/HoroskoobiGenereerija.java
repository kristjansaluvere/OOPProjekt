import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Juhusliku horoskoobi valimine */

public class HoroskoobiGenereerija {
    private Random random = new Random();

    public Horoskoop genereerija(List<Horoskoop> horoskoobid, String kasutajaTähtkuju, String kasutajaKategooria) {
        List<Horoskoop> sobivHoroskoop = new ArrayList<>();

        // kas tähtkuju ja kategooria klapivad sisendiga
        for (Horoskoop h : horoskoobid) {
            if (h.getTähtkuju().equalsIgnoreCase(kasutajaTähtkuju) && h.getKategooria().equalsIgnoreCase(kasutajaKategooria)) {
                sobivHoroskoop.add(h);
            }
        }

        // vale sisendi puhul
        if (sobivHoroskoop.isEmpty()) {
            return new Horoskoop(kasutajaTähtkuju, kasutajaKategooria, "Kahjuks ei leidnud horoskoopi. Kontrolli sisendit!");
        }
        int randomIndeks = random.nextInt(sobivHoroskoop.size());
        return sobivHoroskoop.get(randomIndeks);
    }
}
