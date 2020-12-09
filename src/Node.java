
public class Node {
    private int score;
    private String name;
    private String result;

    public Node(String x) {
        this.result = x;
        String[] arrOfStr = x.split("--");
        this.name = arrOfStr[0];
        this.score = Integer.parseInt(arrOfStr[1]);
    }

    public Node(int x, String person) {
        this.result = person + "--" + x;
        this.name = person;
        this.score = x;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public String getResult() {
        return this.result;
    }

    public Boolean realNode(int into) {
        return into == this.score ? true : false;
    }
}
