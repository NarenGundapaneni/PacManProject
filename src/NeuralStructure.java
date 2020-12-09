import org.lwjgl.system.CallbackI;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.Clock;

public class NeuralStructure
{
    private PacPlayer pacMan;
    private List<GhostPlayer> Ghosts;
    private PacDistCell pacDistCell;
    private PacDirectionCell pacDirectionCell;
    private LeftCell left;
    private RightCell right;
    private UpCell up;
    private DownCell down;
    private OtherGCellTwo two;
    private OtherGCellThree three;
    private OtherGCellOne one;
    private MoveCell move;
    private PacRelativityCell relitve;
    private Stopwatch watch;
    private TrainNetwork train;

    public NeuralStructure( GhostPlayer four, PacPlayer pac, GhostPlayer one, GhostPlayer two, GhostPlayer three)
    {
        try{
        pacMan = pac;
        System.out.println("pac is made");
        Ghosts = new ArrayList<GhostPlayer>();
        Ghosts.add(one); Ghosts.add(two); Ghosts.add(three); Ghosts.add(four);
            System.out.println("ghost is made");
        makeNetwork();}
        catch (Exception e)
        {System.out.println("issue in iniutalizing");}
    }
    public void makeNetwork()
    {
        try {
            one = new OtherGCellOne(Ghosts.get(3), Ghosts.get(0));
            two = new OtherGCellTwo(Ghosts.get(3),Ghosts.get(1));
            three = new OtherGCellThree(Ghosts.get(3),Ghosts.get(2));
            pacDistCell = new PacDistCell(pacMan, Ghosts.get(3));
            pacDirectionCell = new PacDirectionCell(pacMan);
            relitve = new PacRelativityCell(pacMan,Ghosts.get(3));
            left = new LeftCell(one, two, three, pacDirectionCell, pacDistCell, relitve);
            right = new RightCell(one, two, three, pacDirectionCell, pacDistCell, relitve);
            up = new UpCell(one, two, three, pacDirectionCell, pacDistCell, relitve);
            down = new DownCell(one, two, three, pacDirectionCell, pacDistCell, relitve);
            move = new MoveCell(right, left, up, down);
            watch = new Stopwatch();
            train = new TrainNetwork();
            setWeightsAndBiases("src/train.txt");



        }
        catch (Exception e)
        {System.out.println("issue in making network" );
         e.printStackTrace();
        }





    }
    public void setWeightsAndBiases(String filename)
    {
        //use file reader to get the file and 4 strings to set up the network
       // left.setWeights("1 1 1 1 1 1 1 1 1 1 1 1");
       // right.setWeights("1 1 1 1 1 1 1 1 1 1 1 1");
      //  up.setWeights("1 1 1 1 1 1 1 1 1 1 1 1");
      //  down.setWeights("1 1 1 1 1 1 1 1 1 1 1 1");

        try
        {
            //Open a Stream to a file you can read from
            Scanner s = new Scanner( new File(filename) );
            //Loop through each line of the file
            if(s.hasNextLine()){left.setWeights(s.nextLine());}
            if(s.hasNextLine()){right.setWeights(s.nextLine());}
            if(s.hasNextLine()){up.setWeights(s.nextLine());}
            if(s.hasNextLine()){down.setWeights(s.nextLine());}
            //Close the stream
            s.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public String netMove()
    {
        return move.MakeMove();
    }
    public void printNetwork(String filename, boolean append)
    {   String print= "";

        print = left.sendWeights() + System.lineSeparator() + right.sendWeights() + System.lineSeparator() + up.sendWeights() + System.lineSeparator() + down.sendWeights() + System.lineSeparator() + watch.Lap()/100000;
        System.out.println(" the newtwork is" + print);
        try
        {
            //Open a Stream to a file that you can write to
            FileWriter fw = new FileWriter(filename, append);//false to overwrite
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw, true);//false for manual flush
            //write data to the stream
            out.println(print);
            //close the stream
            out.close();
            train.merge("src/train.txt", "src/merge.txt");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


}
