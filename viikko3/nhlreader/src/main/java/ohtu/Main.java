package ohtu;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        ArrayList<Player> finnishPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                finnishPlayers.add(player);
                player.setPoints(player.getGoals() + player.getAssists());
            }
        }

        Collections.sort(finnishPlayers, new SortByPoints());


        Date date = new Date();
        System.out.println("Players from FIN " + date);
        System.out.println();

        for (Player player : finnishPlayers) {
            System.out.println(player);
        }
    }
    
}
