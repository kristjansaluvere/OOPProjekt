package OOProjekt;

public class Horoskoop {
    private Kasutaja kasutaja;
    private String sõnum;

    public Horoskoop(Kasutaja kasutaja, String sõnum) {
        this.kasutaja = kasutaja;
        this.sõnum = sõnum;
    }

    public Kasutaja getKasutaja() {
        return kasutaja;
    }

    public String getSõnum() {
        return sõnum;
    }

    // Vastavalt kasutaja meeleolule lisatakse ennustusele sissejuhatus.
    private String lõppvastuseMeeleolu() {
        String meeleolu = kasutaja.getMeeleolu();

        if (meeleolu.equalsIgnoreCase("Rõõmus")) {
            return "Su positiivne meeleolu annab tänasele päevale erilise sära.\n";
        }
        if (meeleolu.equalsIgnoreCase("Õnnetu")) {
            return "Isegi kui päev tundub raske, ei tasu pead norgu lasta.\n";
        }

        // Kui kasutaja valib meeleoluks "Neutraalne" või mitte midagi, siis lõppvastusesse sissejuhatust ei lisata.
        return "";
    }

    // Vormistatakse lõppvastus, mis kuvatakse kasutajale.
    public String getLõppVastus() {
        return kasutaja.getNimi() + ", sinu tänane horoskoop:\n" +
                        "Tähtkuju: " + kasutaja.getTähtkuju() + "\n" +
                        "Meeleolu: " + kasutaja.getMeeleolu() + "\n" +
                        "Kategooria: " + kasutaja.getKategooria() + "\n" +
                        lõppvastuseMeeleolu() +
                        "Ennustus: " + sõnum;

    }

    @Override

    public String toString() {
        return getLõppVastus();
    }
}
