
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLukumaara;    // Tyhjässä joukossa alkioiden_määrä on nolla.
    private static int[] joukkoA;
    private static int[] joukkoB;

    public IntJoukko() {
        alustaLukujono(KAPASITEETTI);
        setKasvatuskoko(OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaLukujono(kapasiteetti);
        setKasvatuskoko(OLETUSKASVATUS);
    }


    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko väärin");
        }
        alustaLukujono(kapasiteetti);
        setKasvatuskoko(kasvatuskoko);

    }

    private void setLukujono(int[] lukujono) {
        this.lukujono = lukujono;
    }

    public int[] getLukujono() {
        int[] taulu = new int[alkioidenLukumaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public int getAlkioidenLukumaara() {
        return alkioidenLukumaara;
    }

    private void setKasvatuskoko(int kasvatuskoko) {
        this.kasvatuskoko = kasvatuskoko;
    }

    private static void setJoukkoAjaB(IntJoukko a, IntJoukko b) {
        joukkoA = a.getLukujono();
        joukkoB = b.getLukujono();
    }

    private void alustaLukujono(int kapasiteetti) {
        alkioidenLukumaara = 0;
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }

    }

    public boolean lisaa(int luku) {
        if (alkioidenLukumaara == 0) {
            lukujono[0] = luku;
            alkioidenLukumaara++;
            return true;
        }
        if (!kuuluu(luku)) {
            lukujono[alkioidenLukumaara] = luku;
            alkioidenLukumaara++;
            if (alkioidenLukumaara % lukujono.length == 0) {
                kasvataLukujonoa();
            }
            return true;
        }
        return false;
    }

    public void kasvataLukujonoa() {
        int[] vanhaLukujono = lukujono;
        setLukujono(new int[alkioidenLukumaara + kasvatuskoko]);
        kopioiTaulukko(vanhaLukujono, lukujono);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = 0;
                for (int j = i; j < alkioidenLukumaara - 1; j++) {
                    lukujono[j] = lukujono[j + 1];
                }
                alkioidenLukumaara--;
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }


    @Override
    public String toString() {
        if (alkioidenLukumaara == 0) {
            return "{}";
        }

        String tuloste = "{";
        for (int i = 0; i < alkioidenLukumaara; i++) {
            tuloste += lukujono[i];
            if (i < alkioidenLukumaara - 1) {
                tuloste += ", ";
            }
        }
        return tuloste + "}";
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        setJoukkoAjaB(a, b);

        for (int i = 0; i < joukkoA.length; i++) {
            yhdiste.lisaa(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            yhdiste.lisaa(joukkoB[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        setJoukkoAjaB(a, b);

        for (int i = 0; i < joukkoA.length; i++) {
            for (int j = 0; j < joukkoB.length; j++) {
                if (joukkoA[i] == joukkoB[j]) {
                    leikkaus.lisaa(joukkoB[j]);
                }
            }
        }
        return leikkaus;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        setJoukkoAjaB(a, b);

        for (int i = 0; i < joukkoA.length; i++) {
            erotus.lisaa(joukkoA[i]);
        }
        for (int i = 0; i < joukkoB.length; i++) {
            erotus.poista(joukkoB[i]);
        }
        return erotus;
    }


        
}
