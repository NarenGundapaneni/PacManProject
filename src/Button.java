import mayflower.*;

import java.awt.*;

public class Button extends Actor
{

    private MayflowerImage regular;
    private MayflowerImage hovered;
    private MayflowerImage clicked;
    private MayflowerSound clickSound;
    private World newWorld;
    private boolean wasHovered;
    public Button(String path, String hoveredPath, String clickedPath, 
    String clickSnd, World worldSet, int xSize, int ySize)
    {
        regular = new MayflowerImage(path);
        hovered = new MayflowerImage(hoveredPath);
        regular.scale(xSize,ySize);
        hovered.scale(xSize,ySize);
        if(clickedPath != null)
        {
            //clicked = new MayflowerImage();
            clicked.scale(xSize,ySize);
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

        /* i don't have the documentation on me at the moment,
            but there is something wrong with isClicked

        if(Mayflower.isClicked())
        {
            if(clicked!=null)
                setImage(clicked);
            if(clickSound!=null)
                clickSound.play();
            if(newWorld!=null)
                Mayflower.setWorld(newWorld);
        }

        */
    }
    public boolean isHovered() //checks if the mouse is over the button
    {
        int x = 0,y = 0;
        if(Mayflower.getMouseInfo()!=null) {
            x = Mayflower.getMouseInfo().getX(); //mouse's x coordinate
            y = Mayflower.getMouseInfo().getY(); //mouse's y coordinate
        }
        /* i dont know what to use instead of isTouching
        if(isTouching(x,y))
            return true;
        */
        return false;
    }


}

