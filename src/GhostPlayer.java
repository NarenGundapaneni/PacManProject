
import java.util.List;

public class GhostPlayer extends PlayerActor {
    public GhostPlayer() {
        super(PlayerType.Ghost0, 2);
    }

    public GhostPlayer(PlayerType playerType, int speed) {
        super(playerType, speed);
    }

    public void act() {
        super.act();
    }

    public void resetImage() {
        if (this.getPlayerType() == PlayerType.Ghost0) {
            this.setImage("img/ghost0.jpg");
        } else if (this.getPlayerType() == PlayerType.Ghost1) {
            this.setImage("img/ghost1.jpg");
        } else if (this.getPlayerType() == PlayerType.Ghost2) {
            this.setImage("img/ghost2.jpg");
        } else {
            this.setImage("img/ghost3.jpg");
        }

    }

    public List<PlayerActor> getTouching() {
        return this.getIntersectingObjects(PlayerActor.class);
    }
}
