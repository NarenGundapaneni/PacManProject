
import java.util.Iterator;
import java.util.List;

public class PacPlayer extends PlayerActor {
    private int pacLives = 1;
    private int pacScore = 0;
    private int lastX;
    private int lastY;

    public PacPlayer() {
        super(PlayerType.PacMan, 2);
    }

    public void act() {
        super.act();
        this.lastX = this.getX();
        this.lastY = this.getY();
    }

    public void pickUp() {
        List<Block> cheeses = this.getObjectsInRange(2, Block.class);
        if (cheeses.size() != 0) {
            Iterator var2 = cheeses.iterator();

            while(var2.hasNext()) {
                Block c = (Block)var2.next();
                if (c.getType() == 1) {
                    this.setPacScore(this.getPacScore() + 10);
                    this.getWorld().removeObject(c);
                } else if (c.getType() == 2) {
                    this.setPacScore(this.getPacScore() + 20);
                    StageOne stage = (StageOne)this.getWorld();
                    if (!stage.hasEatStarted()) {
                        stage.startEatTime();
                    }

                    this.getWorld().removeObject(c);
                }
            }
        }

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

    public int getLastX() {
        return this.lastX;
    }

    public int getLastY() {
        return this.lastY;
    }
}
