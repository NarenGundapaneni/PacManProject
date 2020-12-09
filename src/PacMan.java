
public class PacMan {
    private int pacLives;
    private int pacScore;

    public PacMan() {
    }

    public void movePlayer(PlayerType player, String direction) {
    }

    public boolean isGameOver() {
        return this.pacLives < 0;
    }

    public void setPacLives(int pacLives) {
        this.pacLives = pacLives;
    }

    public void setPacScore(int pacScore) {
        this.pacScore = pacScore;
    }

    public int getPacLives() {
        return this.pacLives;
    }

    public int getPacScore() {
        return this.pacScore;
    }
}
