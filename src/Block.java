import mayflower.Actor;

public class Block extends Actor {
    private int type;

    public Block(int t) {
        this.type = t;
        this.setImage("img/block" + this.type + ".jpg");
    }

    public void act() {
    }

    public int getType() {
        return this.type;
    }
}
