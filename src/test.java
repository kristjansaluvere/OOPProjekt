public class test {
    public static void main(String[] args) throws Exception {
        AndmeHoidla hoidla = new AndmeHoidla();
        hoidla.loeFailist();

        HoroskoobiGenereerija g = new HoroskoobiGenereerija();
        Horoskoop h = g.genereerija(hoidla.getHoroskoobid(), "sõnn", "üldine");
        System.out.println(h.getSõnum());
    }
}
