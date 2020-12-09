import org.lwjgl.system.CallbackI;

public class MoveCell
{
    double up; double down; double let; double right;
    RightCell rightCell; LeftCell leftCell; UpCell upCell; DownCell downCell;

    public MoveCell(RightCell ri, LeftCell le, UpCell u, DownCell d)
    {
     leftCell = le;
     rightCell = ri;
     upCell = u;
     downCell = d;

    }

    public String MakeMove()
    {
        right= rightCell.fire(); let = leftCell.fire(); up= upCell.fire(); down = downCell.fire();
        double xAxis = right - let;
        double yAxis = up - down;
        //System.out.println( "left  " +let + " right    " + right +"up   " + up + "down  " + down);
        if (Math.abs(xAxis) > Math.abs(yAxis))
        {
            if(right > let)
            {
                return  "RIGHT";
            }
            else return "LEFT";
        }
        else
        {
            if (up > down) {
                return "UP";
            } else return "DOWN";
        }

        /*
        if(right > left && right > up&& right > down)
        { return "RIGHT";}
        if(left > right && left > up&& left > down)
        { return "LEFT";}
        if(up > left && up > right & up > down)
        { return "UP";}
        if(down > left && down > up&& down > right)
        { return "DOWN";}
        */
    }
    public static double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }
}
