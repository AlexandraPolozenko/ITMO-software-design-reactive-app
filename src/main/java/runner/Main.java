package runner;

import domain.ReactiveMongoDriver;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(new ReactiveMongoDriver());
        server.run();
    }
}
