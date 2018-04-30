import mayflower.*;

import java.awt.*;

public class Button extends Actor
{
    private Image regular;
    private Image hovered;
    private Image clicked;
    private MayflowerSound clickSound;
    private World newWorld;
    private boolean wasHovered;
    public Button(String path, String hoveredPath, String clickedPath, 
    String clickSnd, World worldSet, int xsize, int ysize)
    {
        regular = new Image(path);
        hovered = new Image(hoveredPath);
        regular.resize(xsize,ysize);
        hovered.resize(xsize,ysize);
        if(clickedPath != null)
        {
            clicked =
            clicked.resize(xsize,ysize);
        }
        if(clickSnd!=null)clickSound = new MayflowerSound(clickSnd);
        newWorld = worldSet;
        setImage(path);
    }
    public void act()
    {
        if(isHovered() && !wasHovered) 
        {
            setImage(hovered);
            wasHovered = true;
        }
        else
        {
            setImage(regular);
            wasHovered = false;
        }

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
        if(Mayflower.getMouseInfo()!=null) {
            x = Mayflower.getMouseInfo().getX(); //mouse's x coordinate
            y = Mayflower.getMouseInfo().getY(); //mouse's y coordinate
        }
        if(isTouching(x,y))
            return true;
        return false;
    }

}

