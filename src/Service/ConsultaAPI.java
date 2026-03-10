package Service;

import Modelos.Moeda;
import com.google.gson.Gson;
import config.variables;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {
    public static class ConsultaRequest {
        private final String from;
        private final String to;
        private final double value;

        public ConsultaRequest(String from, String to, double value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public String getFrom() {
            return from;
        }
        public String getTo() {
            return to;
        }
        public double getValue() {
            return value;
        }
    }

    public Moeda Consultar() {
        ConsultaRequest request = new ConsultaRequest("USD", "BRL", 100);
        return Consultar(request);
    }

    public Moeda Consultar(ConsultaRequest consultaRequest) {
        String query = "https://v6.exchangerate-api.com/v6/" + variables.API_KEY + "/pair/" +
                consultaRequest.getFrom() + "/" + consultaRequest.getTo() + "/" + consultaRequest.getValue();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Moeda.class);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao consultar API de moedas: " + e.getMessage(), e);
        }
    }
}
