package Uteis;

public class CentralizadorString {
    public static void CentralizadorMenssagem(String mensagem, Integer maxLetter) {
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
