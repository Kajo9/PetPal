# MyPetPal

Android aplikacija za lakšu brigu o kućnom ljubimcu, s elementom igre u stilu Tamagotchija.

Projekt je izrađen u timu na kolegiju RMAI (FOI). Ovaj repositorij je **ponovni upload** koda na novi GitHub račun. Originalna povijest commitova nije sačuvana jer nije bilo pristupa starom računu/repositoriju. Aplikaciju **nije radio jedan autor** — radili smo u timu od 3 člana.

## Tim

| Ime i prezime | E-mail (FOI) | JMBAG | GitHub |
| --- | --- | --- | --- |
| Jakov Glavač | jglavac21@student.foi.hr | 0016152889 | [jglavac21](https://github.com/jglavac21) |
| Karlo Alković | kalkovic21@student.foi.hr | 0016156440 | [Kajo9](https://github.com/Kajo9) (ranije `kalkovic21`) |
| Dominik Janda | djanda@student.foi.hr | 0016124868 | [djandafoi](https://github.com/djandafoi) |

## Što aplikacija radi

MyPetPal pomaže korisniku da vodi brigu o psu ili mački: registracija, prijava, dodavanje ljubimaca, pregled profila i savjeti za prehranu i njegu. Uz to ima mini-igru (hranjenje / izbjegavanje prepreka) kako bi korisnik ostao angažiran.

## Funkcionalnosti i tko je bio zadužen

Oznake su iz originalne specifikacije projekta.

| Oznaka | Naziv | Kratki opis | Zaduženi |
| --- | --- | --- | --- |
| F01 | Registracija korisnika | Obrazac, validacija i obavijest o uspjehu/neuspjehu | Jakov Glavač |
| F02 | Login | Prijava s podacima s računa | Karlo Alković |
| F03 | Odabir / dodavanje ljubimca | Prvi odabir i kasnije dodavanje novog ljubimca | Jakov Glavač |
| F04 | Profil ljubimca | Pasmina, očekivani životni vijek i ostale informacije | Dominik Janda |
| F05 | Planer prehrane / savjeti za njegu | Savjeti za prehranu, krzno, kandže i higijenu | Karlo Alković |
| F07 | Element igre | Mini-igra u stilu Tamagotchija (hranjenje, igranje, zdravlje) | cijeli tim |

## Tehnologije

- **Jezik:** Kotlin
- **IDE:** Android Studio
- **Lokalna baza:** Room
- **UI:** XML layouti
- **Min. SDK:** 24

## Pokretanje

1. Otvori ovaj folder u Android Studio.
2. Sinkroniziraj Gradle.
3. Pokreni na emulatoru ili uređaju s **API 24+**.

`applicationId`: `hr.foi.rmai.mypetpal`

## Napomena o Git povijesti

Na ovom repositoriju vidi se samo početni commit jer je kod prebačen na novi, prazan repo. To **ne znači** da je cijeli projekt nastao u jednom commitu ili da ga je radio samo vlasnik ovog računa. Autori su navedeni u tablici tima i u zaduženjima po funkcionalnostima.
