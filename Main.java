public class Main{
    public static void main(String args[]) {
        MaquinaTuring MT = new MaquinaTuring();
        Reader reader = new Reader("arquivo.txt");
        reader.readFile(MT);
    }

}