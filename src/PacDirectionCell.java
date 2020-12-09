public class PacDirectionCell
{
    //gets the direction of pacman and returns
    private PacPlayer pac;
    public PacDirectionCell(PacPlayer p)
    {
        pac = p;
    }
    public int fire()
    { //1 is up, 2 is down, 3 is left, 4 is right
        int lx = pac.getLastX();
        int ly = pac.getLastY();
        int x = pac.getX();
        int y = pac.getY();
        if(lx == x)
        {
            if(ly == y)
            {
                return 0; //no movement change
            }
            else
            {
                int change = ly-y;
                if(change>0) return 1; //up
                else return 2; //down
            }
        }
        int change = lx-x;
        if(change>0) return 3;//left
        else return 4;//right
    }

}
