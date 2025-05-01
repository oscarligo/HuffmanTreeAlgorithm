public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {
    
    private char character; 
    private double frequency; 
    private HuffmanNode<T> left; 
    private HuffmanNode<T> right; 

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

    @Override
    public int compareTo(HuffmanNode<T> other) {
        return Double.compare(this.frequency, other.frequency);
    }

}
