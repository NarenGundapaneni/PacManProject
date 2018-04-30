/*import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ghost extends MovingThing
{
    private int speed;
    private Image image;
    private int type;
    public Ghost()
    {
        this(0,0,35,35,0,0);
    }

    public Ghost(int x, int y, int w, int h, int s,int t)
    {
        super(x, y, w, h);
        speed=s;
        type = t;
        try
        {
            URL url = getClass().getResource("/images/ghost" + type + ".jpg"); //need to add ghost images
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
        }
    }

    public void setSpeed(int s)
    {
        speed = s;
    }

    public int getSpeed()
    {
        return 0;
    }

    public void move(String direction)
    {
        if(direction.equals("UP"))
        {
            super.setY(getY()-speed);
        }
        if(direction.equals("DOWN"))
        {
            super.setY(getY()+speed);
        }
        if(direction.equals("LEFT"))
        {
            super.setX(getX()-speed);
        }
        if(direction.equals("RIGHT"))
        {
            super.setX(getX()+speed);
        }
    }

    public void draw( Graphics window )
    {
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    public String toString()
    {
        return "";
    }
    public int getType()
    {
    return type;
    }

}

*/
