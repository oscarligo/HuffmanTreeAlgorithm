import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Clase que representa un árbol de Huffman.
 * Contiene métodos para construir el árbol a partir de un mapa de frecuencias
 * y para generar un mapa de códigos de Huffman.
 */
public class HuffmanTree {


    private HashMap<Character, String> huffmanCodes; 
    private HuffmanNode<Double> root; 


    public HuffmanTree() {
        this.huffmanCodes = new HashMap<>();
    }

    public HashMap<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void setHuffmanCodes(HashMap<Character, String> huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }

    /**
     * Método para construir el árbol de Huffman a partir de un mapa de frecuencias de caracteres.
     * @param charFrequency Mapa de frecuencias de caracteres. 
     */

    public void buildTree(HashMap<Character, Double> charFrequency) {

        // Cola de prioridad con nodos de Huffman
        PriorityQueue<HuffmanNode<Double>> queue = new PriorityQueue<>();

        // Para cada elemento del mapa, se crea un nodo de Huffman y se añade a la cola
        for (HashMap.Entry<Character, Double> entry : charFrequency.entrySet()) {
            queue.add(new HuffmanNode<>(entry.getKey(), entry.getValue()));
        }

        // Mientras haya más de un nodo en la cola, se combinan los dos nodos con menor frecuencia
        // para crear un nuevo nodo padre, se añade a la cola y se repite el proceso hasta darle
        // forma al árbol de Huffman

        while (queue.size() > 1) {

            HuffmanNode<Double> left = queue.poll(); // el nodo con menor frecuencia
            HuffmanNode<Double> right = queue.poll(); // el nodo con la siguiente menor frecuencia

            char parentChar = 0; // el carácter que tendrá nodo padre
            double frequencySum = left.getFrequency() + right.getFrequency(); // suma de frecuencias para el nodo padre

            // El nodo padre
            HuffmanNode<Double> parent = new HuffmanNode<>(parentChar, frequencySum);
            
            // Asignar los nodos hijo al nodo padre
            parent.setLeft(left);
            parent.setRight(right);


            // Añadir el nodo padre a la cola
            queue.add(parent);
            // Actualizar el carácter del del sigiente nodo padre
            parentChar++;
        }

        // El nodo restante es la raíz del árbol de Huffman
        root = queue.poll();
        
        // Generar los códigos de Huffman a partir de la raíz del árbol
        generateCodes(root, "");
    }

    /**
     * Método recursivo para generar los códigos de Huffman a partir del árbol.
     * Haciendo un recorrido en preorden, se asigna un código binario a cada carácter.
     * @param node
     * @param code
     */

    private void generateCodes(HuffmanNode<Double> node, String code) {

    
        if (node == null) return;
        
        if (node.getLeft() == null && node.getRight() == null) {
            huffmanCodes.put(node.getCharacter(), code);
        }

        generateCodes(node.getLeft(), code + "0");
        generateCodes(node.getRight(), code + "1");
    }



}
