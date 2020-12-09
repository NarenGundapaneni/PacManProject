
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FileChanger {
    private String file;
    private List<Node> scores;

    public FileChanger(String filename) {
        this.file = filename;
        this.scores = new ArrayList();
    }

    public void addScore(Node ore) {
        this.readFile();
        this.scores.add(ore);
        this.sort();
        this.writeFile();
    }

    public void readFile() {
        try {
            Scanner s = new Scanner(new File(this.file));

            while(s.hasNextLine()) {
                this.scores.add(new Node(s.nextLine()));
            }
        } catch (FileNotFoundException var2) {
            System.out.println("The file was not read...");
            var2.printStackTrace();
        }

    }

    public void writeFile() {
        try {
            FileWriter fw = new FileWriter(this.file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw, true);
            out.println(this.scores);
            out.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void sort() {
        List<Integer> nScore = new ArrayList();
        Iterator var2 = this.scores.iterator();

        while(var2.hasNext()) {
            Node x = (Node)var2.next();
            nScore.add(x.getScore());
        }

        Collections.sort(nScore);
        List<Node> reassign = new ArrayList();
        Iterator var8 = nScore.iterator();

        while(var8.hasNext()) {
            Integer x = (Integer)var8.next();
            Iterator var5 = this.scores.iterator();

            while(var5.hasNext()) {
                Node ode = (Node)var5.next();
                if (ode.realNode(x.intValue()).booleanValue()) {
                    reassign.add(ode);
                }
            }
        }

        this.scores = reassign;
    }
}
