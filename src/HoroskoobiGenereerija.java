import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Juhusliku horoskoobi valimine */

public class HoroskoobiGenereerija {
    private Random random = new Random();

    public Horoskoop genereerija(List<HoroskoobiKirje> horoskoop, Kasutaja kasutaja) {
        List<HoroskoobiKirje> sobivHoroskoop = new ArrayList<>();

        // Kas tähtkuju ja kategooria klapivad sisendiga
        for (HoroskoobiKirje rida : horoskoop) {
            if (rida.getTähtkuju().equalsIgnoreCase(kasutaja.getTähtkuju()) && rida.getKategooria().equalsIgnoreCase(rida.getKategooria())) {
                sobivHoroskoop.add(rida);
            }
        }

        // Vale sisendi puhul
        if (sobivHoroskoop.isEmpty()) {
            return new Horoskoop(kasutaja,"Kahjuks ei leidnud horoskoopi. Kontrolli sisendit!");
        }

        // Genereerime suvaarvu kaudu isikule suvalise ennustuse.
        int randomIndeks = random.nextInt(sobivHoroskoop.size());
        HoroskoobiKirje suvaEnnustus = sobivHoroskoop.get(randomIndeks);

        return new Horoskoop(kasutaja, suvaEnnustus.getSõnum());
    }
}
