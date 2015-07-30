package eu.dandelion.dummy.common;

import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DummyCLParserTest {

    @Test
    public void testAppIDKeyEvaluation() {

        try {

            DummyCLParser cl = new DummyCLParser(ConstantTest.APP_ID_KEY_OPTIONS);

            assertEquals("foo", cl.getAppID());
            assertEquals("bar", cl.getAppKey());

            cl = new DummyCLParser(ConstantTest.VALID_OPTIONS);

            assertEquals("mickey", cl.getAppID());
            assertEquals("mouse", cl.getAppKey());

        } catch (Exception e) {
            fail(e.getMessage());
        }


    }

    @Test
    public void testRegularApiParser() {

        try {

            DummyCLParser cl = new DummyCLParser(ConstantTest.VALID_OPTIONS);

            assertTrue(cl.hasOption(Constant.OPTION.ee));
            assertEquals(cl.getOptionValue(Constant.OPTION.ee), "text=this is just a test");

            assertTrue(cl.hasOption(Constant.OPTION.lang));
            assertEquals(cl.getOptionValue(Constant.OPTION.lang), "en");

            assertEquals(cl.whichApi(), Constant.OPTION.ee);

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testMoreThanOneApiSpecified() {

        try {

            DummyCLParser cl = new DummyCLParser(ConstantTest.VALID_OPTIONS);

            assertFalse(cl.moreThanOneApiSpecified());

            cl = new DummyCLParser(ConstantTest.INVALID_TWO_OPTIONS);

            assertTrue(cl.moreThanOneApiSpecified());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testEvaluatedOption() {

        try {

            DummyCLParser cl = new DummyCLParser(ConstantTest.VALID_OPTIONS);

            Map<Constant.OPTION, String> shouldBe = new HashMap<Constant.OPTION, String>();
            for (int i = 0; i < ConstantTest.VALID_OPTIONS.length; i++) {

                if ((i & 1) == 0) continue;

                shouldBe.put(Constant.OPTION.valueOf(ConstantTest.VALID_OPTIONS[i - 1].substring(1)), ConstantTest.VALID_OPTIONS[i]);
            }

            assertEquals(shouldBe, cl.getEvaluatedOption());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testInvalidOption() {

        try {
            new DummyCLParser(ConstantTest.INVALID_UNRECOGNIZED_OPTIONS);
        } catch (Exception e) {
            assertEquals(UnrecognizedOptionException.class, e.getClass());
        }

    }

}