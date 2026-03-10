package View;

import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);

    public void ExibirMenu() {
        ExibirBarra();
        System.out.println("Bem vindo ao\nConversor de Moedas");
        ExibirBarra();
        System.out.println("Informe o a conversão que deseja Realizar");

    }
    public void Clean(){
        System.out.close();
    }
    private void ExibirBarra(){
        String barra = "";
        for(int i=0; i<=20 ; i++){
            barra = barra.concat("=");
        }
    }
}
