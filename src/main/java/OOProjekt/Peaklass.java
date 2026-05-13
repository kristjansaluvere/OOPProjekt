package OOProjekt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Peaklass extends Application {

    private StackPane juur;
    private AndmeHoidla andmeHoidla;
    private HoroskoobiGenereerija genereerija;
    private TextArea tulemusAla;

    private List<String> ajalooRead = new ArrayList<>();
    private int ajalooIndeks = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage peaLava) {
        andmeHoidla = new AndmeHoidla();
        genereerija = new HoroskoobiGenereerija();

        try {
            andmeHoidla.loeFailist();
        } catch (IOException e) {
            naitaViga("Viga", "Faili horoskoobid.csv ei leitud või seda ei saanud lugeda.");
        }

        juur = new StackPane();
        lisaTaust();

        Scene stseen = new Scene(juur, 800, 600);

        kuvaAlgusVaade();

        stseen.setOnKeyPressed(event -> kuvaPohiVaade());
        juur.setOnMouseClicked(event -> kuvaPohiVaade());

        peaLava.setTitle("Horoskoobi generaator");
        peaLava.setScene(stseen);
        peaLava.setMinWidth(800);
        peaLava.setMinHeight(600);

        peaLava.widthProperty().addListener((obs, vana, uus) -> {
            peaLava.setHeight(uus.doubleValue() * 600 / 800);
        });
        peaLava.show();
    }

    private void lisaTaust() {
        juur.setBackground(new Background(
                new BackgroundFill(Color.rgb(43, 43, 43), null, null)
        ));

        URL pildiUrl = getClass().getResource("/nõid2.png");

        if (pildiUrl != null) {
            Image pilt = new Image(pildiUrl.toExternalForm());
            ImageView taust = new ImageView(pilt);

            taust.setPreserveRatio(true);

            juur.widthProperty().addListener((obs, vana, uus) -> muudaTaustaSuurust(taust, pilt));
            juur.heightProperty().addListener((obs, vana, uus) -> muudaTaustaSuurust(taust, pilt));

            juur.getChildren().add(taust);
        }
    }

    private void kuvaAlgusVaade() {
        juur.getChildren().removeIf(node -> !(node instanceof ImageView)); // Eemaldab vaatest vana valge kasti

        VBox kast = looValgeKast();
        kast.setMaxWidth(500);

        Label pealkiri = new Label("Horoskoobi generaator");
        pealkiri.setFont(Font.font(34));

        Label juhis = new Label("Vajutage suvalist klahvi, et alustada");
        juhis.setFont(Font.font(16));

        kast.getChildren().addAll(pealkiri, juhis);
        juur.getChildren().add(kast);
    }

    private void kuvaPohiVaade() {
        juur.setOnMouseClicked(null);

        juur.getChildren().removeIf(node -> !(node instanceof ImageView)); // Eemaldab vaatest vana valge kasti

        VBox kast = looValgeKast();
        kast.setMaxWidth(560);

        Label pealkiri = new Label("Horoskoobi generaator");
        pealkiri.setFont(Font.font(28));

        TextField nimiVali = new TextField();
        nimiVali.setPromptText("Sisesta oma nimi");

        ComboBox<String> tähtkujuValik = new ComboBox<>();
        tähtkujuValik.getItems().addAll(
                "Jäär", "Sõnn", "Kaksikud", "Vähk", "Lõvi", "Neitsi",
                "Kaalud", "Skorpion", "Ambur", "Kaljukits", "Veevalaja", "Kalad"
        );
        tähtkujuValik.setPromptText("Vali tähtkuju");

        ComboBox<String> meeleoluValik = new ComboBox<>();
        meeleoluValik.getItems().addAll("Rõõmus", "Õnnetu", "Neutraalne");
        meeleoluValik.setPromptText("Vali meeleolu");

        ComboBox<String> kategooriaValik = new ComboBox<>();
        kategooriaValik.getItems().addAll(andmeHoidla.getOlemasolevadKategooriad());
        kategooriaValik.setPromptText("Vali kategooria");

        Button genereeriNupp = new Button("Genereeri horoskoop");
        Button ajaluguNupp = new Button("Vaata ajalugu");

        tulemusAla = new TextArea();
        tulemusAla.setEditable(false);
        tulemusAla.setWrapText(true);
        tulemusAla.setPrefHeight(170);
        tulemusAla.setPromptText("Sinu horoskoop kuvatakse siia.");

        genereeriNupp.setOnAction(event -> {
            String nimi = nimiVali.getText().trim();
            String tähtkuju = tähtkujuValik.getValue();
            String meeleolu = meeleoluValik.getValue();
            String kategooria = kategooriaValik.getValue();

            if (nimi.isEmpty() || tähtkuju == null || meeleolu == null || kategooria == null) {
                naitaViga("Puudulik sisend", "Palun täida kõik väljad.");
                return;
            }

            Kasutaja kasutaja = new Kasutaja(nimi, tähtkuju, meeleolu, kategooria);
            Horoskoop horoskoop = genereerija.genereerija(andmeHoidla.getHoroskoobiKirjed(), kasutaja);

            tulemusAla.setText(horoskoop.toString());

            try {
                andmeHoidla.salvestaAjalugu(kasutaja.getNimi(), horoskoop.getSõnum());
            } catch (IOException e) {
                naitaViga("Salvestamise viga", "Horoskoopi ei saanud ajalukku salvestada.");
            }
        });

        ajaluguNupp.setOnAction(event -> {
            String nimi = nimiVali.getText().trim();

            if (nimi.isEmpty()) {
                naitaViga("Puudulik sisend", "Ajaloo vaatamiseks sisesta esmalt nimi.");
                return;
            }

            kuvaAjalugu(nimi);
        });

        HBox nupud = new HBox(10, genereeriNupp, ajaluguNupp);
        nupud.setAlignment(Pos.CENTER);

        kast.getChildren().addAll(pealkiri, nimiVali, tähtkujuValik, meeleoluValik, kategooriaValik, nupud, tulemusAla);

        juur.getChildren().add(kast);
    }

    private void kuvaAjalugu(String nimi) {
        try {
            String ajalugu = andmeHoidla.loeAjalugu(nimi);

            ajalooRead.clear();

            if (ajalugu.startsWith("Kasutajal") || ajalugu.startsWith("Ajalugu on tühi")) {
                tulemusAla.setText(ajalugu);
                return;
            }

            String[] read = ajalugu.split("\n");

            for (String rida : read) {
                if (!rida.trim().isEmpty()) {
                    ajalooRead.add(rida.trim());
                }
            }

            if (ajalooRead.isEmpty()) {
                tulemusAla.setText("Ajalugu puudub.");
                return;
            }

            ajalooIndeks = 0;
            kuvaAjalooKirje();

        } catch (IOException e) {
            naitaViga("Lugemise viga", "Ajalugu ei saanud failist lugeda.");
        }
    }

    private void muudaTaustaSuurust(ImageView taust, Image pilt) {
        double aknaLaius = juur.getWidth();
        double aknaKõrgus = juur.getHeight();

        if (aknaLaius / aknaKõrgus > pilt.getWidth() / pilt.getHeight()) {
            taust.setFitWidth(aknaLaius);
            taust.setFitHeight(0);
        } else {
            taust.setFitHeight(aknaKõrgus);
            taust.setFitWidth(0);
        }
    }

    private void kuvaAjalooKirje() {
        juur.getChildren().removeIf(node -> !(node instanceof ImageView));

        VBox kast = looValgeKast();
        kast.setMaxWidth(620);

        Label pealkiri = new Label("Varasemad horoskoobid");
        pealkiri.setFont(Font.font(26));

        TextArea ajalooAla = new TextArea();
        ajalooAla.setEditable(false);
        ajalooAla.setWrapText(true);
        ajalooAla.setPrefHeight(180);
        ajalooAla.setText(ajalooRead.get(ajalooIndeks));

        Label loendur = new Label((ajalooIndeks + 1) + " / " + ajalooRead.size());
        loendur.setFont(Font.font(15));

        Button eelmine = new Button("Eelmine");
        Button järgmine = new Button("Järgmine");
        Button tagasi = new Button("Tagasi");

        eelmine.setDisable(ajalooIndeks == 0);
        järgmine.setDisable(ajalooIndeks == ajalooRead.size() - 1);

        eelmine.setOnAction(event -> {
            ajalooIndeks--;
            kuvaAjalooKirje();
        });

        järgmine.setOnAction(event -> {
            ajalooIndeks++;
            kuvaAjalooKirje();
        });

        tagasi.setOnAction(event -> kuvaPohiVaade());

        HBox nupud = new HBox(10, eelmine, järgmine, tagasi);
        nupud.setAlignment(Pos.CENTER);

        kast.getChildren().addAll(pealkiri, ajalooAla, loendur, nupud);
        juur.getChildren().add(kast);
    }

    private VBox looValgeKast() {
        VBox kast = new VBox(12);
        kast.setAlignment(Pos.CENTER);

        // Padding
        kast.setPadding(new Insets(22, 28, 22, 28));

        // Takistab aknal venimast
        kast.setMaxHeight(Region.USE_PREF_SIZE);

        kast.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(180, 180, 180, 0.72),
                        new CornerRadii(12),
                        null
                )
        ));

        kast.setBorder(new Border(new BorderStroke(
                Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2)
        )));

        return kast;
    }

    private void naitaViga(String pealkiri, String sisu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(pealkiri);
        alert.setHeaderText(null);
        alert.setContentText(sisu);
        alert.showAndWait();
    }
}