import mayflower.*;

public class Runner extends Mayflower
{
    public Runner()
    {
        super("PacMan", 700, 725);
    }
    public void init()
    {
        Mayflower.setFullScreen(false);
        World startingWorld = new TitleScreen(); //set to Stage1 if we don't have a title yet
        Mayflower.setWorld(startingWorld);
    }
    public static void main(String[] args)
    {
        new Runner();
    }
}