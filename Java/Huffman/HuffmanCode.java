// Jerry
// 03/10/2023
// CSE 123
// TA: GAURAV
// This class constructs a HuffmanCode and is able to compress
// and decompress files. The inner static HuffmanNode creates nodes
// to build the Huffman code binary tree.
import java.util.*;
import java.io.*;

public class HuffmanCode {
    
    private HuffmanNode root;

    // Takes an int array with the frequencies of each character's ASCII value
    // Constructs and initialize a new Huffman Code
    public HuffmanCode(int[] frequencies) {
        Queue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != 0) {
                HuffmanNode temp = new HuffmanNode(i, frequencies[i]);
                pq.add(temp);
            }
        }
        codeHelper(pq);
    }

    // private helper method to constructs the huffman code for 
    // the constructor that takes an int array
    private void codeHelper(Queue<HuffmanNode> pq) {
        if (pq.size() < 2) {
            this.root = pq.peek();
        } else {
            HuffmanNode rootLeft = pq.remove();
            HuffmanNode rootRight = pq.remove();
            int totalFreq = rootLeft.freq + rootRight.freq;
            HuffmanNode temp = new HuffmanNode(-1, totalFreq, rootLeft, rootRight);
            pq.add(temp);
            codeHelper(pq);
        }
    }

    // Takes a scanner input from a constructed code from a .code file
    // Constructs and initialize a new Huffman code 
    // Assumes the Scanner is not null and only contains legal data
    // Legal data means it should contain a pair of lines with first line of ASCII code
    // and second line of Huffman encoding to represent each character
    public HuffmanCode(Scanner input) {
        while(input.hasNext()) {
            int data = Integer.parseInt(input.nextLine());
            String location = (String)input.nextLine();
            int index = 0;
            root = scannerCode(root, data, location, index);
        }
    }

    // private helper method for the constructor that takes a scanner input
    // returns the root of the Huffman code
    private HuffmanNode scannerCode(HuffmanNode root, int data, String location, int index) {
        if (location.length() == index) {
            HuffmanNode fill = new HuffmanNode(data, 0);
            return fill;
        } else {
            if (root == null) {
                root = new HuffmanNode();
            }
            if (location.charAt(index) == '0') {
                root.left = scannerCode(root.left, data, location, index + 1);
            } else {
                root.right = scannerCode(root.right, data, location, index + 1);
            }
        }
        return root;
    }

    // Takes a PrintStream output file
    // Stores the current Huffman code to the given file
    // The file should contain a pair of lines with first line of ASCII code
    // and second line of Huffman encoding to represent each character by pre-order traversal
    public void save(PrintStream output) {
        readOff(output, root, "");
    }

    // private helper method for save method
    private void readOff(PrintStream output, HuffmanNode root, String loc) {
        if (root != null) {
            if(root.left == null && root.right == null) {
                output.println(root.data);
                output.println(loc);
            } else {
                readOff(output, root.left, loc + "0");
                readOff(output, root.right, loc + "1");
            }
        }
    }

    // Takes a BitInputStream input of Huffman encoding and PrintStream output where to translate
    // Reads individual bits from the input stream and 
    // writes the corresponding characters to the output
    // Assumes that the input only contains legal encoding of characters
    // that can represent the character in the Huffman code
    public void translate(BitInputStream input, PrintStream output) {
        HuffmanNode currRoot = this.root;
        while (input.hasNextBit()) {
            int loc = input.nextBit();
            if (currRoot.left == null && currRoot.right == null) {
                output.write((char)currRoot.data);
                currRoot = this.root;
            }
            if (loc == 0) {
                currRoot = currRoot.left;
            } else if (loc == 1) {
                currRoot = currRoot.right;
            }
        }
        output.write((char)currRoot.data);
    }

    // Static inner class HuffmanNode for the outer class to create the Huffman code binary tree
    // this class implements the comparable interface
    public static class HuffmanNode implements Comparable<HuffmanNode>{

        public int data;
        public int freq;
        public HuffmanNode left;
        public HuffmanNode right;

        // Constructs a node represents empty in the Huffman code tree
        public HuffmanNode() {
            this(-1, 0, null, null);
        }

        // Constructs a node with given ASCII of character and its frequency
        public HuffmanNode(int data, int freq) {
            this(data, freq, null, null);
        }

        // Constructs a node with given ASCII of character, its frequency, and children nodes
        public HuffmanNode(int data, int freq, HuffmanNode left, HuffmanNode right) {
            this.data = data;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        // Takes another node to compare
        // Override the original method to compare each node by their frequencies
        // Returns an int to represent the comparing result
        public int compareTo (HuffmanNode other) {
            if (this.freq < other.freq) {
                return -1;
            } else if (this.freq > other.freq) {
                return 1;
            } else {
                return 0;
            }
        }


    }

}
