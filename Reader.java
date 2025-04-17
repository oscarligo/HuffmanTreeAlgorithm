import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase encargada de leer los caracteres de un archivo de texto y almacenarlos 
 * en un mapa con su respectiva frecuencia.
 */
public class Reader {


    private ArrayList<Character> charList; // Lista de caracteres
    private HashMap<Character, Double> charPercentage; // Mapa de frecuencia de caracteres

    /**
     * Constructor de la clase Reader.
     */
    public Reader() {
        charPercentage = new HashMap<>();
        charList = new ArrayList<>();
    }


    public HashMap<Character, Double> getCharFrequency() {
        return charPercentage;
    }

    public ArrayList<Character> getCharList() {
        return charList;
    }

    /**
     * Método para leer un archivo de texto y colocar cada carácter en un arraylist.
     * @param filePath Ruta del archivo a leer.
     */

    public void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    charList.add(c); 
                }        
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método para calcular la frecuencia relativa de cada carácter en porcentaje.
     */

    public void calculateFrequency() {
        int totalChars = charList.size();
        if (totalChars == 0) return;
        
        // Crear el mapa con caracteres únicos
        for (char c : charList) {
            if (!charPercentage.containsKey(c)) {
                charPercentage.put(c, 0.0);
            }
        }

        // Contar la frecuencia de cada carácter
        for (char c : charList) {
            double count = charPercentage.get(c);
            count++;
            charPercentage.put(c, count);
        }
        // Calcular el porcentaje y asignarlo al mapa
        for (char c : charPercentage.keySet()) {
            double percentage = charPercentage.get(c);
            percentage = (percentage / totalChars);
            charPercentage.put(c, percentage);
        }

    }
}