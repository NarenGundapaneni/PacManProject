public class Node
{
    private int score;
    private String name;
    private String result;
    public Node(String x)
    {
        result = x;
        String [] arrOfStr= x.split("--");
        name = arrOfStr[0];
        score = Integer.parseInt(arrOfStr[1]);

    }
    public Node (int x, String person)
    {
        result = person + "--" + x;
        name = person;
        score = x;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
    public Boolean realNode(int into)
    {
        if(into == score)
        { return true;
        }
        else return false;    }

}
