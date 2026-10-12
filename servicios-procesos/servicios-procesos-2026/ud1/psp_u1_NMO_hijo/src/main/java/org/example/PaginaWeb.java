package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class PaginaWeb {

    public PaginaWeb() {}

    public static void main(String[] args) throws IOException {

        try{
            String url = leer();
            HttpsURLConnection con = conexion(url);
            String html = contenidoURL(con);
            System.out.println(html);

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static String leer() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return br.readLine();
        }
    }

    private static HttpsURLConnection conexion(String urlContent) throws IOException{
        URL url = new URL(urlContent);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        return con;
    }

    private static String contenidoURL(HttpsURLConnection con) throws IOException {
        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))){
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }finally{
            con.disconnect();
        }
    }
}