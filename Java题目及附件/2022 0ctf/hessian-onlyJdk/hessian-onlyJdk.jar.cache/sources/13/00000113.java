package com.ctf.hessian.onlyJdk;

import com.caucho.hessian.io.Hessian2Constants;
import com.caucho.hessian.io.Hessian2Input;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/* loaded from: hessian-onlyJdk.jar:com/ctf/hessian/onlyJdk/Index.class */
public class Index {
    public static void main(String[] args) throws Exception {
        System.out.println("server start");
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }

    /* loaded from: hessian-onlyJdk.jar:com/ctf/hessian/onlyJdk/Index$MyHandler.class */
    static class MyHandler implements HttpHandler {
        MyHandler() {
        }

        public void handle(HttpExchange t) throws IOException {
            String response = "Welcome to 0CTF 2022!";
            InputStream is = t.getRequestBody();
            try {
                Hessian2Input input = new Hessian2Input(is);
                input.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                response = "oops! something is wrong";
            }
            t.sendResponseHeaders((int) Hessian2Constants.BC_INT_BYTE_ZERO, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}