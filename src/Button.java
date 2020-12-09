
import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import mayflower.World;

public class Button extends Actor {
    World worldo;

    public Button(String path, World worldSet, int x, int y) {
        MayflowerImage pic = new MayflowerImage(path);
        pic.scale(x, y);
        this.setImage(pic);
        this.worldo = worldSet;
    }

    public void act() {
        if (Mayflower.mouseClicked(this)) {
            Mayflower.setWorld(this.worldo);
        }

    }
}