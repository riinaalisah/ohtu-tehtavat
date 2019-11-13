
package ohtu;

public class Player {
    private int goals;
    private int assists;
    private String name;
    private int penalties;
    private String team;
    private String nationality;
    private String birtdate;
    private int points;

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthdate(String birtdate) {
        this.birtdate = birtdate;
    }

    public String getName() {
        return name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNationality() {
        return nationality;
    }

   

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return name + "     " + team + "    " + goals + " + " + assists + " = " + points;
    }
      
}
