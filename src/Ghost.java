/*import mayflower.*;

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ghost extends Actor
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

   public void move()
   {
	int direction = 0;//0 is up, 1 is down, 2 is left, 3 is right
	List<Block> blocks = getIntersectingObjects(Block.class);
	if(!blocks.isEmpty())
{
	if(direction==0)
       {
           super.setY(getY()-1);
       }
       if(direction==1)
       {
           super.setY(getY()+1);
       }
       if(direction==2)
       {
           super.setX(getX()-1);
       }
       if(direction==3)
       {
           super.setX(getX()+1);
       }
	boolean flag = false;
	int rand = -1;
	while(!flag)
	{
		rand = (int)(Math.random()*4);
		if(rand!=direction) flag = true;
	}
	direction = rand;
}
       if(direction==0)
       {
           super.setY(getY()-speed);
       }
       if(direction==1)
       {
           super.setY(getY()+speed);
       }
       if(direction==2)
       {
           super.setX(getX()-speed);
       }
       if(direction==3)
       {
           super.setX(getX()+speed);
       }
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