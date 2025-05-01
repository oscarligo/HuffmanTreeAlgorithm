import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase encargada de leer los caracteres de un archivo de texto y almacenarlos 
 * en un mapa con su respectiva frecuencia.
 */
public class Reader {
    
    private ArrayList<Character> charList; // Lista con los caracteres que hay en el archivo
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
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                charList.add((char) character);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método para calcular la frecuencia de los caracteres.
     * - Se obtiene el tamaño del arraylist.
     * - Se ańade al mapa de frecuencias cada carácter único.
     * - Se cuenta el numero de veces que aparece cada carácter.
     * - Se calcula el porcentaje de cada carácter y se asigna al mapa.
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

    /**
     * Método para escrobir un documento con los caracteres en binario utilizando el arbol de Huffman.
     * - Se crea un StringBuilder que contiene cada carácter reemplazado por su código de Huffman.
     * - Se asegura que la longitud del StringBuilder sea un múltiplo de 8 y si no, se agregan ceros.
     * - Se escribe el contenido del StringBuilder en un archivo, pero antes, se divide en bloques de 8
     *   bits que se convierte a decimal y se escribe como un carácter ASCII.
     * @param filePath Ruta del archivo a escribir.
     * @param huffmanCodes Mapa de cada caracter con su respectivo código de Huffman.
     */

    public void writeHuffmanCodesAsAscii(String filePath, HashMap<Character, String> huffmanCodes) {
        
        // StringBuilder para almacenar el contenido binario
        StringBuilder binary = new StringBuilder();
    
        for (char c : charList) {
            String code = huffmanCodes.get(c);
            if (code != null) {
                binary.append(code);
            }
        }
    
        // Para asegurar que la longitud del StringBuilder sea un múltiplo de 8
        int difference = 8 - (binary.length() % 8);
        if (difference != 8) {
            binary.append("0".repeat(difference));
        }
    
        
        // Escribir el contenido en un archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String binaryString = binary.toString();
            for (int i = 0; i < binaryString.length(); i += 8) {
                String byteStr = binaryString.substring(i, i + 8);
                int decimal = Integer.parseInt(byteStr, 2);
                char asciiChar = (char) decimal;
                writer.write(asciiChar);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    /**
     * Método para regresar el archivo comprimido a su estado original.
     * @param CompressedFile Ruta del archivo comprimido.
     * @param DecompressedFile Ruta del archivo descomprimido.
     * @param huffmanCodes Mapa de cada caracter con su respectivo código de Huffman.
     * 
     */

    public void writeHuffmanCodesAsText(String compressedFile, String decompressedFile, HashMap<Character, String> huffmanCodes) {
        // StringBuilder para almacenar el contenido binario
        StringBuilder binary = new StringBuilder();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFile))) {
            int character;
            while ((character = reader.read()) != -1) {
                String byteStr = String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');
                binary.append(byteStr);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    

        StringBuilder decompressedText = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
    
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(i);
            currentCode.append(c);
            if (huffmanCodes.containsValue(currentCode.toString())) {
                for (HashMap.Entry<Character, String> entry : huffmanCodes.entrySet()) {
                    if (entry.getValue().equals(currentCode.toString())) {
                        decompressedText.append(entry.getKey());      
                    }
                }

                currentCode.setLength(0); // vaciar el StringBuilder
            }
            
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(decompressedFile))) {
            writer.write(decompressedText.toString());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
        
    }

    


    
}