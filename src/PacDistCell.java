public class PacDistCell
{
    //gets the distance of pacman and returns
    private PacPlayer pac;
    private GhostPlayer gho;
    public PacDistCell(PacPlayer p, GhostPlayer g)
    {
        pac = p;
        gho = g;
    }
    public double fire()
    {
        int px = pac.getX();
        int py = pac.getY();
        int gx = gho.getX();
        int gy = gho.getY();
        return Math.hypot(px-gx, py-gy);
    }

}
