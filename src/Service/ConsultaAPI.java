package Service;

import Modelos.Moeda;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultaAPI {
    public void consultar() throws IOException, InterruptedException {
        Scanner moeda = new Scanner(System.in);
        System.out.println("Digite a moeda que deseja buscar:");
        var moedaBuscada = moeda.nextLine();
        String query = "https://v6.exchangerate-api.com/v6/510da90dd751c36891f4c11c/latest/" + moedaBuscada;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        Gson gson = new Gson();
        gson.fromJson(json, Moeda.class);
    }
}
