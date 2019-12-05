package statistics.matcher;

import statistics.Player;

public class All implements Matcher {

    private Matcher[] matchers;

    public All(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            return true;
        }
        return true;
    }
}
