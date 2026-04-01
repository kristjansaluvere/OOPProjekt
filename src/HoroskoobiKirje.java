/* Loob objekti, kus hoiustatakse faili ühte konkreetset ennustust.*/

public class HoroskoobiKirje {
    private String tähtkuju;
    private String kategooria;
    private String sõnum;

    // Konstuktor
    public HoroskoobiKirje(String tähtkuju, String kategooria, String sõnum) {
        this.tähtkuju = tähtkuju;
        this.kategooria = kategooria;
        this.sõnum = sõnum;
    }

    public String getTähtkuju() {
        return tähtkuju;
    }

    public String getKategooria() {
        return kategooria;
    }

    public String getSõnum() {
        return sõnum;
    }

    // Väljastab CSV failis kuvatud ennustuse loetaval kujul ühe rea kaupa.
    @Override
    public String toString() {
        return "HoroskoobiKirje{" +
                "tähtkuju='" + tähtkuju + '\'' +
                ", kategooria='" + kategooria + '\'' +
                ", sõnum='" + sõnum + '\'' +
                '}';
    }
}