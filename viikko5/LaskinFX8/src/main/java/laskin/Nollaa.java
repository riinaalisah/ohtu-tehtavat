package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private int arvo;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        arvo = sovellus.tulos();
        sovellus.nollaa();

        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());

        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        if (arvo > 0) {
            sovellus.plus(arvo);
        } else {
            sovellus.miinus(arvo);
        }
        tuloskentta.setText("" + sovellus.tulos());
        undo.disableProperty().set(true);
    }
}
