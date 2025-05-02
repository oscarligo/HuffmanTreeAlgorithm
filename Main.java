import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
    
        ReaderAndWriter reader = new ReaderAndWriter();
        
        reader.readFile("input.txt");

        reader.calculateFrequency();

        HashMap<Character, Double> chars = reader.getCharFrequency();
        for (Character c : chars.keySet()) {
            System.out.println( "'"+ c + "' " + chars.get(c));
        }

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.buildTree(chars);

        System.out.println("\n");

        HashMap<Character, String> huffmanCodes = huffmanTree.getHuffmanCodes();
        for (Character c : huffmanCodes.keySet()) {
            System.out.println( "'"+ c + "' " + huffmanCodes.get(c));
        }

        reader.writeHuffmanCodesAsAscii("input.txt", huffmanCodes);
        reader.writeHuffmanCodesAsText("input.txt","DecompressedFile.txt", huffmanCodes);
        
    }

}
