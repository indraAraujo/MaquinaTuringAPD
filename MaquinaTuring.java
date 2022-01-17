
import java.util.*;
import javax.sound.sampled.SourceDataLine;

public class MaquinaTuring
{
    char fita1[]; // entrada
    int cabecote1 = 0; // cabeçote para fita 1

    char fita2[]; // pilha 1
    int cabecote2 = 0; // cabeçote para a fita 2

    char fita3[]; // pilha 2
    int cabecote3 = 0; // cabeçote para a fita 3

	String[][] m;
	String entrada;
    String estadoInicial;
    ArrayList<String> estadosIntermediarios = new ArrayList<>();
    ArrayList<String> estadosFinais = new ArrayList<>();
    
    public MaquinaTuring()
    {
		fita1 = new char[200];
		fita2 = new char[200];
		fita3 = new char[200];
		
		for(int i = 0; i < 200; i++)
			fita1[i] = 'e';
		for(int i = 0; i < 200; i++)
			fita2[i] = 'e';
		for(int i = 0; i < 200; i++)
			fita3[i] = 'e';
	}

	/* recebe a matriz correspondente às transições do AFP, e devolve uma
	   lista encadeada, cujos nós são matrizes contendo as transições
	   convertidas para a Máquina de Turing */
    public LinkedList mapTransitionsFunctions (String[][] afp)
    {
		// formato de um nó da lista: [est. atual] [simb. fita] [prox estado] [esc. na fita] [mov.]
		LinkedList tm = new LinkedList();
		
		int i = 0;
		while (i < Reader.numberOfTransitions) {
			String[][] transition = new String[3][5];
			String currentState = afp[i][0];
			String nextState = afp[i][4];
			
			transition[0][0] = currentState; 
			transition[0][1] = afp[i][1]; 
			transition[0][2] = nextState; 
			transition[0][3] = "E"; 
			transition[0][4] = "D"; 
			
			transition[1][0] = currentState; 
			transition[1][1] = afp[i][2]; 
			transition[1][2] = nextState; 
			transition[1][3] = afp[i][5]; 
			transition[1][4] = "D"; 
			
			transition[2][0] = currentState; 
			transition[2][1] = afp[i][3]; 
			transition[2][2] = nextState; 
			transition[2][3] = afp[i][6]; 
			transition[2][4] = "D"; 
			
			i++;
			tm.add (transition);
		}
		
		return tm;
	}
    
    /* imprime as transições adaptadas à máquina de turing, por sua
       vez recebidas como parâmetro */
    public void printConvertedTransitions (LinkedList tm)
    {
		ListIterator itr = tm.listIterator(0);
		
		System.out.println("\n:::: Transições geradas para a Máquina de Turing: ::::");
		while (itr.hasNext()) {
			System.out.println("\n\n\t-> Transição " + itr.nextIndex() + ":");
			String[][] transition = (String[][]) itr.next();
			int i = 0;
			while (i < 3) {
				System.out.print("\n\t\tFita " + i + ":");
				System.out.print("\t\t[" + transition[i][0] + "]" + 
										" [" + transition[i][1] + "]" +
											" [" + transition[i][2] + "]" +
												" [" + transition[i][3] + "]" +
													" [" + transition[i][4] + "]");
				i ++;
			}
		}
		System.out.println("\n");
	}
    
    public void primeiraTransicaoPadrao(){
        fita1[cabecote1] = 'e'; //palavra vazia inserida
        cabecote1++;
        fita2[cabecote2] = 'e'; // palavra vazia inserida
        cabecote2++;
        fita3[cabecote3] = 'e'; // palavra vazia inserida
        cabecote3++;
    }

    public void popularEntrada(String entry)
    {
        char entrada_split[] = entry.toCharArray();
        for (int i = 0; i < entrada_split.length; i++) {
            fita1[i + 1] = entrada_split[i];
        }
        System.out.println("");
    }

    public String getEstadoInicial()
    {
        return m[0][0];
        // return estadoInicial;
    }

    public boolean linguagemReconhecida(String estadoAtual)
    {
        boolean result = false;
        
        if (estadosFinais.contains(estadoAtual) && fitaVazia(fita1) && fitaVazia(fita2) && fitaVazia(fita3))
            result = true;
            printConfiguration(estadoAtual);

        return result;
    }

