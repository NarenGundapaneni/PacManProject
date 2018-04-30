import mayflower.*;
import mayflower.net.*;
import java.util.Scanner;

public class PacManClient extends Client
{
    private StageOne stage;
    private PacMan game;

    public PacManClient()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Use localhost to connect to a server running on your computer.");
        System.out.print("IP Address > ");
        String ip = in.next();

        //System.out.print("Port > ");
        //int port = in.nextInt();
        int port = 1234; //default port

        System.out.println("Connecting...");
        connect(ip, port);
    }

    /*
     *  Messages:
     *      youare [type]
     *      move [type] [direction]
     *      winner disconnect
     *      error [message...]
     */

    public void process(String message)
    {
        //output the message recieved from the server. This is useful for debugging
        System.out.println("Message from server: " + message);

        String[] parts = message.split(" ");

        //use the first value in the array as the "command"
        //use if statements to determine which command to process
        if("youare".equals(parts[0]))
        {
            PlayerType yourPlayer;
            game = new PacMan();
            if(parts[1].equals("PacMan"))
                yourPlayer = PlayerType.PacMan;
            else if(parts[1].endsWith("0"))
                yourPlayer = PlayerType.Ghost0;
            else if(parts[1].endsWith("1"))
                yourPlayer = PlayerType.Ghost1;
            else if(parts[1].endsWith("2"))
                yourPlayer = PlayerType.Ghost2;
            else
                yourPlayer = PlayerType.Ghost3;
            //stage = new StageOne(this, game, yourPlayer);
            //new Mayflower("PacMan", 700, 725, stage);
        }
        else if("movePlayer".equals(parts[0]))
        {
            PlayerType player;
            if(parts[1].equals("PacMan"))
                player = PlayerType.PacMan;
            else if(parts[1].endsWith("0"))
                player = PlayerType.Ghost0;
            else if(parts[1].endsWith("1"))
                player = PlayerType.Ghost1;
            else if(parts[1].endsWith("2"))
                player = PlayerType.Ghost2;
            else
                player = PlayerType.Ghost3;

            String direction = parts[2];

            //game.movePlayer(player, direction);
            //stage.movePlayer(player, direction);
        }
        else if("error".equals(parts[0]))
            System.out.println(message);
        else if("winner".equals(parts[0]))
            System.out.println("Opponent disconnected. You win!");

    }

    public void onConnect()
    {
        System.out.println("Connected!");
    }

    public void onDisconnect(String s)
    {
        System.out.println("Disconnected from server.");
    }
}



