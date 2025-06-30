import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 80");
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            try {
                String response = "Hello from Java 21 HTTP Server!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
