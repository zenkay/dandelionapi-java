package eu.dandelion.dummy.common;

/**
 * Utility class, contains all the useful information for the application.
 */
public class Constant {

    /**
     * System line separator.
     */
    public static final String NL = System.getProperty("line.separator");

    /**
     * A complete list of input options. Includes:
     * <ul>
     *  <li>option name</li>
     *  <li>if the option expect arguments</li>
     *  <li>option description</li>
     * </ul>
     */
    public enum OPTION {

        //OPTION NAME   HAS ARGUMENT    DESCRIPTION

        /** Print the menu message. The same result is achieved if no input is specified. */
        help            (false,         "print this message"),

        /** Application ID. */
        ai              (true,          "Dandelion APP ID"),

        /** Application Key. */
        ak              (true,          "Dandelion APP KEY"),

        /** Entity Extraction API. */
        ee              (true,          "Entity Extraction API." + NL + "values: text=|url=|html=|html_fragment=|file="),

        /** Language Detection API. */
        ld              (false,         "Language Detection API"),

        /** Sentiment Analysis API. */
        sa              (false,         "Sentiment Analysis API"),

        /** Text Similarity API. */
        ts              (false,         "Text Similarity API"),

        /** Specify language text. */
        lang            (true,          "Entity Extraction API language." + NL + "values: de|en|fr|it|pt"),

        /** Specify entity minimum confidence. */
        min_confidence  (true,          "Entity Extraction API entity minimum confidence." + NL + "values: a float"),

        /** Specify spot minimum length. */
        min_length      (true,          "Entity Extraction API entity spot minimum length." + NL + "values: an integer"),

        /** Enable social hashtag parsing. */
        social_hashtag  (false,         "Entity Extraction API hashtag parsing."),

        /** Enable social mention parsing. */
        social_mention  (false,         "Entity Extraction API mention parsing."),

        /** Specify more information to be returned. */
        include         (true,          "Entity Extraction API returns more information." + NL + "values: types, categories, abstract, image, lod, alternate_label"),

        /** Specify more information to be returned. */
        extra_types     (true,          "Entity Extraction API returns more information." + NL + "values: phone, vat"),

        /** Specify country. */
        country         (true,          "Entity Extraction API country VAT and phone number." + NL + "values: AD|AE|AM|AO|AQ|AR|AU|BB|BR|BS|BY|CA|CH|CL|CN|CX|DE|FR|GB|HU|IT|JP|KR|MX|NZ|PG|PL|RE|SE|SG|US|YT|ZW"),

        /** Specify custom spots. */
        custom_spots    (true,          "Entity Extraction API specific user-defined spots." + NL + "values: any valid spots-ID"),

        /** Balance between context and common topics. */
        epsilon         (true,          "Entity Extraction API rely more on context or common topics." + NL + "values: a float (an higher value favors more common topics)");

        private boolean hasArg;
        private String description;

        /**
         * Build a standard input option
         *
         * @param hasArg        true if the parameter expect an argument
         * @param description   simple option description
         */
        OPTION(boolean hasArg, String description) {
            this.hasArg = hasArg;
            this.description = description;
        }

        /**
         *  Check if the option needs an argument.
         *
         * @return true if the option expect an argument, false otherwise
         */
        public boolean hasArg() {
            return hasArg;
        }

        /**
         * Get the option description.
         *
         * @return the option description
         */
        public String getDescription() {
            return description;
        }

    }

}