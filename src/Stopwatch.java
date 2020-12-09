public class Stopwatch
{
    private long start;
    public Stopwatch()
    {
        start = System.currentTimeMillis();
    }
    public long Lap()
    {
        return System.currentTimeMillis()- start / 1000;
    }

}
