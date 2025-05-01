/** 
 * Esta clase representa un nodo en el árbol de Huffman.
 * Cada nodo contiene un carácter, su frecuencia, y referencias a sus 
 * nodos hijo izquierdo y derecho.
 */

public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {
    
    private char character; // carácter asociado al nodo
    private double frequency; // frecuencia del carácter
    private HuffmanNode<T> left; // nodo hijo izquierdo
    private HuffmanNode<T> right;  // nodo hijo derecho

    public HuffmanNode(char character, double frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode<T> getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode<T> left) {
        this.left = left;
    }

    public HuffmanNode<T> getRight() {
        return right;
    }

    public void setRight(HuffmanNode<T> right) {
        this.right = right;
    }

    // Método para comparar nodos por frecuencia
    @Override
    public int compareTo(HuffmanNode<T> other) {
        return Double.compare(this.frequency, other.frequency);
    }

}
