import java.io.*;
import java.util.*;
public class TrainNetwork
{

    public TrainNetwork()
    {random();}
    public void random()
    {
        String insert ="";
        for(int k =0; k<4; k++)
        {
        for(int i = 0; i< 7; i++)
        {
            int number = (int)(Math.random() * 100);
            if (Math.random() > .9)
                number = number * -1;
            insert = insert + number +" ";
        }
            insert = insert + System.lineSeparator();
        }
       // System.out.println(insert);
        fileWrite(insert,"src/train.txt", false);
    }
    public void fileWrite(String text, String filename, boolean overwrite)
    { String print = text;
        try
        {
            //Open a Stream to a file that you can write to
            FileWriter fw = new FileWriter(filename, overwrite);//false to overwrite
            BufferedWriter bw = new BufferedWriter(fw);
            //bw.write(print);
            PrintWriter out = new PrintWriter(bw, true);//false for manual flush
            //write data to the stream
           // System.out.println(print);
            out.println(print);
            //close the stream
            out.close();
            System.out.println("pineapple");

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void merge(String filename, String secondFile)
    {
        try
        {
            //Open a Stream to a file you can read from
            Scanner s = new Scanner( new File(filename) );
            //Loop through each line of the file

            String[] lOne= s.nextLine().split(" ");
            String[] lTwo= s.nextLine().split(" ");
            String[] lThree= s.nextLine().split(" ");
            String[] lFour = s.nextLine().split(" ");
            //System.out.println("please tell me tworlsdna");
            int l = Integer.parseInt(s.nextLine());
            //System.out.println("test to see the eroororor");
            int[] lOneint = new int[lOne.length];
            int[] lTwoint = new int[lTwo.length];
            int[] lThreeint = new int[lThree.length];
            int[] lFourint = new int[lFour.length];
            for(int i = 0; i<lOne.length; i++)
            {
                lOneint[i] = Integer.parseInt(lOne[i]);
            }
            for(int i = 0; i<lTwo.length; i++)
            {
                lTwoint[i] = Integer.parseInt(lTwo[i]);
            }
            for(int i = 0; i<lThree.length; i++)
            {
                lThreeint[i] = Integer.parseInt(lThree[i]);
            }
            for(int i = 0; i<lFour.length; i++)
            {
                lFourint[i] = Integer.parseInt(lFour[i]);
            }



            //Close the stream
            s.close();





            ////// part 2
            Scanner d = new Scanner( new File(secondFile) );
            //Loop through each line of the file
                System.out.println("part 2 test");
            String[] rOne= d.nextLine().split(" ");
            String[] rTwo= d.nextLine().split(" ");
            String[] rThree= d.nextLine().split(" ");
            String[] rFour = d.nextLine().split(" ");
            int r = Integer.parseInt(d.nextLine());
            int[] rOneint = new int[lOne.length];
            int[] rTwoint = new int[lTwo.length];
            int[] rThreeint = new int[lThree.length];
            int[] rFourint = new int[lFour.length];
            for(int i = 0; i<rOne.length; i++)
            {
                rOneint[i] = Integer.parseInt(lOne[i]);
            }
            for(int i = 0; i<rTwo.length; i++)
            {
                rTwoint[i] = Integer.parseInt(lTwo[i]);
            }
            for(int i = 0; i<rThree.length; i++)
            {
                rThreeint[i] = Integer.parseInt(lThree[i]);
            }
            for(int i = 0; i<rFour.length; i++)
            {
                rFourint[i] = Integer.parseInt(lFour[i]);
            }


            //Close the stream
            d.close();

            ///// merge merge mergeugruehsuhf
            int avg = r+l/2 ;
            String merge ="";
            for (int i=0; i<7; i++)
            {
                merge = merge + (((lOneint[i]*r) + (rOneint[i]*l))/ (r+l)) + " ";
            }
            merge= merge + System.lineSeparator();
            for (int i=0; i<7; i++)
            {
                merge = merge + (((lTwoint[i]*r) + (rTwoint[i]*l))/ (r+l)) + " ";
            }
            merge= merge + System.lineSeparator();
            for (int i=0; i<7; i++)
            {
                merge = merge + (((lThreeint[i]*r) + (rThreeint[i]*l))/ (r+l)) + " ";
            }
            merge= merge + System.lineSeparator();
            for (int i=0; i<7; i++)
            {
                merge = merge + (((lFourint[i]*r) + (rFourint[i]*l))/ (r+l)) + " ";
            }
            merge= merge + System.lineSeparator() + avg;
            fileWrite(merge, "src/merge.txt", false);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }


}
