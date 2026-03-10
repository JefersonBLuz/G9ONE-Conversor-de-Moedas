package Uteis;

public class ExibirBarra {
    public static void exibirBarra(Integer maxLetter) {
        String barra = "";
        for (int i = 0; i < maxLetter; i++) {
            barra = barra.concat("=");
        }
        System.out.println(barra);
    }
}
