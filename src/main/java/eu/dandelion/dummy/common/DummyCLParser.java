package eu.dandelion.dummy.common;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.*;

/**
 * Simple implementation to manage command line options.
 */
public class DummyCLParser {

    private static final Set<Constant.OPTION> APIS = new HashSet<Constant.OPTION>() {{ add(Constant.OPTION.ee); add(Constant.OPTION.ld); add(Constant.OPTION.sa); add(Constant.OPTION.ts); }};
    private static final String APP_PROPERTY = "/app.properties";

    private final CommandLine cmd;
    private final Options options;
    private String appID, appKey;

    /**
     * Instantiate a command line parser based on the arguments received in input.
     *
     * @param args              the input options
     * @throws ParseException   can not parse input options
     */
    public DummyCLParser(String[] args) throws ParseException {

        options = new Options();

        for (Constant.OPTION option : Constant.OPTION.values()) {
            options.addOption(option.name(), option.hasArg(), option.getDescription());
        }

        CommandLineParser parser =  new DefaultParser();
        cmd = parser.parse(options, args);

        evaluateAppIDKey();

    }

    /**
     * Get the application ID.
     *
     * @return the application ID
     */
    public String getAppID() {
        return appID;
    }

    /**
     * Get the application key.
     *
     * @return the application key
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * Check if an option has been received in input.
     *
     * @param opt the {@link eu.dandelion.dummy.common.Constant.OPTION} to be checked
     * @return  true if the option has been received in input, false otherwise
     */
    public boolean hasOption(Constant.OPTION opt) {

        return cmd.hasOption(opt.name());
    }

    /**
     * Return an option argument value.
     *
     * @param opt the {@link eu.dandelion.dummy.common.Constant.OPTION} name
     * @return  the argument value
     */
    public String getOptionValue(Constant.OPTION opt) {

        return cmd.getOptionValue(opt.name());
    }

    /**
     * Check if more than one api option have been specified in input.
     *
     * @return true if the one more than one api option has been specified, false otherwise
     */
    public boolean moreThanOneApiSpecified() {

        boolean alreadyOne = false;

        for (Constant.OPTION api : APIS) {
            if (cmd.hasOption(api.name())) {
                if (alreadyOne) return true;
                else alreadyOne = true;
            }
        }

        return false;

    }

    /**
     * Compose the complete list of {@link eu.dandelion.dummy.common.Constant.OPTION}s specified in input.
     *
     * @return a map with all the {@link eu.dandelion.dummy.common.Constant.OPTION}s name and the values received
     */
    public Map<Constant.OPTION, String> getEvaluatedOption() {

        Map<Constant.OPTION, String> opts = new HashMap<Constant.OPTION, String>();
        for (Constant.OPTION opt : Constant.OPTION.values()) {

            if (hasOption(opt)) {
                opts.put(opt, getOptionValue(opt));
            }
        }

        return opts;
    }

    /**
     * Return the API {@link eu.dandelion.dummy.common.Constant.OPTION} to be executed.
     *
     * @return the {@link eu.dandelion.dummy.common.Constant.OPTION} representing the API to be called
     * @throws {@link IllegalArgumentException} if more than one API option has been specified
     */
    public Constant.OPTION whichApi() {

        if (moreThanOneApiSpecified()) {
            throw new IllegalArgumentException("Only one between " + APIS + " options can be specified");
        }

        if (hasOption(Constant.OPTION.ee)) {
            return Constant.OPTION.ee;
        } else if (hasOption(Constant.OPTION.ts)) {
            throw new IllegalArgumentException("Text similarity API not implemented yet.");
//            return Constant.OPTION.ts;
        } else if (hasOption(Constant.OPTION.ld)) {
            throw new IllegalArgumentException("Language Detection API not implemented yet.");
//            return Constant.OPTION.ld;
        } else if (hasOption(Constant.OPTION.sa)) {
            throw new IllegalArgumentException("Sentiment Analysis API not implemented yet.");
//            return Constant.OPTION.sa;
        } else {
            return null;
        }

    }

    /**
     * Check the Dandelion application ID and Key against both input options and application property file.
     */
    public void evaluateAppIDKey() {

        if (hasOption(Constant.OPTION.ai) && hasOption(Constant.OPTION.ak)) {

            appID = getOptionValue(Constant.OPTION.ai);
            appKey = getOptionValue(Constant.OPTION.ak);

        } else {

            try {

                Properties prop = loadAppProperty(APP_PROPERTY);
                appID = prop.getProperty("dandelion.app.id");
                appKey = prop.getProperty("dandelion.app.key");

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        if (appID == null || appKey == null) {
            throw new IllegalArgumentException("Missing Dandelion application ID/Key.");
        }
    }

    private Properties loadAppProperty(String appProperty) throws IOException {

        Properties prop = new Properties();
        prop.load(DummyCLParser.class.getResourceAsStream(appProperty));

        return prop;

    }

    /**
     * Print the menu message.
     */
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar dandelion-dummy-client-1.0-SNAPSHOT.jar options", options);
    }

}