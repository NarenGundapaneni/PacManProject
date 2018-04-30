import mayflower.*;

import java.security.Key;
import java.util.*;

public class PlayerActor extends Actor
{
    //PacManClient client;
    private PlayerType playerType;
    private int speed;
    private Keyboard kb;


    public PlayerActor()
    {
        this(PlayerType.PacMan, 3);
    }


    public PlayerActor(PlayerType playerType, int speed)
    {
        //this.client = client;
        this.playerType = playerType;
        this.speed = speed;

        if(playerType == PlayerType.PacMan) {
            setImage("img/pacman.jpg");
        }
        else {
            setImage("img/ghost.jpg");
        }

    }

    public void act()
    {
        if(Mayflower.isKeyDown(Keyboard.KEY_UP))
            move("UP");
        if(Mayflower.isKeyDown(Keyboard.KEY_DOWN))
            move("DOWN");
        if(Mayflower.isKeyDown(Keyboard.KEY_LEFT))
            move("LEFT");
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
            move("RIGHT");

    }

    public void move(String direction)
    {
        if("UP".equals(direction) && !isBlocked("UP"))
            setLocation(getX(), getY() - speed);
        if("DOWN".equals(direction) && !isBlocked("DOWN"))
            setLocation(getX(), getY() + speed);
        if("LEFT".equals(direction) && !isBlocked("LEFT"))
            setLocation(getX() - speed, getY());
        if("RIGHT".equals(direction) && !isBlocked("RIGHT"))
            setLocation(getX() + speed, getY());


    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public boolean isBlocked(String direction)
    {
        int xPos = getX();
        int yPos = getY();

        if("UP".equals(direction))
        {
            setLocation(xPos, yPos-1);
            List<Block> blocks = getIntersectingObjects(Block.class);
            if(blocks.size() != 0) {
                setLocation(xPos, yPos);
                return true;
            }
        }
        else if("DOWN".equals(direction))
        {
            //cockblocked
            setLocation(xPos, yPos+1);
            List<Block> blocks = getIntersectingObjects(Block.class);
            if(blocks.size() != 0) {
                setLocation(xPos, yPos);
                return true;
            }
        }
        else if("LEFT".equals(direction))
        {
            setLocation(xPos-1, yPos);
            List<Block> blocks = getIntersectingObjects(Block.class);
            if(blocks.size() != 0) {
                setLocation(xPos, yPos);
                return true;
            }
        }
        else
        {
            setLocation(xPos+1, yPos);
            List<Block> blocks = getIntersectingObjects(Block.class);
            if(blocks.size() != 0) {
                setLocation(xPos, yPos);
                return true;
            }
        }
        return false;
    }
}
