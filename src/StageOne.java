import mayflower.*;
import java.util.List;
import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class StageOne extends World
{
    private Keyboard kb;
    private int[][] map;
    private PacMan game;
    private PlayerType yourPlayer;
    private PlayerActor[] players;
    private PacPlayer pac;
    private GhostPlayer[] ghosts;
    private ImageActor pacLife0;
    private ImageActor pacLife1;
    private Timer damageTime;
    private Timer eatTime;
    private boolean panic;
    private NeuralStructure structure;
    private NeuralStructure structureTwo;
    private NeuralStructure structureThree;
    private NeuralStructure structureFour;
    public StageOne()
    {
        int width = 25;
        int height = 25;
        map = new int[][]
                {
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
                        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                        {1,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,1},
                        {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
                        {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
                        {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
                        {1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1},
                        {0,0,0,0,0,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,0,0,0,0,0},
                        {0,0,0,0,0,1,2,1,1,2,2,2,2,2,2,2,2,2,2,1,1,2,1,0,0,0,0,0},
                        {0,0,0,0,0,1,2,1,1,2,1,1,1,5,5,1,1,1,2,1,1,2,1,0,0,0,0,0},
                        {1,1,1,1,1,1,2,1,1,2,1,0,0,0,0,0,0,1,2,1,1,2,1,1,1,1,1,1},
                        {0,0,0,0,0,4,2,2,2,2,1,0,0,0,0,0,0,1,2,2,2,2,4,0,0,0,0,0},//midpoint
                        {1,1,1,1,1,1,2,1,1,2,1,0,0,0,0,0,0,1,2,1,1,2,1,1,1,1,1,1},
                        {0,0,0,0,0,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,0,0,0,0,0},
                        {0,0,0,0,0,1,2,1,1,2,2,2,2,2,2,2,2,2,2,1,1,2,1,0,0,0,0,0},
                        {0,0,0,0,0,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,0,0,0,0,0},
                        {1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1},
                        {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
                        {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
                        {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
                        {1,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,1},
                        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                        {1,3,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,3,1},
                        {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                        {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
                };

        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                int i = map[r][c];
                Actor tile = getTile(i);

                if(null != tile)
                    addObject(tile, c*width, r*height);
            }
        }

        pac = new PacPlayer();
        ghosts = new GhostPlayer[]
                {
                        new GhostPlayer(PlayerType.Ghost0, 2),
                        new GhostPlayer(PlayerType.Ghost1, 2),
                        new GhostPlayer(PlayerType.Ghost2, 2),
                        new GhostPlayer(PlayerType.Ghost3, 2)
                };


        addObject(pac, 338, 576);
        addObject(ghosts[0], 338, 328);
        addObject(ghosts[1], 338, 338);
        addObject(ghosts[2], 338, 348);
        addObject(ghosts[3], 338, 358);

        pacLife0 = new ImageActor("img/pacLives.jpg");
        pacLife1 = new ImageActor("img/pacLives.jpg");
        addObject(pacLife0, 555, 5);
        addObject(pacLife1, 580, 5);

        damageTime = new Timer();
        damageTime.set(3000);
        eatTime = new Timer();
        structure = new NeuralStructure(ghosts[0],pac,ghosts[1],ghosts[2],ghosts[3]);
        structureTwo = new NeuralStructure(ghosts[1],pac,ghosts[0],ghosts[2],ghosts[3]);
        structureThree = new NeuralStructure(ghosts[2],pac,ghosts[1],ghosts[0],ghosts[3]);
        structureFour = new NeuralStructure(ghosts[3],pac,ghosts[1],ghosts[2],ghosts[0]);

    }

    public void act()
    {
        showText("Score: " + pac.getPacScore(), 20, 25, 0);
        showText("Lives Remaining: ", 20, 400, 0);
        System.out.println(structure.netMove());


        pac.pickUp();
        if(!eatTime.isDone())
            getEaten();
        else
            damagePacMan();

        if(getObjects(GhostPlayer.class).size() == 0)
            endGame();

    }

    public boolean hasEatStarted()
    {
        return !eatTime.isDone();
    }

    public void startEatTime()
    {
        eatTime.set(10000);
    }

    public void getEaten()
    {

        for(int i = ghosts.length - 1; i >= 0; i--)
        {
            GhostPlayer ghost = ghosts[i];
            ghost.setImage("img/ghostPanic.jpg");
            if(ghost != null)
            {
                List<PlayerActor> touching = ghost.getTouching();
                for(PlayerActor player : touching) {
                    if(null != player && player.getPlayerType() == PlayerType.PacMan && !eatTime.isDone()) {
                        PacPlayer pacPlayer = (PacPlayer) player;
                        pacPlayer.setPacScore(pacPlayer.getPacScore()+100);
                        removeObject(ghosts[i]);
                    }
                }
            }
        }


    }


    public void damagePacMan()
    {
        for(GhostPlayer ghost : ghosts)
        {
            ghost.resetImage();
            if(ghost != null) {
                List<PlayerActor> touching = ghost.getTouching();
                for (PlayerActor player : touching) {
                    if (null != player && player.getPlayerType() == PlayerType.PacMan && damageTime.isDone()) {
                        PacPlayer pacPlayer = (PacPlayer) player;
                        int lives = pacPlayer.getPacLives();
                        if (lives == 3)
                            removeObject(pacLife1);
                        else if (lives == 2)
                            removeObject(pacLife0);
                        else
                            endGame();
                        damageTime.reset();
                        pacPlayer.setPacLives(lives - 1);

                    }

                }
            }

        }

    }

    public void endGame()
    {
        GameOver gameOver = new GameOver(pac.getPacScore());
        structure.printNetwork("src/train.txt",false);
        Mayflower.setWorld(gameOver);
    }

    public void movePlayer(PlayerType player, String direction)
    {
        for(PlayerActor a : getObjects(PlayerActor.class))
        {
            if(a.getPlayerType() == player)
            {
                //a.move(direction);
            }

        }
    }

    public int[][] getMap()
    {
        return map;
    }
    public void setBlock(int r, int c, int type)
    {
        map[r][c] = type;
    }
    public int getBlock(int r, int c)
    {
        return map[r][c];
    }

    private Actor getTile(int i)
    {
        if(i == 1)
            return new Block(0);
        if(i == 2)
            return new Block(1);
        if(i == 3)
            return new Block(2);
        if(i == 4)
            return new Block(3);
        if(i == 5)
            return new Block(4);
        return null;
    }
    public String fileRead(String filename)
    {
        String data = "";
        try
        {
            //Open a Stream to a file you can read from
            Scanner s = new Scanner( new File(filename) );
            //Loop through each line of the file
            while( s.hasNextLine() )
            {
                //Read each line and append it to a String
                data += s.nextLine() + "\n";
            }
            //Close the stream
            s.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public int fileReadInt(String filename)
    {
        int data = -1;
        try
        {
            //Open a Stream to a file you can read from
            Scanner s = new Scanner( new File(filename) );
            //Loop through each line of the file
            while( s.hasNextInt() )
            {
                //Read each line and append it to a String
                data = s.nextInt();
            }
            //Close the stream
            s.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public void fileWrite(String text, String filename, boolean overwrite)
    {
        try
        {
            //Open a Stream to a file that you can write to
            FileWriter fw = new FileWriter(filename, overwrite);//false to overwrite
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw, true);//false for manual flush
            //write data to the stream
            out.println(text);
            //close the stream
            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}