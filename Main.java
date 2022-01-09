public class Main{
    public static void main(String args[]) {
        Reader reader = new Reader("arquivo.txt");

        System.out.println(reader.readFile().toString());
        
    }

}