	public boolean fitaVazia(char fita[])
	{
        boolean vazia = true;
        
        for (int i = 0; i < fita.length; i++) {
            if (fita[i] != 'e') {
                vazia = false;
                break;
			}
        }
        
        return vazia;
    }

    public void empilhar(char caracter, int fita)
    {
        switch(fita){
            case 2:
                fita2[cabecote2] = caracter;
                cabecote2++;    
                break;
            case 3:
                fita3[cabecote3] = caracter;
                cabecote3++;                
                break;
        }
    }

    public void desempilhar(int fita)
    {
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
    
    // apenas lê o cabeçote, sem consumir a entrada.
    // necessário pois a simples consulta ao cabeçote
    // nos teste de condição em "transição" consumiam
    // o conteúdo do mesmo
    public char checarFita1()
    {
		return fita1[cabecote1];
	}

    public void setStates(ArrayList<String> read)
    {
		this.estadoInicial = read.get(0);
		for(int i=0;i<read.size()-1;i=i+7){
			if(read.get(i).contains(">>")){
				read.set(i, read.get(i).replaceAll(">>", ">"));
				if(!this.estadosFinais.contains(read.get(i))){
					String tmp = read.get(i).replace(">", "");
					this.estadosFinais.add(tmp.trim());
				}
			}
		}
		System.out.println("Estado Inicial => "+this.estadoInicial);
		System.out.println("Estados Finais => "+this.estadosFinais);
    }

	public String transicao(String estadoAtual)
	{
        String proxEstado = null;
        
        for (int i = 0; i < Reader.numberOfTransitions; i++){
            if(estadoAtual.equals(m[i][0]) && isSame(m[i][1], checarFita1()) && isSame(m[i][2], getTopoPilha(2)) && isSame(m[i][3], getTopoPilha(3))){
				lerEntrada();
				if(m[i][2].charAt(0) != 'e') desempilhar(2);
				if(m[i][3].charAt(0) != 'e') desempilhar(3);
				if(m[i][5].charAt(0) != 'e') empilhar(m[i][5].charAt(0), 2);
				if(m[i][6].charAt(0) != 'e') empilhar(m[i][6].charAt(0), 3);
                if(m[i][0].equals("s0") && m[i][4].equals("s1")){
                    cabecote2--;
                    cabecote3--;
                }
				proxEstado = m[i][4];
				break;
            }
        }
        
        return proxEstado;
    }
    
    public void setAFP (String[][] afp)
    {
		this.m = afp;
	}
	
	public void setEntrada (String entrada)
	{
		this.entrada = entrada;
	}
    
    public void printConfiguration(String estado_atual)
    {
		System.out.println("\n--- ");
		printSpecificConfiguration(estado_atual, 1);
		printSpecificConfiguration(estado_atual, 2);
		printSpecificConfiguration(estado_atual, 3);
	}
    
    public void printSpecificConfiguration (String estado_atual, int k)
    {
		char fita[] = null;
		int cabecote = 0;
		
		if (k == 1) {
			fita = fita1;
			cabecote = cabecote1;
		}
		else if (k == 2){
			fita = fita2;
			cabecote = cabecote2;
		}
		else if (k == 3){
			fita = fita3;
			cabecote = cabecote3;
		}
		
		String cabecote_esq = new String();
		for (int i = 0; i < cabecote; i++)
				cabecote_esq += fita[i];
		
		String cabecote_dir = new String();
		for (int i = cabecote + 1; i <= entrada.length(); i++)
				cabecote_dir += fita[i];
		
		System.out.println("K = " + estado_atual + 
								" | CE = " + cabecote_esq +
									" | C = " + fita[cabecote] +
										" | CD = " + cabecote_dir);
	}

    public static void printMat(String[][] m )
    {
        int x,y;
        for(y=0;y<4;y++){
			System.out.print("\t Transição " + y + ": ");
            for(x=0;x<7;x++){
                System.out.print("["+m[y][x]+"] ");
            }System.out.println("");
        }
    }

    public static String[][] organizer(ArrayList<String> read) 
    {
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
        System.out.println("\n:::: Tabela de transições do Autômato de Pilha: ::::\n");
        printMat(m);
        return m;
    }
    
    public boolean isSame(String s, char c) 
    {
		if (s != null && s.length() == 1) { 
			return s.charAt(0) == c;
		}
		return false;
	}
}
