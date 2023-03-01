package Server;

public class StartServerThread implements Runnable {
    int port ;

    public StartServerThread(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Main.startServer(port);
    }
}
