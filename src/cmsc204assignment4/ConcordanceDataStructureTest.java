package cmsc204assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the ConcordanceDataManager
 * which is implemented from the ConcordanceDataManagerInterface
 * @author Professor Kartchner
 */
public class ConcordanceDataStructureTest {
	ConcordanceDataStructureInterface concordanceDataStructure, testStructure, testStudent;
	String text;
	ArrayList<String> array;

	@Before
	public void setUp() throws Exception {
		concordanceDataStructure = new ConcordanceDataStructure(500);
		testStructure = new ConcordanceDataStructure("Testing", 20);
                testStudent = new ConcordanceDataStructure("Testing", 20);
	}

	@After
	public void tearDown() throws Exception {
		concordanceDataStructure = testStructure = null;
	}

	/**
	 * Test that words that hash to the same index are put in the same
	 * "bucket" for that index
	 */
	@Test
	public void testAddA() {
		testStructure.add("banana", 1);
		//banana should be stored at index 7
		//Math.abs("banana".hashCode()%20)
		assertEquals("banana", testStructure.getWords(7).get(0));
		assertEquals(1, testStructure.getPageNumbers(7).get(0).get(0), .001);
		testStructure.add("carrot", 2);
		//carrot should be stored at index 5
		assertEquals("carrot", testStructure.getWords(5).get(0));
		assertEquals(2, testStructure.getPageNumbers(5).get(0).get(0), .001);
		testStructure.add("apple", 3);
		//apple should be stored at index 10
		assertEquals("apple", testStructure.getWords(10).get(0));
		assertEquals(3, testStructure.getPageNumbers(10).get(0).get(0), .001);
		testStructure.add("cucumber", 2);
		//cucumber should also be stored at index 10
		assertEquals("cucumber", testStructure.getWords(10).get(1));
		assertEquals(2, testStructure.getPageNumbers(10).get(1).get(0), .001);
	}
	
	@Test
	public void testAddSTUDENT() {
		testStudent.add("knight", 1);
		//knight should be stored at index 11
		//Math.abs("knight".hashCode()%20)
		assertEquals("knight", testStudent.getWords(11).get(0));
		assertEquals(1, testStudent.getPageNumbers(11).get(0).get(0), .001);
                
		testStudent.add("pawn", 2);
		//pawn should be stored at index 8
		assertEquals("pawn", testStudent.getWords(8).get(0));
		assertEquals(2, testStudent.getPageNumbers(8).get(0).get(0), .001);
                
                testStudent.add("rook", 3);
		//rook should be stored at index 13
		assertEquals("rook", testStudent.getWords(13).get(0));
		assertEquals(3, testStudent.getPageNumbers(13).get(0).get(0), .001);
		                
                testStudent.add("bishop", 2);
		//bishop should also be stored at index 11
		assertEquals("bishop", testStudent.getWords(11).get(1));
		assertEquals(2, testStudent.getPageNumbers(11).get(1).get(0), .001);
	}
	
	/**
	 * Test that the words with the same hash index are retrieved with getWords
	 */
	@Test
	public void testAddB() {
		ArrayList<String> testWords = new ArrayList<String>(5);
		testStructure.add("apple",1); testWords.add("apple");
		//apple should be stored at index 10
		assertEquals("apple", testStructure.getWords(10).get(0));
		assertEquals(testWords, testStructure.getWords(10));
		//cucumber should also be stored at index 10
		testStructure.add("cucumber", 2); testWords.add("cucumber");
		assertEquals("cucumber", testStructure.getWords(10).get(1));
		assertEquals(testWords, testStructure.getWords(10));
		
	}
	
	/**
	 * Test that the same linenumber for a word is only entered once
	 * for that word
	 */
	@Test
	public void testAddC() {
		ArrayList<Integer> pagenumbers = new ArrayList<Integer>(5);
		pagenumbers.add(1);
		testStructure.add("apple",1);
		//apple should be stored at index 10
		assertEquals(1, testStructure.getPageNumbers(10).get(0).get(0), .001);
		assertEquals(pagenumbers, testStructure.getPageNumbers(10).get(0));
		//add another apple in line 1, should not add additional (apple,1) to the data structure
		testStructure.add("apple", 1);
		assertEquals(pagenumbers, testStructure.getPageNumbers(10).get(0));
		//add apple at line 2, should add (apple, 2) to the data structure
		testStructure.add("apple", 2);
		pagenumbers.add(2);
		assertEquals(2, testStructure.getPageNumbers(10).get(0).get(1), .001);
		assertEquals(pagenumbers, testStructure.getPageNumbers(10).get(0));
		//add another (apple, 1) - shouldn't add to the page numbers
		testStructure.add("apple", 1);				
		assertEquals(2, testStructure.getPageNumbers(10).get(0).get(1), .001);
		assertEquals(pagenumbers, testStructure.getPageNumbers(10).get(0));
	}


	/**
	 * Test the tableSize for concordanceDataStructures constructed
	 * with both constructors
	 */
	@Test
	public void testGetTableSize()
	{
		assertEquals(347, concordanceDataStructure.getTableSize());
		assertEquals(20, testStructure.getTableSize());		
	}
	
	/**
	 * Test that the resulting ArrayList is in alphabetical order
	 */
	@Test
	public void testShowAll() {
		testStructure.add("banana", 1);
		testStructure.add("carrot", 2);
		testStructure.add("apple", 3);
		array = testStructure.showAll();
		assertTrue(array.get(0).contains("apple: 3"));
		assertTrue(array.get(1).contains("banana: 1"));
		assertTrue(array.get(2).contains("carrot: 2"));
	}

}