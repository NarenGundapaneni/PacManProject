import mayflower.net.*;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class PacManServer extends Server {
    private Queue<Integer> clientsWaitingForGame;
    private Map<Integer, PacMan> games;
    private Map<Integer, Integer[]> otherPlayers;
    private Set<Integer> pacClients, ghostClients;

    public PacManServer(int port) {
        super(port);

        clientsWaitingForGame = new LinkedList<Integer>();
        games = new HashMap<Integer, PacMan>();
        otherPlayers = new HashMap<Integer, Integer[]>();
        pacClients = new HashSet<Integer>();
        ghostClients = new HashSet<Integer>();

        System.out.println("Waiting for clients on port " + getPort() + " at " + getIP());

    }

    public void process(int id, String message) {
        System.out.println("Message from client "+id+": "+message);
        //get this client's game
        PacMan game = games.get(id);
        //if(game != null && !game.isGameOver())
        {
            String[] parts = message.trim().split(" ");
            if(parts.length >= 3) {
                try
                {
                    if (parts[0].equals("movePlayer")) {
                        String player = parts[1];
                        String direction = parts[2];

                        //game.movePlayer(parts[1], parts[2]);

                        String response = "movePlayer " + player + " " + direction;
                        send(id, response);
                        for (int otherID : otherPlayers.get(id))
                            send(otherID, response);
                        /*if(game.isGameOver())
                            endGame(id, otherPlayers.get(id));
                        */

                    }
                }
                catch (Exception e)
                {
                    send(id, "error invalid request: ["+message+"]");
                }
            }
            else
                send(id, "error invalid request: ["+message+"]");

        }
        //else
        //send(id, "error game not found");

    }

    public void onJoin(int id)
    {
        clientsWaitingForGame.add(id);
        System.out.println("Client connected: "+id);

        if(clientsWaitingForGame.size() >= 5)
        {
            int client0 = clientsWaitingForGame.remove();
            int client1 = clientsWaitingForGame.remove();
            int client2 = clientsWaitingForGame.remove();
            int client3 = clientsWaitingForGame.remove();
            int client4 = clientsWaitingForGame.remove();

            otherPlayers.put(client0, new Integer[]{client1, client2, client3, client4});
            otherPlayers.put(client1, new Integer[]{client0, client2, client3, client4});
            otherPlayers.put(client2, new Integer[]{client0, client1, client3, client4});
            otherPlayers.put(client3, new Integer[]{client0, client1, client2, client4});
            otherPlayers.put(client4, new Integer[]{client0, client1, client2, client3});

            PacMan game = new PacMan();
            games.put(client0, game);
            games.put(client1, game);
            games.put(client2, game);
            games.put(client3, game);
            games.put(client4, game);

            int r = (int)(Math.random() * 5);
            switch (r)
            {
                case 0 :
                {
                    pacClients.add(client0);
                    ghostClients.add(client1);
                    ghostClients.add(client2);
                    ghostClients.add(client3);
                    ghostClients.add(client4);

                    send(client0, "youare PacMan");
                    send(client1, "youare Ghost0");
                    send(client2, "youare Ghost1");
                    send(client3, "youare Ghost2");
                    send(client4, "youare Ghost3");

                    break;
                }
                case 1 :
                {
                    pacClients.add(client1);
                    ghostClients.add(client0);
                    ghostClients.add(client2);
                    ghostClients.add(client3);
                    ghostClients.add(client4);

                    send(client0, "youare Ghost0");
                    send(client1, "youare PacMan");
                    send(client2, "youare Ghost1");
                    send(client3, "youare Ghost2");
                    send(client4, "youare Ghost3");

                    break;
                }
                case 2 :
                {
                    pacClients.add(client2);
                    ghostClients.add(client0);
                    ghostClients.add(client1);
                    ghostClients.add(client3);
                    ghostClients.add(client4);

                    send(client0, "youare Ghost0");
                    send(client1, "youare Ghost1");
                    send(client2, "youare PacMan");
                    send(client3, "youare Ghost2");
                    send(client4, "youare Ghost3");

                    break;
                }
                case 3 :
                {
                    pacClients.add(client3);
                    ghostClients.add(client0);
                    ghostClients.add(client1);
                    ghostClients.add(client2);
                    ghostClients.add(client4);

                    send(client0, "youare Ghost0");
                    send(client1, "youare Ghost1");
                    send(client2, "youare Ghost2");
                    send(client3, "youare PacMan");
                    send(client4, "youare Ghost3");

                    break;
                }
                case 4 :
                {
                    pacClients.add(client4);
                    ghostClients.add(client0);
                    ghostClients.add(client1);
                    ghostClients.add(client2);
                    ghostClients.add(client3);

                    send(client0, "youare Ghost0");
                    send(client1, "youare Ghost1");
                    send(client2, "youare Ghost2");
                    send(client3, "youare Ghost3");
                    send(client4, "youare PacMan");

                    break;
                }
            }
        }
    }

    public void onExit(int id)
    {
        System.out.println("Client disconnected: " + id);
        //check if this client is waiting in the queue
        if(clientsWaitingForGame.contains(id))
        {
            clientsWaitingForGame.remove(id);
        }

        //check if this player is already in a game
        PacMan game = games.get(id);
        if(game != null)
        {
            for(int i = 0; i < 4; i++) {
                send(otherPlayers.get(id)[i], "winner disconnect");

            }
            endGame(id, otherPlayers.get(id));
        }
    }

    private void endGame(int clientA, Integer[] otherClients)
    {
        //disconnect  clients
        //remove the data associated with these clients from data structures:
        //otherPlayer, games, xClients, oClients

        disconnect(clientA);
        otherPlayers.remove(clientA);
        games.remove(clientA);
        pacClients.remove(clientA);
        ghostClients.remove(clientA);

        for(int id : otherClients) {
            disconnect(id);
            otherPlayers.remove(id);
            games.remove(id);
            pacClients.remove(id);
            ghostClients.remove(id);
        }

    }
}