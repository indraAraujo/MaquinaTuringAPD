import java.util.ArrayList;

public class MaquinaTuring {

    char fita1[]; //entrada 
    int cabecote1=0; //cabeçote para fita 1

    char fita2[]; //pilha 1
    int cabecote2=0; //cabeçote para a fita 2

    char fita3[]; //pilha 2
    int cabecote3=0; //cabeçote para a fita 3

    String estadoInicial;
    ArrayList<String> estadosIntermediarios = new ArrayList<>();
    ArrayList<String> estadosFinais = new ArrayList<>();

    
    public void primeiraTransicaoPadrao(){
        fita1[cabecote1] = 'e'; //palavra vazia inserida
        cabecote1++;
        fita2[cabecote2] = 'e'; //palavra vazia inserida
        cabecote2++;
        fita3[cabecote3] = 'e'; //palavra vazia inserida
        cabecote3++;
    }

    public void popularEntrada(String entrada){
        char ent[] = entrada.toCharArray();
        for(int i=0; i<ent.length; i++){
            fita1[i++] = ent[i];
        }
    }
    
    public boolean fitaVazia(char fita[]){
        boolean vazia =false;
        for(int i=0; i<fita.length; i++){
            if(fita[i]=='e'){
                vazia=true;
            }else{
                vazia=false;
            }
        }
        return vazia;
    }

    public boolean linguagemReconhecida(String estadoAtual){
        boolean reconheceu = false;
        if(estadosFinais.contains(estadoAtual) && fitaVazia(fita1) && fitaVazia(fita2)&& fitaVazia(fita3)){
            reconheceu=true;
        }else{
            reconheceu=false;
        }
        return reconheceu;
    }

    public void empilhar(char caracter, int fita){
        switch(fita){
            case 2:
                fita2[cabecote2++] = caracter;
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


}
