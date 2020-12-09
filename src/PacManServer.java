
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import mayflower.net.Server;

public class PacManServer extends Server {
    private Queue<Integer> clientsWaitingForGame = new LinkedList();
    private Map<Integer, PacMan> games = new HashMap();
    private Map<Integer, Integer[]> otherPlayers = new HashMap();
    private Set<Integer> pacClients = new HashSet();
    private Set<Integer> ghostClients = new HashSet();

    public PacManServer(int port) {
        super(port);
        System.out.println("Waiting for clients on port " + this.getPort() + " at " + this.getIP());
    }

    public void process(int id, String message) {
        System.out.println("Message from client " + id + ": " + message);
        PacMan var10000 = (PacMan)this.games.get(id);
        String[] parts = message.trim().split(" ");
        if (parts.length >= 3) {
            try {
                if (parts[0].equals("movePlayer")) {
                    String player = parts[1];
                    String direction = parts[2];
                    String response = "movePlayer " + player + " " + direction;
                    this.send(id, response);
                    Integer[] var8 = (Integer[])this.otherPlayers.get(id);
                    int var9 = var8.length;

                    for(int var10 = 0; var10 < var9; ++var10) {
                        int otherID = var8[var10].intValue();
                        this.send(otherID, response);
                    }
                }
            } catch (Exception var12) {
                this.send(id, "error invalid request: [" + message + "]");
            }
        } else {
            this.send(id, "error invalid request: [" + message + "]");
        }

    }

    public void onJoin(int id) {
        this.clientsWaitingForGame.add(id);
        System.out.println("Client connected: " + id);
        if (this.clientsWaitingForGame.size() >= 5) {
            int client0 = ((Integer)this.clientsWaitingForGame.remove()).intValue();
            int client1 = ((Integer)this.clientsWaitingForGame.remove()).intValue();
            int client2 = ((Integer)this.clientsWaitingForGame.remove()).intValue();
            int client3 = ((Integer)this.clientsWaitingForGame.remove()).intValue();
            int client4 = ((Integer)this.clientsWaitingForGame.remove()).intValue();
            this.otherPlayers.put(client0, new Integer[]{client1, client2, client3, client4});
            this.otherPlayers.put(client1, new Integer[]{client0, client2, client3, client4});
            this.otherPlayers.put(client2, new Integer[]{client0, client1, client3, client4});
            this.otherPlayers.put(client3, new Integer[]{client0, client1, client2, client4});
            this.otherPlayers.put(client4, new Integer[]{client0, client1, client2, client3});
            PacMan game = new PacMan();
            this.games.put(client0, game);
            this.games.put(client1, game);
            this.games.put(client2, game);
            this.games.put(client3, game);
            this.games.put(client4, game);
            int r = (int)(Math.random() * 5.0D);
            switch(r) {
                case 0:
                    this.pacClients.add(client0);
                    this.ghostClients.add(client1);
                    this.ghostClients.add(client2);
                    this.ghostClients.add(client3);
                    this.ghostClients.add(client4);
                    this.send(client0, "youare PacMan");
                    this.send(client1, "youare Ghost0");
                    this.send(client2, "youare Ghost1");
                    this.send(client3, "youare Ghost2");
                    this.send(client4, "youare Ghost3");
                    break;
                case 1:
                    this.pacClients.add(client1);
                    this.ghostClients.add(client0);
                    this.ghostClients.add(client2);
                    this.ghostClients.add(client3);
                    this.ghostClients.add(client4);
                    this.send(client0, "youare Ghost0");
                    this.send(client1, "youare PacMan");
                    this.send(client2, "youare Ghost1");
                    this.send(client3, "youare Ghost2");
                    this.send(client4, "youare Ghost3");
                    break;
                case 2:
                    this.pacClients.add(client2);
                    this.ghostClients.add(client0);
                    this.ghostClients.add(client1);
                    this.ghostClients.add(client3);
                    this.ghostClients.add(client4);
                    this.send(client0, "youare Ghost0");
                    this.send(client1, "youare Ghost1");
                    this.send(client2, "youare PacMan");
                    this.send(client3, "youare Ghost2");
                    this.send(client4, "youare Ghost3");
                    break;
                case 3:
                    this.pacClients.add(client3);
                    this.ghostClients.add(client0);
                    this.ghostClients.add(client1);
                    this.ghostClients.add(client2);
                    this.ghostClients.add(client4);
                    this.send(client0, "youare Ghost0");
                    this.send(client1, "youare Ghost1");
                    this.send(client2, "youare Ghost2");
                    this.send(client3, "youare PacMan");
                    this.send(client4, "youare Ghost3");
                    break;
                case 4:
                    this.pacClients.add(client4);
                    this.ghostClients.add(client0);
                    this.ghostClients.add(client1);
                    this.ghostClients.add(client2);
                    this.ghostClients.add(client3);
                    this.send(client0, "youare Ghost0");
                    this.send(client1, "youare Ghost1");
                    this.send(client2, "youare Ghost2");
                    this.send(client3, "youare Ghost3");
                    this.send(client4, "youare PacMan");
            }
        }

    }

    public void onExit(int id) {
        System.out.println("Client disconnected: " + id);
        if (this.clientsWaitingForGame.contains(id)) {
            this.clientsWaitingForGame.remove(id);
        }

        PacMan game = (PacMan)this.games.get(id);
        if (game != null) {
            for(int i = 0; i < 4; ++i) {
                this.send(((Integer[])this.otherPlayers.get(id))[i].intValue(), "winner disconnect");
            }

            this.endGame(id, (Integer[])this.otherPlayers.get(id));
        }

    }

    private void endGame(int clientA, Integer[] otherClients) {
        this.disconnect(clientA);
        this.otherPlayers.remove(clientA);
        this.games.remove(clientA);
        this.pacClients.remove(clientA);
        this.ghostClients.remove(clientA);
        Integer[] var3 = otherClients;
        int var4 = otherClients.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int id = var3[var5].intValue();
            this.disconnect(id);
            this.otherPlayers.remove(id);
            this.games.remove(id);
            this.pacClients.remove(id);
            this.ghostClients.remove(id);
        }

    }
}
