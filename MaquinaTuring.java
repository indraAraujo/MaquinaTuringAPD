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




}
