
import java.util.*;

public class Main
{
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        MaquinaTuring MT = new MaquinaTuring();
        Reader reader = new Reader("arquivo.txt");
        String estadoAtual;
        
        String[][] afp = reader.readFile(MT);
        MT.setAFP(afp);
        LinkedList mtTransitions = MT.mapTransitionsFunctions (afp);
        MT.printConvertedTransitions(mtTransitions);
        System.out.print("Insira uma palavra para ser reconhecida: ");
        // entrada = in.nextLine();
        String entrada = "010010110110";
        MT.setEntrada(entrada);

        MT.primeiraTransicaoPadrao();
        MT.popularEntrada(entrada);

        estadoAtual = MT.getEstadoInicial();

		System.out.print("\n");
        do {
			MT.printConfiguration(estadoAtual);
			estadoAtual = MT.transicao(estadoAtual);
		} while (estadoAtual != null && !MT.linguagemReconhecida(estadoAtual));
		
		if (estadoAtual != null){
            MT.printConfiguration(estadoAtual);
			System.out.println("\n--> A palavra pertence à liguagem.");
        }else{
			System.out.println("\n--> A palavra não pertence à liguagem.");
    }
    }
}
