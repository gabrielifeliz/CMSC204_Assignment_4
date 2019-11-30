package cmsc204assignment4;

import java.util.LinkedList;

/**
 * This is the class representation of the concordance
 * @author Gabriel I Feliz
 */
public class ConcordanceDataElement implements Comparable<ConcordanceDataElement> {

    /**
     * Word in text or file
     */
    private String word;
    
    /**
     * Pages or line numbers to find the word
     */
    private LinkedList<Integer> list;
    
    /**
     * Constructor that initializes concordance 
     * element with a word and no pages
     * @param word word in text or file
     */
    public ConcordanceDataElement(String word) {
        this.word = word;
        list = new LinkedList<>();
    }
    
    /**
     * Return word in concordance element
     * @return word in text or file
     */
    public String getWord() {
        return word;
    }
    
    /**
     * Return pages in concordance element
     * @return pages or line numbers to find the word
     */
    public LinkedList<Integer> getList() {
        return list;
    }
    
    /**
     * Add page to concordance element 
     * @param lineNum page or line number to find word
     */
    public void addPage(int lineNum) {
        if (!list.contains(lineNum)) {
            list.add(lineNum);
        }
    }
    
    /**
     * Return string representation of concordance element
     * @return word with page or line numbers
     */
    @Override
    public String toString() {
        String concordance = word + ": ";
        for (int i = 0; i < list.size(); i++) {
            concordance += (i == 0 ? list.get(i) : ", " + list.get(i));
        }
        return concordance;
    }
    
    /**
     * Compare current concordance element with another
     * @param element another concordance element
     * @return 
     */
    @Override
    public int compareTo(ConcordanceDataElement element) {        
        throw new UnsupportedOperationException();
    }
    
    /**
     * Return hash code
     * @return hash code of the word in concordance element
     */
    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
