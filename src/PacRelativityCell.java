public class PacRelativityCell
{


        //gets the distance of pacman and returns
        private PacPlayer pac;
        private GhostPlayer gho;
        public PacRelativityCell(PacPlayer p, GhostPlayer g)
        {
            pac = p;
            gho = g;
        }
        public double fireX()
        {
            int px = pac.getX();
            int py = pac.getY();
            int gx = gho.getX();
            int gy = gho.getY();
            return px-gx;
        }
        public double fireY()
        {
            int px = pac.getX();
            int py = pac.getY();
            int gx = gho.getX();
            int gy = gho.getY();
            return py-gy;
        }

    }

