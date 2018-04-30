<<<<<<< HEAD
import mayflower.*;
public class Button extends Actor
{
    private Picture regular;
    private Picture hovered;
    private Picture clicked;
    private Sound clickSound;
    private Stage newWorld;
    private boolean wasHovered;
    public Button(String path, String hoveredPath, String clickedPath, 
    String clickSnd,Stage worldSet, int xsize, int ysize)
    {
        regular = new Picture(path);
        hovered = new Picture(hoveredPath);
        regular.resize(xsize,ysize);
        hovered.resize(xsize,ysize);
        if(clickedPath != null)
        {
            clicked = new Picture(clickedPath);
            clicked.resize(xsize,ysize);
        }
        if(clickSnd!=null)clickSound = new Sound(clickSnd);
        newWorld = worldSet;
        setPicture(path);
    }
    public void update()
    {
        if(isHovered() && !wasHovered) 
        {
            setPicture(hovered); wasHovered = true;
        }
        else setPicture(regular); wasHovered = false;
        if(isClicked())
        {
            if(clicked!=null)setPicture(clicked);
            if(clickSound!=null)clickSound.play();
            if(newWorld!=null)getMayflower().setStage(newWorld);
        }
    }
    public boolean isHovered() //checks if the mouse is over the button
    {
        int x = 0,y = 0;
        if(getMouse()!=null)x = getMouse().getX(); //mouse's x coordinate
        if(getMouse()!=null)y = getMouse().getY(); //mouse's y coordinate
        if(isTouching(x,y)) return true;
        return false;
    }
}

=======
import mayflower.*;
public class Button extends Actor
{
    private Picture regular;
    private Picture hovered;
    private Picture clicked;
    private Sound clickSound;
    private Stage newWorld;
    private boolean wasHovered;
    public Button(String path, String hoveredPath, String clickedPath, 
    String clickSnd,Stage worldSet, int xsize, int ysize)
    {
        regular = new Picture(path);
        hovered = new Picture(hoveredPath);
        regular.resize(xsize,ysize);
        hovered.resize(xsize,ysize);
        if(clickedPath != null)
        {
            clicked = new Picture(clickedPath);
            clicked.resize(xsize,ysize);
        }
        if(clickSnd!=null)clickSound = new Sound(clickSnd);
        newWorld = worldSet;
        setPicture(path);
    }
    public void update()
    {
        if(isHovered() && !wasHovered) 
        {
            setPicture(hovered); wasHovered = true;
        }
        else setPicture(regular); wasHovered = false;
        if(isClicked())
        {
            if(clicked!=null)setPicture(clicked);
            if(clickSound!=null)clickSound.play();
            if(newWorld!=null)getMayflower().setStage(newWorld);
        }
    }
    public boolean isHovered() //checks if the mouse is over the button
    {
        int x = 0,y = 0;
        if(getMouse()!=null)x = getMouse().getX(); //mouse's x coordinate
        if(getMouse()!=null)y = getMouse().getY(); //mouse's y coordinate
        if(isTouching(x,y)) return true;
        return false;
    }
}

>>>>>>> 17ccca0f8b042da6dc550df0fe0377ad7fad9c00
