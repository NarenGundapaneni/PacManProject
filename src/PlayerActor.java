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

    //cockblock does not run
    public boolean isBlocked(String direction)
    {
        int xPos = getX();
        int yPos = getY();



        if(direction.equals("UP"))
            if(getOneObjectAtOffset(0, -11, Block.class) != null)
                return true;
            else if(direction.equals("DOWN"))
                if(getOneObjectAtOffset(0, 11, Block.class) != null)
                    return true;
        if(direction.equals("LEFT"))
            if(getOneObjectAtOffset(-11, 0, Block.class) != null)
                return true;
            else
            if(getOneObjectAtOffset(11, 0, Block.class) != null)
                return true;

        return false;
    }
}
