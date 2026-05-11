Koos programmiga (ja vajalike lisafailidega) tuleb esitada ka oma rühmatöö kirjeldus, kus peavad olema

    /* Autorite nimed: 
    *  Kristofer Voodla ja Kristjan Saluvere
    */

    /* Programmi eesmärk ja selgitus:
    *  Programm pakub kasutajale meelelahutuslikku ja isikupärastatud horoskoopi,
    *  kombineerides failis hoiustatud ennustused kasutaja valitud kategooria ja tähtkujuga.
    *  Programm käivitub ja loeb failist horoskoobid.csv sisse ennustused.
    *  Seejärel küsitakse kasutajalt nime ja tähtkuju.
    *  Kasutaja valib meeleolu ja kategooria
    *  Programm viib kokku sisendi ning tähtkuju ja kategooria. Seejärel valib juhuslikult ühe ennustuse
    *  Väljastatakse tulemus.
    */

    /* Iga klassi kohta eraldi selle eesmärk ja olulisemad meetodid:
    *  Andmehoidla - vastutab failist andmete lugemise ja nende mälus hoidmise eest 
    *  HoroskoobiGenereerija - Võtab AndmeHoidlast kindla ennustuse kategooria ning valib sealt juhuslikult ühe. Teeb sellest Horoskoop objekti
    *  HoroskoobiKirje - Loob objekti, kus hoiustatakse faili ühte konkreetset ennustust
    *  Horoskoop - Paneb kõik andmed kokku (teeb lõpptulemuse) ehk hoiab valmis vastust
    *  Kasutaja - Hoiab kasutaja andmeid
    *  Peaklass - Kasutajaliides, käivitab programmi
    */

    /* Kasutusjuhis:
    *  Veendu, et kõik failid oleksid olemas. 
    *  Käivita peaklass ning järgi terminalis juhiseid ning seejärel sisesta andmed.
    *  Kui peaks ilmuma veateade, et faili ei leita, siis veendu, et working directory oleks juurkaust mitte src.
    */

    /* Projekti tegemise protsessi kirjeldus (erinevad etapid ja rühmaliikmete osalemine neis):
    *  Kõigepealt mõtlesime idee välja, millele ei kulunud palju aega. Seejärel panime plaani paika ning jaotasime töö ära.
    *  Töö kujunemine:
    *  Kõigepealt loodi Andmehoidla ja HoroskoobiGenereerija. Samuti koos nendega tulid ka andmed.
    *  Seejärel lõime klassid Horoskoop, Kasutaja ning HoroskoobiKirjed ning viimasena panime kõik kokku peaklassis.
    */
   
    /* Iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt):
    *  Kristjan - AndmeHoidla (samuti csv faili andmed), HoroskoobiGenereerija ja 1/2 Horoskoop (~4 tundi)
    *  Kristofer - Kasutaja, Horoskoop, HoroskoobiKirje ja Peaklass (~5 tundi)
    */

    /* Tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust):
    *  Programmi töökindluse tagamine.
    *  Failide töötlemine (sisend/väljund)
    */

    /* Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist):
    *  Klassid on selgelt jaotatud vastutusalade järgi. 
    *  Kasutajaliides töötab hästi ning on interaktiivne kasutajaga.
    *  Kasutusele võiks võtta graafilise kasutajaliidese.
    */

    /* Selgitus ja/või näiteid, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt
    *  Andmehoidla ja HoroskoobiGenereerijat testiti algselt Peaklassis, et veenduda andmete korrektsuses.
    *  Klasse Kasutaja, Horoskoop ning HoroskoobiKirje sai testitud alguses toorete andmetega (ilma failist lugemiseta) ning seejärel kõike koos peaklassis.
    */
