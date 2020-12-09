
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import mayflower.Mayflower;
import mayflower.World;

public class GameOver extends World {
    private String userName;
    private int userScore;

    public GameOver(int score) {
        this.setBackground("img/gameOver.jpg");
        this.showText("Enter Name:", 300, 350);
        this.userName = "";
        this.userScore = score;
    }

    public void act() {
        if (this.userName == "") {
            Scanner s = new Scanner(System.in);
            this.userName = s.nextLine();
            this.fileWrite(this.userName + " " + this.userScore, "src/scores.txt", true);
            System.out.println("Thank you for your personal information. Please close window.");
            Mayflower.stop();
        }

    }

    public void fileWrite(String text, String filename, boolean overwrite) {
        try {
            FileWriter fw = new FileWriter(filename, overwrite);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw, true);
            out.println(text);
            out.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }
}
