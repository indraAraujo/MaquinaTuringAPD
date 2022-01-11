import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

public class MaquinaTuring {

    char fita1[]; // entrada
    int cabecote1 = 0; // cabeçote para fita 1

    char fita2[]; // pilha 1
    int cabecote2 = 0; // cabeçote para a fita 2

    char fita3[]; // pilha 2
    int cabecote3 = 0; // cabeçote para a fita 3

    String estadoInicial;
    ArrayList<String> estadosIntermediarios = new ArrayList<>();
    ArrayList<String> estadosFinais = new ArrayList<>();

    public void primeiraTransicaoPadrao() {
        fita1[cabecote1] = 'e'; // palavra vazia inserida
        cabecote1++;
        fita2[cabecote2] = 'e'; // palavra vazia inserida
        cabecote2++;
        fita3[cabecote3] = 'e'; // palavra vazia inserida
        cabecote3++;
    }

    public boolean fitaVazia(char fita[]) {
        boolean vazia = false;
        for (int i = 0; i < fita.length; i++) {
            if (fita[i] == 'e') {
                vazia = true;
            } else {
                vazia = false;
            }
        }
        return vazia;
    }

    public boolean linguagemReconhecida(String estadoAtual) {
        boolean reconheceu = false;
        if (estadosFinais.contains(estadoAtual) && fitaVazia(fita1) && fitaVazia(fita2) && fitaVazia(fita3)) {
            reconheceu = true;
        } else {
            reconheceu = false;
        }
        return reconheceu;
    }

    public static void organizer(ArrayList<String> read) {
        int current = 0;
        int temp = 0;
        char f;
        for (int count = 0; count < read.size(); count++) {
            String[] words = read.get(count).split(" >");
            for (int i = 0; i < words.length; i++) {
                if (!words[i].trim().isEmpty()){
                temp = count-(current*7);
                switch (temp) {
                    case 0:
                    System.out.println("-----------------------------");
                        System.out.println("estado-atual: "+words[i]);
                        break;
                    case 1:
                        System.out.println("simbolo-lido: "+words[i]);
                        break;
                    case 2:
                        System.out.println("topo-pilha1: "+words[i]);
                        break;
                    case 3:
                        System.out.println("topo-pilha2: "+words[i]);
                        break;
                    case 4:
                        System.out.println("proximo-estado: "+words[i]);
                        break;
                    case 5:
                        System.out.println("escrita-pilha1: "+words[i]);
                        break;
                    case 6:
                        current++;
                        System.out.println("escrita-pilha2: "+words[i]);
                        break;
                }
            }
            }
        }
        System.out.println("-----------------------------");
    }

}
