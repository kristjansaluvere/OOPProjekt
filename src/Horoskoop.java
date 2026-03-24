/*
* Ma tegin algse versiooni, et saaksin andmete laadimist testida.
* Oli vaja genereerija klassi jaoks.
* Vaata see üle ja muuda või lisa midagi juurde kui vajalik.
*/

public class Horoskoop {
    private String tähtkuju;
    private String kategooria;
    private String sõnum;

    public Horoskoop(String tähtkuju, String kategooria, String sõnum) {
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
}
