import mayflower.*;
import java.util.*;

public class PlayerActor extends Actor
{
    //PacManClient client;
    private PlayerType playerType;
    private int speed;
    private Keyboard kb;
    private int ghostDirection;


    public PlayerActor()
    {
        this(PlayerType.PacMan, 1);
    }


    public PlayerActor(PlayerType playerType, int speed)
    {
        //this.client = client;
        this.playerType = playerType;
        this.speed = speed;
        ghostDirection = 0;

        if(playerType == PlayerType.PacMan)
            setImage("img/pacman.jpg");
        else if(playerType == PlayerType.Ghost0)
            setImage("img/ghost0.jpg");
        else if(playerType == PlayerType.Ghost1)
            setImage("img/ghost1.jpg");
        else if(playerType == PlayerType.Ghost2)
            setImage("img/ghost2.jpg");
        else
            setImage("img/ghost3.jpg");


    }

    public void act()
    {
        if(playerType != PlayerType.PacMan)
            moveGhost();
        else
            movePac();

    }

    public void movePac()
    {
        if(playerType == PlayerType.PacMan) {
            if (Mayflower.isKeyDown(Keyboard.KEY_UP))
                move("UP");
            if (Mayflower.isKeyDown(Keyboard.KEY_DOWN))
                move("DOWN");
            if (Mayflower.isKeyDown(Keyboard.KEY_LEFT))
                move("LEFT");
            if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
                move("RIGHT");
        }
    }

    public void moveGhost()
    {
        //0 is up, 1 is down, 2 is left, 3 is right
        List<Block> blocks = getIntersectingObjects(Block.class);
        for(int i = blocks.size()-1; i >= 0; i--)
        {
            Block b = blocks.get(i);

            if(b.getType() != 0)
                blocks.remove(b);
        }

        if(!blocks.isEmpty()) {
            if (ghostDirection == 0)
                move("DOWN");
            else if (ghostDirection == 1)
                move("UP");
            else if (ghostDirection == 2)
                move("RIGHT");
            else
                move("LEFT");

            boolean flag = false;
            int rand = -1;
            while (!flag) {
                rand = (int) (Math.random() * 4);
                int dontgothisway = -1;
                if(ghostDirection == 0) dontgothisway = 1;
                if(ghostDirection == 1) dontgothisway = 0;
                if(ghostDirection == 2) dontgothisway = 3;
                if(ghostDirection == 3) dontgothisway = 2;
                if (rand != ghostDirection && rand != dontgothisway)
                    flag = true;
            }
            ghostDirection = rand;
        }
        if (ghostDirection == 0)
            move("UP");
        else if (ghostDirection == 1)
            move("DOWN");
        else if (ghostDirection == 2)
            move("LEFT");
        else
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

        if(playerType == PlayerType.PacMan) {
            if (getX() < 0)
                setLocation(699, getY());
            if (getX() > Mayflower.getWidth())
                setLocation(1, getY());
        }


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
        int padding = 0;
        int width = 1;
        if(playerType == PlayerType.PacMan) {
            padding = 12;
            width = 10;
        }


        if("UP".equals(direction))
        {
            List<Block> blocks = getObjectsAtOffset(0, 0-padding, Block.class);
            blocks.addAll(getObjectsAtOffset(0-width, 0-padding, Block.class));
            blocks.addAll(getObjectsAtOffset(width, 0-padding, Block.class));
            for(Block b : blocks)
            {
                if(b.getType() == 0 ||
                        (playerType == PlayerType.PacMan && b.getType() == 4) ||
                        (playerType != PlayerType.PacMan && b.getType() == 3))
                    return true;
            }

        }
        else if("DOWN".equals(direction))
        {
            List<Block> blocks = getObjectsAtOffset(0, padding, Block.class);
            blocks.addAll(getObjectsAtOffset(0-width, padding, Block.class));
            blocks.addAll(getObjectsAtOffset(width, padding, Block.class));
            for(Block b : blocks)
            {
                if(b.getType() == 0 ||
                        (playerType == PlayerType.PacMan && b.getType() == 4) ||
                        (playerType != PlayerType.PacMan && b.getType() == 3))
                    return true;
            }
        }
        else if("LEFT".equals(direction))
        {
            List<Block> blocks = getObjectsAtOffset(0-padding, 0, Block.class);
            blocks.addAll(getObjectsAtOffset(0-padding, 0-width, Block.class));
            blocks.addAll(getObjectsAtOffset(0-padding, width, Block.class));
            for(Block b : blocks)
            {
                if(b.getType() == 0 ||
                        (playerType == PlayerType.PacMan && b.getType() == 4) ||
                        (playerType != PlayerType.PacMan && b.getType() == 3))
                    return true;
            }
        }
        else
        {
            List<Block> blocks = getObjectsAtOffset(padding, 0, Block.class);
            blocks.addAll(getObjectsAtOffset(padding, 0-width, Block.class));
            blocks.addAll(getObjectsAtOffset(padding, width, Block.class));
            for(Block b : blocks)
            {
                if(b.getType() == 0 ||
                        (playerType == PlayerType.PacMan && b.getType() == 4) ||
                        (playerType != PlayerType.PacMan && b.getType() == 3))
                    return true;
            }
        }
        return false;
    }
}