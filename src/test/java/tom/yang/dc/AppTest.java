package tom.yang.dc;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(final String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     * 
     * @throws ParseException
     * @throws IOException 
     */
    public void testApp() {
        try{
            final App app = new App();
            app.main(new String[] { "-T10", "-DD:/temp" });
        }catch(Throwable ignore){}
    }
}
