package OOProjekt;

public class Kasutaja {
    private String nimi;
    private String tähtkuju;
    private String meeleolu;
    private String kategooria;

    // Konstruktor
    public Kasutaja(String nimi, String tähtkuju, String meeleolu, String kategooria) {
        this.nimi = nimi;
        this.tähtkuju = tähtkuju;
        this.meeleolu = meeleolu;
        this.kategooria = kategooria;
    }

    // Kui kasutaja sisestab meeleolu järjekorranumbri, siis programm töötab ikka.
    public static String teisendaMeeleolu(int valik) {
        switch (valik) {
            case 1:
                return "Rõõmus";
            case 2:
                return "Õnnetu";
            case 3:
                return "Neutraalne";
            default:
                return "Neutraalne";
        }
    }

    public String getNimi() {
        return nimi;
    }

    public String getTähtkuju() {
        return tähtkuju;
    }

    public String getMeeleolu() {
        return meeleolu;
    }

    public String getKategooria() {
        return kategooria;
    }
}