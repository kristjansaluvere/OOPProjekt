
/* Autorite nimed:
*  Kristofer Voodla ja Kristjan Saluvere
*/


/* Programmi eesmärk ja selgitus:
*  Programm pakub kasutajale meelelahutuslikku ja isikupärastatud horoskoopi,
*  kombineerides failis hoiustatud ennustused kasutaja valitud kategooria ja tähtkujuga.
*  Programm ja GUI käivitub ning loeb failist horoskoobid.csv sisse ennustused.
*  Seejärel küsitakse kasutajalt nime, tähtkuju, meeleolu ja soovitud kategooriat.
*  Programm viib kokku kasutaja sisendi ning valib vastavast kategooriast juhuslikult ühe ennustuse.
*  Lõpuks väljastatakse isikupärastatud tulemus ning salvestatakse ajalugu eraldi faili.
*/

/* Iga klassi kohta eraldi selle eesmärk ja olulisemad meetodid:
*  Andmehoidla - vastutab failist andmete lugemise ja nende mälus hoidmise eest. Ennustuste ajaloo salvestamine.
*  HoroskoobiGenereerija - Võtab AndmeHoidlast kindla ennustuse kategooria ning valib sealt juhuslikult ühe. Teeb sellest Horoskoop objekti
*  HoroskoobiKirje - Loob objekti, kus hoiustatakse faili ühte konkreetset ennustust
*  Horoskoop - Paneb kõik andmed kokku (teeb lõpptulemuse) ehk hoiab valmis vastust
*  Kasutaja - Hoiab kasutaja andmeid
*  Peaklass - Kasutajaliides, käivitab programmi
*/

/* Kasutusjuhis:
*  Veendu, et kõik failid oleksid olemas.
*  Käivita läbi Gradle'i programm.
*  Pärast käivitamist avangeb GUI. Järgi juhiseid, tee oma valikud ja programm väljastab sulle isikupärastatud horoskoobi
*/


/* Projekti tegemise protsessi kirjeldus (erinevad etapid ja rühmaliikmete osalemine neis):
*  Kõigepealt mõtlesime idee välja, millele ei kulunud palju aega. Seejärel panime plaani paika ning jaotasime töö ära.
*  Töö kujunemine:
*  Kõigepealt loodi Andmehoidla ja HoroskoobiGenereerija. Samuti koos nendega tulid ka andmed.
*  Seejärel lõime klassid Horoskoop, Kasutaja ning HoroskoobiKirjed ning viimasena panime kõik kokku peaklassis.
*  Teises etapis kõigepealt lisati failide erinditöötlus ning andmete ajaloo fail.
*  Seejärel pandi kokku kogu algne kood JavaFX Gradle'iga ning lisati GUI.
*/


/* Iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt):
*  Kristjan - Esimeses etapis AndmeHoidla (samuti csv faili andmed), HoroskoobiGenereerija ja 1/2 Horoskoop.
*  Teises etapis erinditöötlus ja ajaloo faili kirjutamise loogika (~7 tundi).
*  Kristofer - Esimese etapis Kasutaja, Horoskoop, HoroskoobiKirje ja Peaklass.
*  Teises etapis graaflise kasutajaliidese loomine ja integreerimine varasema koodiga. (~ tundi).
*/

/* Tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust):
*  Programmi töökindluse tagamine.
*  Failide töötlemine (sisend/väljund)
*  JavaFX ja Gradle'i keskkonna seadistamine ning esimese etapis kirjutatud koodi ühendamine GUI-ga.
*/

/* Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist):
*  Klassid on selgelt jaotatud vastutusalade järgi.
*  Kasutajaliides töötab hästi ning on interaktiivne kasutajaga.
*  Saadi hästi hakkama graafilise liidese loomisega
*  1. etapi koodi kombineerimine uue GUI ja failikirjutamise loogikaga õnnestus.
*/

/* Selgitus ja/või näiteid, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt
*  Andmehoidla ja HoroskoobiGenereerijat testiti algselt Peaklassis, et veenduda andmete korrektsuses.
*  Klasse Kasutaja, Horoskoop ning HoroskoobiKirje sai testitud alguses toorete andmetega (ilma failist lugemiseta) ning seejärel kõike koos peaklassis.
*  Lõpuks integreeriti kõik osad graafilisse liidesesse ja testiti programmi tervikuna, proovides läbi erinevaid sisendeid ning jälgides programmi reageerimist.
*/