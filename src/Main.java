import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        String hostname = "localhost";
        String root = "/api/";
        String resposta = "{\"message\": \"Hello World!\"}";
        String POSTresposta = "{\"message\": \"Foi Post!\"}";

        InetSocketAddress addr = new InetSocketAddress(hostname, port);
        HttpServer server = HttpServer.create(addr, 0);
        server.createContext(
            root,
            (exchange -> {
                boolean isPost = exchange
                    .getRequestMethod()
                    .equalsIgnoreCase("POST");
                exchange
                    .getResponseHeaders()
                    .set("Content-Type", "application/json");

                exchange.sendResponseHeaders(
                    200,
                    isPost
                        ? POSTresposta.getBytes().length
                        : resposta.getBytes().length
                );
                exchange
                    .getResponseBody()
                    .write(
                        isPost ? POSTresposta.getBytes() : resposta.getBytes()
                    );
                exchange.close();
            })
        );

        server.start();

        System.out.println("Server iniciado em " + hostname + ":" + port);
    }
}
