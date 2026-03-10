
package View;

import Modelos.Moeda;
import Service.ConsultaAPI;
import Uteis.CentralizadorString;
import Uteis.ExibirBarra;

import java.util.Scanner;

public class Menu {
    private final Scanner input = new Scanner(System.in);
    private final Integer maxLetter = 45;
    private final ConsultaAPI consultaAPI = new ConsultaAPI();
    private final Result resultView = new Result(maxLetter);

    public void Iniciar() {
        boolean executando = true;

        while (executando) {
            ExibirMenu();
            int opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1 -> executando = processarConversao("USD", "BRL");
                case 2 -> executando = processarConversao("BRL", "USD");
                case 3 -> executando = processarConversao("BRL", "ARS");
                case 4 -> executando = processarConversao("BRL", "BOB");
                case 5 -> executando = processarConversao("BRL", "CLP");
                case 6 -> executando = processarConversao("BRL", "COP");
                case 7 -> executando = false;
                default -> {
                    System.out.println("Opcao invalida. Tente novamente.");
                    aguardarEnter();
                }
            }
        }
        System.out.println("Programa encerrado.");
    }

    public void ExibirMenu() {
        ExibirBarra.exibirBarra(maxLetter);
        CentralizadorString.CentralizadorMenssagem("Bem vindo ao Conversor de Moedas", maxLetter);
        ExibirBarra.exibirBarra(maxLetter);
        System.out.println("Selecione uma opcao:");
        System.out.println("1 - USD -> BRL");
        System.out.println("2 - BRL -> USD");
        System.out.println("3 - BRL -> ARS");
        System.out.println("4 - BRL -> BOB");
        System.out.println("5 - BRL -> CLP");
        System.out.println("6 - BRL -> COP");
        System.out.println("7 - Sair");
        ExibirBarra.exibirBarra(maxLetter);
    }

    private int lerOpcaoMenu() {
        System.out.print("Opcao: ");
        String linha = input.nextLine();
        try {
            return Integer.parseInt(linha.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double lerValor(String moedaOrigem) {
        while (true) {
            System.out.print("Informe o valor em " + moedaOrigem + ": ");
            String linha = input.nextLine().replace(",", ".").trim();
            try {
                double valor = Double.parseDouble(linha);
                if (valor <= 0) {
                    System.out.println("Digite um valor maior que zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite apenas numeros.");
            }
        }
    }

    private boolean processarConversao(String from, String to) {
        boolean noFluxoConversao = true;

        while (noFluxoConversao) {
            double valor = lerValor(from);
            ConsultaAPI.ConsultaRequest request = new ConsultaAPI.ConsultaRequest(from, to, valor);
            Moeda resposta = consultaAPI.Consultar(request);

            resultView.ExibirResultado(from, to, valor, resposta);

            int acao = switchPosResultado();
            switch (acao) {
                case 1 -> noFluxoConversao = false;
                case 2 -> noFluxoConversao = true;
                case 3 -> {
                    return false;
                }
                default -> {
                    System.out.println("Opcao invalida. Retornando ao menu.");
                    aguardarEnter();
                    noFluxoConversao = false;
                }
            }
        }
        return true;
    }

    private int switchPosResultado() {
        System.out.println("1 - Voltar ao menu");
        System.out.println("2 - Calcular novo valor");
        System.out.println("3 - Encerrar");
        System.out.print("Opcao: ");
        String linha = input.nextLine();
        try {
            return Integer.parseInt(linha.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void aguardarEnter() {
        System.out.print("Pressione ENTER para continuar...");
        input.nextLine();
    }
}
