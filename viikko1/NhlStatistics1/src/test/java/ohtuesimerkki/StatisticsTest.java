package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }

    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void findsPlayerOnList() {
        Player returned = stats.search("Kurri");
        assertNotNull(returned);
    }

    @Test
    public void doesNotFindPlayerNotOnList() {
        Player returned = stats.search("NoOne");
        assertNull(returned);
    }

    @Test
    public void returnsPlayersOnTeam() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
    }

    @Test
    public void doesNotFindPlayersOnNonexistentTeam() {
        List<Player> players = stats.team("none");
        assertEquals(0, players.size());
    }

    @Test
    public void returnsThreeTopScorers() {
        List<Player> topScorers = stats.topScorers(2);
        assertEquals(3, topScorers.size());
    }

}
