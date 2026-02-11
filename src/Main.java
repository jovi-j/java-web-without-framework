import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        String hostname = "localhost";
        String root = "/";
        String resposta = "Hello!";

        InetSocketAddress addr = new InetSocketAddress(hostname, port);
        HttpServer server = HttpServer.create(addr, 0);
        server.createContext(
            root,
            (exchange -> {
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, resposta.getBytes().length);
                exchange.getResponseBody().write(resposta.getBytes());
                exchange.close();
            })
        );

        server.start();
        System.out.println("Server iniciado em " + hostname + ":" + port);
    }
}
