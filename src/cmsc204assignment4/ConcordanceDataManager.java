package cmsc204assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the manager class responsible for creating an array of
 * a concordance or a file of a concordance
 * @author Gabriel I Feliz
 */
public class ConcordanceDataManager implements ConcordanceDataManagerInterface {

    @Override
    public ArrayList<String> createConcordanceArray(String input) {
        String[] lineOfWords = input.split("\n");
        String[][] words = new String[lineOfWords.length][];
        
        for (int i = 0; i < words.length; i++) {
            words[i] = lineOfWords[i].replaceAll("[\\p{P}&&[^\\u0027]]", "")
                    .toLowerCase().split(" ");
        }
        
        ConcordanceDataStructure structure = new ConcordanceDataStructure(words.length);
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                if (!words[i][j].equalsIgnoreCase("and") &&
                        !words[i][j].equalsIgnoreCase("the") && 
                        words[i][j].length() >= 3) {
                    structure.add(words[i][j], i + 1);
                }
            }
        }
        
        return structure.showAll();
    }

    @Override
    public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
        Scanner readInput = new Scanner(input);
        String words = "";
        while (readInput.hasNextLine()) {
            words += readInput.nextLine() + "\n";
        }
        
        ArrayList<String> concordance = createConcordanceArray(words);
        
        PrintWriter writeOutput = new PrintWriter(output);
        
        for (String element : concordance) {
            writeOutput.println(element);
        }
        
        readInput.close();
        writeOutput.close();
        
        return true;
    }
    
}
