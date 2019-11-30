package cmsc204assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class is the data structure for the concordance
 * @author Gabriel I Feliz
 */
public class ConcordanceDataStructure implements ConcordanceDataStructureInterface {
    
    private LinkedList<ConcordanceDataElement>[] concordanceTable;

    /**
     * This constructor calculates a 4k+3 prime 
     * to be the size of the concordance table
     * @param size size of the concordance table
     */
    ConcordanceDataStructure(int size) {
        boolean fkp3 = false;
        boolean aPrime = false;
        int prime, highDivisor, d;
        prime = (int)(size / 1.5);
        
        if(prime % 2 == 0) // if even make odd
            prime = prime +1;
        while(fkp3 == false) { // not a 4k+3 prime
            while(aPrime == false) { // not a prime
                highDivisor = (int)(Math.sqrt(prime) + 0.5);
                for(d = highDivisor; d > 1; d--) {  
                    if(prime % d == 0)
                        break; // not a prime
	        }
	        if(d != 1) // prime not found
                    prime = prime + 2;
                else
                    aPrime = true;
	    } // end of the prime search loop
            if((prime - 3) % 4 == 0)
                fkp3 = true;
            else {  
                prime = prime + 2;
                aPrime = false;
            }
        } // end of 4k+3 prime search loop
        
        concordanceTable = new LinkedList[prime];
        for (int i = 0; i < concordanceTable.length; i++){
            concordanceTable[i] = new LinkedList<>();
        }
    }

    /**
     * This constructor is responsible for using the concordance
     * data structure class for testing purposes
     * @param testing testing purposes label
     * @param size size of the concordance table
     */
    ConcordanceDataStructure(String testing, int size) {
        concordanceTable = new LinkedList[size];
        for (int i = 0; i < concordanceTable.length; i++){
            concordanceTable[i] = new LinkedList<>();
        }
    }

    @Override
    public int getTableSize() {
        return concordanceTable.length;
    }

    @Override
    public ArrayList<String> getWords(int index) {
        ArrayList<String> wordList = new ArrayList<>();
        for (ConcordanceDataElement e : concordanceTable[index]) {
            wordList.add(e.getWord());
        }
        return wordList;
    }

    @Override
    public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
        ArrayList<LinkedList<Integer>> pageNumbers = new ArrayList<>();
        for (ConcordanceDataElement e : concordanceTable[index]) {
            pageNumbers.add(e.getList());
        }
        return pageNumbers;
    }

    @Override
    public void add(String word, int lineNum) {
        ConcordanceDataElement element = new ConcordanceDataElement(word);
        int hashIdx = Math.abs(element.hashCode() % concordanceTable.length), idx;
        boolean contains = false;
        for (idx = 0; idx < concordanceTable[hashIdx].size(); idx++) {
            if (concordanceTable[hashIdx].get(idx)
                    .getWord().equalsIgnoreCase(word)) {
                contains = true;
                break;
            }   
        }
        
        if (contains) {
            concordanceTable[hashIdx].get(idx).addPage(lineNum);
        } else {
            element.addPage(lineNum);
            concordanceTable[hashIdx].add(element);
        }
        
    }

    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> concordanceList = new ArrayList<>();
        for (LinkedList<ConcordanceDataElement> list : concordanceTable) {
            for (ConcordanceDataElement e : list) {
                concordanceList.add(e.toString());
            }
        }
        Collections.sort(concordanceList);
        for (int i = 0; i < concordanceList.size(); i++) {
            if (concordanceList.get(i).contains("'")) {
                String temp = concordanceList.get(i);
                concordanceList.set(i, concordanceList.get(i + 1));
                concordanceList.set(i + 1, temp);
                i++;
            }
        }
        return concordanceList;
    }
}
