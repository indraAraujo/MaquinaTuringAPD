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

    
    
    public void primeiraTransicaoPadrao(){
        fita1[cabecote1] = 'e'; //palavra vazia inserida
        cabecote1++;
        fita2[cabecote2] = 'e'; // palavra vazia inserida
        cabecote2++;
        fita3[cabecote3] = 'e'; // palavra vazia inserida
        cabecote3++;
    }

    public void popularEntrada(String entrada){
        char entrada_split[] = entrada.toCharArray();
        for (int i=0; i<entrada_split.length; i++){
            fita1[i++] = entrada_split[i];
        } 
    }

    public String getEstadoInicial(){
        return estadoInicial;
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

    public boolean linguagemReconhecida(String estadoAtual){
        boolean nao_reconheceu = true;
        if (estadosFinais.contains(estadoAtual) && fitaVazia(fita1) && fitaVazia(fita2) && fitaVazia(fita3)) {
            nao_reconheceu = false;
        } else {
            nao_reconheceu = true;
        }
        return nao_reconheceu;
    }

    public void empilhar(char caracter, int fita){
        switch(fita){
            case 2:
                fita2[cabecote2] = caracter;
                cabecote2++;
                break;
            case 3:
                fita3[cabecote3++] = caracter;
                cabecote3++;
                break;
        }
        
    }

    public void desempilhar(int fita){
        switch(fita){
            case 2:
                fita2[cabecote2]='e';
                cabecote2--;
                break;
            case 3:
                fita3[cabecote3]='e';
                cabecote3--;
                break;
        }
        
    }

    public char getTopoPilha(int fita){
        char topo ='e';
        switch(fita){
            case 2: 
               topo = fita2[cabecote2]; 
               break;
            case 3:
                topo = fita3[cabecote3];
                break;

        }
        return topo;
    }

    public char lerEntrada(){
        char caracter = fita1[cabecote1];
        fita1[cabecote1]='e';
        cabecote1++;

        return caracter;
    }

    public void setStates(ArrayList<String> read){
    this.estadoInicial = read.get(0);
    for(int i=0;i<read.size()-1;i=i+7){
        if(read.get(i).contains(">>")){
            read.set(i, read.get(i).replaceAll(">>", ">"));
            if(!this.estadosFinais.contains(read.get(i))){
                this.estadosFinais.add(read.get(i));
            }
        }
    }
    System.out.println("Estado Inicial => "+this.estadoInicial);
    System.out.println("Estados Finais => "+this.estadosFinais);
    }

    public String transicao(String estadoAtual ){
        String proxEstado="";
       /* for(....){
            if(estadoAtual = m[i][0] && lerEntrada() == m[i][1] && getTopoPilha(2) == m[i][2] && getTopoPilha(3)==m[i][3]){
                desempilhar(2);
                desempilhar(3);
                empilhar(m[i][5], 1);
                empilhar(m[i][6], 2);
                proxEstado = m[i][4];
                break;
            }
        }*/
        return proxEstado;
    }

    public static void printMat(String[][] m ){
        int x,y;
        for(y=0;y<4;y++){
            for(x=0;x<7;x++){
                System.out.print("["+m[y][x]+"]");
            }System.out.println("");
        }
    }

    public static void organizer(ArrayList<String> read) {
        int current = 0;
        int temp = 0;
        String[][] m = new String[(read.size()/7)+1][8];
        for (int count = 0; count < read.size(); count++) {
            String[] words = read.get(count).split(" >");
            for (int i = 0; i < words.length; i++) {
                if (!words[i].trim().isEmpty()){
                temp = count-(current*7);
                switch (temp) {
                    case 0:
                        m[current][temp] = words[i];
                        break;
                    case 1:
                        if(words[i].equals("> "))
                            words[i] = "";
                        m[current][temp] = words[i];
                        break;
                    case 2:
                        m[current][temp] = words[i];
                        break;
                    case 3:
                        m[current][temp] = words[i];
                        break;
                    case 4:
                        m[current][temp] = words[i];
                        break;
                    case 5:
                        m[current][temp] = words[i];
                        break;
                    case 6:
                        m[current][temp] = words[i];
                        current++;
                        break;
                }

            }
            }
        }
        System.out.println("******************** MATRIZ *******************");
        printMat(m);
        System.out.println("********************* END *********************");
    }

    public void configuracao(String estado_atual){
        // System.out.println("< " + estado_atual + " > " + "< " )
    }
}
