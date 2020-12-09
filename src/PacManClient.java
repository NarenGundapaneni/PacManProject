
import java.util.Scanner;
import mayflower.net.Client;

public class PacManClient extends Client {
    private StageOne stage;
    private PacMan game;

    public PacManClient() {
        Scanner in = new Scanner(System.in);
        System.out.println("Use localhost to connect to a server running on your computer.");
        System.out.print("IP Address > ");
        String ip = in.next();
        int port = 1234;
        System.out.println("Connecting...");
        this.connect(ip, port);
    }

    public void process(String message) {
        System.out.println("Message from server: " + message);
        String[] parts = message.split(" ");
        PlayerType var3;
        if ("youare".equals(parts[0])) {
            this.game = new PacMan();
            if (parts[1].equals("PacMan")) {
                var3 = PlayerType.PacMan;
            } else if (parts[1].endsWith("0")) {
                var3 = PlayerType.Ghost0;
            } else if (parts[1].endsWith("1")) {
                var3 = PlayerType.Ghost1;
            } else if (parts[1].endsWith("2")) {
                var3 = PlayerType.Ghost2;
            } else {
                var3 = PlayerType.Ghost3;
            }
        } else if ("movePlayer".equals(parts[0])) {
            if (parts[1].equals("PacMan")) {
                var3 = PlayerType.PacMan;
            } else if (parts[1].endsWith("0")) {
                var3 = PlayerType.Ghost0;
            } else if (parts[1].endsWith("1")) {
                var3 = PlayerType.Ghost1;
            } else if (parts[1].endsWith("2")) {
                var3 = PlayerType.Ghost2;
            } else {
                var3 = PlayerType.Ghost3;
            }

            String var4 = parts[2];
        } else if ("error".equals(parts[0])) {
            System.out.println(message);
        } else if ("winner".equals(parts[0])) {
            System.out.println("Opponent disconnected. You win!");
        }

    }

    public void onConnect() {
        System.out.println("Connected!");
    }

    public void onDisconnect(String s) {
        System.out.println("Disconnected from server.");
    }
}
