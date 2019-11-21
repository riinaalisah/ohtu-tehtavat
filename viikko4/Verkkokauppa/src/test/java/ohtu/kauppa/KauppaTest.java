package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;


    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void kahdenEriTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(8));
    }

    @Test
    public void kahdenSamanTuoteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(10));
    }

    @Test
    public void varastossaOlevanJaVarastostaLoppuneenTuotteenOstoksenPaatyttyaMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void metodinAloitaAsiointiKutsuminenNollaaEdellisenOstoksenTiedot() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki, times(2)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));
    }

    @Test
    public void jokaiselleMaksutapahtumallePyydetaanUusiViitenumero() {
        when(viite.uusi()).thenReturn(40);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        verify(pankki, times(1)).tilisiirto(anyString(), eq(40), anyString(), anyString(), anyInt());

        when(viite.uusi()).thenReturn(41);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki, times(1)).tilisiirto(anyString(), eq(41), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostoskoriinLisattyTuotePoistetaanKorista() {
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 3));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(3));
    }

}