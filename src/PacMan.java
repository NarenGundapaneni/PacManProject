import mayflower.*;

public class PacMan
{
    private int pacLives;
    private int pacScore;


    public PacMan()
    {

    }

    public void movePlayer(PlayerType player, String direction)
    {

    }

    public boolean isGameOver()
    {
        if(pacLives < 0)
            return true;
        else
            return false;
    }

    public void setPacLives(int pacLives) {
        this.pacLives = pacLives;
    }

    public void setPacScore(int pacScore) {
        this.pacScore = pacScore;
    }

    public int getPacLives() {
        return pacLives;
    }

    public int getPacScore() {
        return pacScore;
    }
}