import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class FileChanger {
    private String file;
    private List<Node> scores;
    public FileChanger(String filename)
    {
        file = filename;
        scores = new ArrayList<>();


    }
    public void addScore(Node ore)
    {
        readFile();
        scores.add(ore);
        sort();
        writeFile();

    }

    public void readFile()
    {
        try
        {
            Scanner s = new Scanner((new File(file)));
            while (s.hasNextLine())
            {
                scores.add(new Node(s.nextLine()));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file was not read...");
            e.printStackTrace();
        }

    }
    public void writeFile()
    {
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw, true);

            //Is this right? I need to check
           /* for (Node ode: scores)
            {
                out.println(ode.getResult());
            }
             out.close();
             */
            out.println(scores);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void sort()
    {   //Sort the score by number
        List<Integer> nScore = new ArrayList<>();
        for (Node x :scores)
        {
            nScore.add(x.getScore());
        }
        Collections.sort(nScore);
       //Make new list that will have the name associated with the number
        List<Node> reassign = new ArrayList<>();
        // make the nodes orginized
        for (Integer x:nScore)
        {
            //find the node that belongs to the score
            for (Node ode: scores)
            {
                if (ode.realNode(x))
                {
                     reassign.add(ode);
                }
            }
        }
        scores = reassign;
    }

}
