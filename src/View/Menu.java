package View;

import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);

    public void ExibirMenu() {
        ExibirBarra();
        CentralizarMensagem("Bem vindo ao",40);
        CentralizarMensagem("Conversor de Moedas",40);
        ExibirBarra();
        System.out.println("Informe o a conversão que deseja Realizar:");

    }
    public void Clean(){
        System.out.close();
    }
    private void ExibirBarra(){
        String barra = "";
        for(int i=0; i<=40 ; i++){
            barra = barra.concat("=");
        }
        System.out.println(barra);
    }
    private void CentralizarMensagem (String mensagem, Integer maxLetter){
        var lettersMessage = mensagem.length();
        if (lettersMessage > maxLetter){
            System.out.println(mensagem);
            return;
        }
        var adicionar = (maxLetter/2)-(lettersMessage/2);
        String espacos = "";
        for (int i=0; i<adicionar; i++){
            espacos = espacos.concat(" ");
        }
        System.out.println(espacos.concat(mensagem));
    }
}
