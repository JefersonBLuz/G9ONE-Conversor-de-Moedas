package View;

import Modelos.Moeda;
import Uteis.CentralizadorString;
import Uteis.ExibirBarra;

import java.util.Locale;

public class Result {
    private final Integer maxLetter;

    public Result(Integer maxLetter) {
        this.maxLetter = maxLetter;
    }

    public void ExibirResultado(String from, String to, double valorInserido, Moeda moeda) {
        ExibirBarra.exibirBarra(maxLetter);
        CentralizadorString.CentralizadorMenssagem("Cotacao atual", maxLetter);

        String taxaFormatada = String.format(Locale.US, "%.2f", moeda.getConversion_rate());
        String valorInseridoFormatado = String.format(Locale.US, "%.2f", valorInserido);
        String valorConvertidoFormatado = String.format(Locale.US, "%.2f", moeda.getConversion_result());

        System.out.println(obterSimbolo(from) + "1 (" + from + ") -> " +
                obterSimbolo(to) + " " + taxaFormatada + " (" + to + ")");
        System.out.println("Valor inserido: " + obterSimbolo(from) + valorInseridoFormatado + " (" + from + ")");
        System.out.println("Valor convertido: " + obterSimbolo(to) + " " + valorConvertidoFormatado + " (" + to + ")");
        ExibirBarra.exibirBarra(maxLetter);
    }

    private String obterSimbolo(String moeda) {
        return switch (moeda) {
            case "USD" -> "$";
            case "BRL" -> "R$";
            case "ARS" -> "AR$";
            case "BOB" -> "Bs";
            case "CLP" -> "CLP$";
            case "COP" -> "COP$";
            default -> moeda + " ";
        };
    }
}
