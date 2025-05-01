import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
    
        Reader reader = new Reader();
        
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


        reader.writeHuffmanCodesAsAscii("CompressedFile.txt", huffmanCodes);

        reader.writeHuffmanCodesAsText("CompressedFile.txt","DecompressedFile.txt", huffmanCodes);
        
    }

}
