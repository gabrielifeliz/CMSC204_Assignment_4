package cmsc204assignment4;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the ConcordanceDataManager
 * which is implemented from the ConcordanceDataManagerInterface
 * 
 * @author Professor Kartchner
 *
 */
public class ConcordanceDataManagerTest {
	private ConcordanceDataManagerInterface concordanceManager = new ConcordanceDataManager();
	private File inputFile, outputFile;
	private String text;

	/**
	 * Create an instance of ConcordanceDataManager
	 * Create a string for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		concordanceManager = new ConcordanceDataManager();
		text = "Now is the time\nfor all good men\n"+
				"to come to the aid\nof their country";
	}

	/**
	 * Set concordanceManager reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		concordanceManager = null;
	}

	/**
	 * Test for the createConcordanceArray method
	 * Use the String text created in setUp()
	 */
	@Test
	public void testCreateConcordanceArray() {
                text = "A journey of a\nthousand miles"
                        + "\nbegins with a\nsingle step";
		ArrayList<String> words = concordanceManager.createConcordanceArray(text);
		assertEquals("begins: 3", words.get(0).trim());
		assertEquals("journey: 1", words.get(1).trim());
		assertEquals("miles: 2", words.get(2).trim());
		assertEquals("single: 4", words.get(3).trim());
		assertEquals("step: 4", words.get(4).trim());
		assertEquals("thousand: 2", words.get(5).trim());
		assertEquals("with: 3", words.get(6).trim());
	}
	
	/**
	 * Student Test for the createConcordanceArray method
	 */
	@Test
	public void testCreateConcordanceArraySTUDENT() {
		ArrayList<String> words = concordanceManager.createConcordanceArray(text);
		assertEquals("aid: 3", words.get(0).trim());
		assertEquals("all: 2", words.get(1).trim());
		assertEquals("come: 3", words.get(2).trim());
		assertEquals("country: 4", words.get(3).trim());
		assertEquals("for: 2", words.get(4).trim());
		assertEquals("good: 2", words.get(5).trim());
		assertEquals("men: 2", words.get(6).trim());
		assertEquals("now: 1", words.get(7).trim());
		assertEquals("their: 4", words.get(8).trim());
		assertEquals("time: 1", words.get(9).trim());
	}
	
	/**
	 * Test for createConcordanceFile method
	 * This is intended to be used with the test file:
	 * Now_is_the_time.txt
	 */
	@Test
	public void testCreateConcordanceFileA() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("Now is the time\n" +
					"for all good men\n" +
					"to come to the aid\n" +
					"of their country\n");
			
			inFile.close();
			outputFile = new File("Test1Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
			}

			scan.close();
			outFile.close();
			
		 
			assertEquals("aid: 3", words.get(0).trim());
			assertEquals( "all: 2", words.get(1).trim());
			assertEquals("come: 3", words.get(2).trim());
			assertEquals("country: 4", words.get(3).trim());
			assertEquals("for: 2", words.get(4).trim());
			assertEquals("good: 2", words.get(5).trim());
			assertEquals("men: 2", words.get(6).trim());
			assertEquals("now: 1", words.get(7).trim());
			assertEquals("their: 4", words.get(8).trim());
			assertEquals("time: 1", words.get(9).trim());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an FileNotFoundException");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an Exception");
		}
	}

	/**
	 * Test for createConcordanceFile method
	 * Create an inputFile "Test.txt" 
	 * and an outputFile "TestOut.txt"
	 */
	
	@Test
	public void testCreateConcordanceFileB() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Tes1t.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("Applets are Java programs that are usually part of a\n" +
					"Web site. They are stored on a Web server along with the site\'s\n" +
					"Web pages. When a remote user accesses a Web page with his or\n" +
					"her browser, any applets associated with the Web page are\n" +
					"transmitted over the Internet from the server to the remote\n" +
					"user\'s system\n");
			inFile.close();
			outputFile = new File("TestOut1.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
		 
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
				
			}

			scan.close();
			outFile.close();
		for(int i=0; i<words.size(); i++)		 
			assertEquals("accesses: 3", words.get(0).trim());
			assertEquals("applets: 1, 4", words.get(3).trim());
			assertEquals("are: 1, 2, 4", words.get(4).trim());
			assertEquals("page: 3, 4", words.get(13).trim());
			assertEquals("pages: 3", words.get(14).trim());
			assertEquals("remote: 3, 5", words.get(17).trim());
			assertEquals("server: 2, 5", words.get(18).trim());
			assertEquals("site's: 2", words.get(20).trim());
			assertEquals("user's: 6", words.get(27).trim());
			assertEquals("web: 2, 3, 4", words.get(29).trim());
			assertEquals("with: 2, 3, 4", words.get(31).trim());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateConcordanceFileC() {
		try {
			inputFile = new File("Test2.txt");
			inputFile.setReadable(false);
			outputFile = new File("Test2Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			assertTrue("This should have raised an exception", false);
			outFile.close();
		
		} catch (FileNotFoundException e) {
			assertTrue("This should have raised a FileNotFoundexception", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateConcordanceFileD() {
		try {
			inputFile = new File("Test3.txt");
			outputFile = new File("Test3Out.txt");
			outputFile.setWritable(false);
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			assertTrue("This should have raised an exception", false);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			assertTrue("This should have raised a FileNotFoundException", true);
		}
	}
	
	/**
	 * Student Test for the createConcordanceFile method
	 */
	@Test
	public void testCreateConcordanceFileSTUDENT() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("TestStudent.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("A journey of a\nthousand miles"
                            + "\nbegins with a\nsingle step");
			
			inFile.close();
			outputFile = new File("TestOutStudent.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
			}

			scan.close();
			outFile.close();
			
		 
			assertEquals("begins: 3", words.get(0).trim());
                        assertEquals("journey: 1", words.get(1).trim());
                        assertEquals("miles: 2", words.get(2).trim());
                        assertEquals("single: 4", words.get(3).trim());
                        assertEquals("step: 4", words.get(4).trim());
                        assertEquals("thousand: 2", words.get(5).trim());
                        assertEquals("with: 3", words.get(6).trim());
                        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an FileNotFoundException");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an Exception");
		}
	}
}