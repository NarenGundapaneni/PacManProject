public class OtherGCellThree
{
    GhostPlayer one;
    GhostPlayer two;
    public OtherGCellThree(GhostPlayer on, GhostPlayer tw)
    {
        one = on;
        two = tw;
        System.out.println("one is a go");
    }
    public double fire()
    {   return 0;
    /*
        int X1 = one.getX();
        int Y1 = one.getY();
        int X2 = two.getX();
        int Y2 = two.getY();
        return Math.hypot(X2-X1, Y2-Y1);
        */
    }
}
