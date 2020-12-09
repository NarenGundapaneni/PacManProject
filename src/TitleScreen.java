import mayflower.*;
public class TitleScreen extends World
{
    public TitleScreen()
    {
        setBackground("img/titlescreen.jpg"); //get this image
        Button play = new Button("img/play.png",new StageOne(),270,120);
        addObject(play,215,302);
    }
    public void act()
    {
    }
}
