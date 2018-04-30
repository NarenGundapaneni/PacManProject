<<<<<<< HEAD:src/TitleScreen.java
import mayflower.*;

public class TitleScreen extends World
=======
<<<<<<< HEAD
import mayflower.*;
public class TitleScreen extends Stage
{
    public TitleScreen()
    {
        setBackground("img/titlescreen.jpg"); //get this image
        Button play = new Button("img/play.png","img/playhovered.png",null,
        null,new StageOne(),270,120); //get click sound
        addActor(play,215,302);
    }
    public void update()
    {
    }
}

=======
import mayflower.*;
public class TitleScreen extends Stage
>>>>>>> 82e8506374183e2c2d0afdca6e8203765987f364:TitleScreen.java
{
    public TitleScreen()
    {
        setBackground("img/titlescreen.jpg"); //get this image
<<<<<<< HEAD:src/TitleScreen.java
        Button play = new Button("img/play.png", "img/playhovered.png",null,
        null,new StageOne(),270,120); //get click sound
        addObject(play,215,302);
    }
    public void act()
=======
        Button play = new Button("img/play.png","img/playhovered.png",null,
        null,new StageOne(),270,120); //get click sound
        addActor(play,215,302);
    }
    public void update()
>>>>>>> 82e8506374183e2c2d0afdca6e8203765987f364:TitleScreen.java
    {
    }
}

<<<<<<< HEAD:src/TitleScreen.java
=======
>>>>>>> 17ccca0f8b042da6dc550df0fe0377ad7fad9c00
>>>>>>> 82e8506374183e2c2d0afdca6e8203765987f364:TitleScreen.java
