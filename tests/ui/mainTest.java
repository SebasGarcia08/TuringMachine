package ui;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import utils.filesComparator;

public class mainTest {
	public String BASE_DIR = "data/tests/Main/read/";
	public String TEST_FILE_IN = BASE_DIR + "read_test_in.txt";
	public String TEST_FILE_OUT = BASE_DIR + "read_test_out.txt";
	public String ANS_TEST_FILE_OUT = BASE_DIR + "ans_read_test_out.txt";
	
	private Main prg = new Main();
	
	@Test
	public void readTest() {
		prg = new Main();
		try {
			prg.readDebug(TEST_FILE_IN, ANS_TEST_FILE_OUT, 1);
			boolean res = filesComparator.filesAreEqual(ANS_TEST_FILE_OUT, TEST_FILE_OUT);
			assertTrue("Files are not equal", res);
		} catch (IOException e) {
			fail("Exception occured: " + e.getMessage());
		}
	}
}
