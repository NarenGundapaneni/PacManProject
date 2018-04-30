import mayflower.*;
public class Block extends Actor
{
    private int type;
    public Block(int t)
    {
        type = t;
        setImage("img/block" + type + ".png");
    }
    public void act()
    {
    }
}