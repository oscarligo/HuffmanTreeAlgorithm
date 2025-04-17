import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
    
        Reader reader = new Reader();
        
        reader.readFile("input.txt");

        reader.calculateFrequency();

        HashMap<Character, Double> percentage = reader.getCharFrequency();
        for (Character c : percentage.keySet()) {
            System.out.println( "'"+ c + "' " + percentage.get(c));
        }
        
        
        
    }
}
