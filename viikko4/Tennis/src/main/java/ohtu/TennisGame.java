package ohtu;

public class TennisGame {
    
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String namePlayer1;
    private String namePlayer2;
    private String score;
    private int minusResult;

    public TennisGame(String player1Name, String player2Name) {
        this.namePlayer1 = player1Name;
        this.namePlayer2 = player2Name;
    }

    private void setMinusResult() {
        minusResult = scorePlayer1 - scorePlayer2;
    }

    public void wonPoint(String playerName) {
        if (playerName == namePlayer1) {
            scorePlayer1 += 1;
        } else {
            scorePlayer2 += 1;
        }
    }

    public String getScore() {
        score = "";
        if (scorePlayer1 == scorePlayer2) {
            equalScores();

        } else if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            checkAdvantage();

        } else {
            playerScore(scorePlayer1);
            score += "-";
            playerScore(scorePlayer2);
        }
        return score;
    }

    private void playerScore(int playerScore) {
        switch(playerScore) {
            case 0:
                score += "Love";
                break;
            case 1:
                score += "Fifteen";
                break;
            case 2:
                score += "Thirty";
                break;
            case 3:
                score += "Forty";
                break;
        }
    }

    private void equalScores() {
        switch (scorePlayer1) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;
        }
    }

    private void checkAdvantage() {
        setMinusResult();
        if (minusResult == 1) {
            advantage(namePlayer1);
        } else if (minusResult == -1) {
            advantage(namePlayer2);
        } else {
            checkWin();
        }
    }

    private void checkWin() {
        if (minusResult >= 2) {
            win(namePlayer1);
        } else {
            win(namePlayer2);
        }
    }

    private void advantage(String playerName) {
        score ="Advantage " + playerName;
    }

    private void win(String playerName) {
        score = "Win for " + playerName;
    }


}