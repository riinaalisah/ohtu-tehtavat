package ohtu;

import java.util.Comparator;

public class SortByPoints implements Comparator<Player> {

    public int compare(Player a, Player b) {
        return b.getPoints() - a.getPoints();
    }
}