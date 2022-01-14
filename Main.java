import java.util.Scanner;

public class Main{
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        MaquinaTuring MT = new MaquinaTuring();
        Reader reader = new Reader("arquivo.txt");
        String entrada;
        String estado_atual;

        reader.readFile(MT);
/*
        System.out.println("Insira uma palavra para ser reconhecida \n");
        entrada = input.nextLine();

        MT.primeiraTransicaoPadrao();
        MT.popularEntrada(entrada);

        estado_atual = MT.getEstadoInicial();

        while(MT.linguagemReconhecida(estado_atual)){
           String temp;
            temp = MT.transicao(estado_atual);
            estado_atual = temp;
        }
  */      


    }

}