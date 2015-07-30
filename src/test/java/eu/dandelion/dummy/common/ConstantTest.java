package eu.dandelion.dummy.common;

public class ConstantTest {

    public static final String[] APP_ID_KEY_OPTIONS             = {"-" + Constant.OPTION.ai.name(), "foo", "-" + Constant.OPTION.ak.name(), "bar"};
    public static final String[] VALID_OPTIONS                  = {"-" + Constant.OPTION.ee.name(), "text=this is just a test", "-" + Constant.OPTION.lang.name(), "en"};
    public static final String[] INVALID_TWO_OPTIONS            = {"-" + Constant.OPTION.ee.name(), "text=this is just a test", "-" + Constant.OPTION.ts.name(), "one too many"};
    public static final String[] INVALID_UNRECOGNIZED_OPTIONS   = {"-" + "foo", "what the hell is this?!"};

}