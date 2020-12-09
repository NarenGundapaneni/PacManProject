import java.util.ArrayList;
import java.util.List;

public class UpCell
{
    private double gOne;
    private double gTwo;
    private double gThree;
    private double pacDist;
    private double pacPowDist;
    private double pacDirection;
    private double relitivity;
    private int a, b, c , d, e, f, g;
    private OtherGCellThree three;
    private OtherGCellTwo two;
    private OtherGCellOne one;
    private PacDirectionCell dir;
    private PacDistCell dist;
    private PacRelativityCell relativityCell;

    public UpCell(OtherGCellOne on, OtherGCellTwo tw, OtherGCellThree the, PacDirectionCell di, PacDistCell dis, PacRelativityCell rel)
    {
        one = on;
        two= tw;
        three = the;
        dir = di;
        dist = dis;
        relativityCell = rel;
    }
    private static double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }
    public void setWeights(String weights)
    {
       String[] wAndG = weights.split(" ", 8);
       a= Integer.parseInt(wAndG[0]);
       b= Integer.parseInt(wAndG[1]);
       c= Integer.parseInt(wAndG[2]);
       d= Integer.parseInt(wAndG[3]);
       e= Integer.parseInt(wAndG[4]);
       f= Integer.parseInt(wAndG[5]);
       g= Integer.parseInt(wAndG[6]);
       // g= 0;
    }
    public String sendWeights()
    {
        return (a + " " + b + " " + c + " "+ d+ " " + e + " "+ f + " " +g);
    }

    private double process()
    {
        gOne = one.fire();
        gTwo = two.fire();
        gThree = three.fire();
        pacDist = dist.fire();
        pacDirection = dir.fire();
        relitivity = relativityCell.fireY();
        double ret = sigmoid(((a *gOne) + (b*gThree) + (c*gTwo) + (d*pacDist) + (e*pacDirection) - (f*relitivity) + g));
        //double ret = sigmoid(gOne + gThree + gTwo + pacDist + pacDirection - relitivity );
        return ret;}

    public  double fire()
    {return process();}
}