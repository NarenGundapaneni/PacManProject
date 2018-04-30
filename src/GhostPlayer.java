import mayflower.*;

public class GhostPlayer extends PlayerActor
{
    public GhostPlayer()
    {
        super(PlayerType.Ghost0, 2);
    }

    public GhostPlayer(PlayerType playerType, int speed)
    {
        super(playerType, speed);
    }

}