package eu.dandelion.dummy.api;

import eu.dandelion.dummy.common.Constant;
import eu.dandelion.dummy.rest.DummyRestClient;
import eu.dandelion.dummy.rest.RestClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Implementation of Dandelion Entity Extraction API.
 */
public class EntityExtractionAPIClient implements APIClient {

    private final RestClient client;
    private String type, content, lang, minConfidence, minLength, include, extraTypes, country, customSpots, epsilon, appID, appKey;
    private boolean socialHashtag, socialMention;

    /**
     * Instantiate an Entity Exctraction API client with the minimum information. The default {@link RestClient} implementation will be used: {@link DummyRestClient}.
     *
     * @param shitToParse               the source to be parsed. This must be preceded by on of the following: text=|url=|html=|html_fragment=|file=
     * @param appID                     the application id
     * @param appKey                    the application key
     * @throws FileNotFoundException    if the input source is a file and file does not exists
     */
    public EntityExtractionAPIClient(String shitToParse, String appID, String appKey) throws FileNotFoundException {

        this(new DummyRestClient(), shitToParse, appID, appKey);
    }

    /**
     * Instantiate an Entity Exctraction API client with the minimum information plus a {@link RestClient} to perform the api call.
     *
     * @param client                    the {@link RestClient} needed to perform the call
     * @param shitToParse               the source to be parsed. This must be preceded by on of the following: text=|url=|html=|html_fragment=|file=
     * @param appID                     the application id
     * @param appKey                    the application key
     * @throws FileNotFoundException    if the input source is a file and file does not exists
     */
    public EntityExtractionAPIClient(RestClient client, String shitToParse, String appID, String appKey) throws FileNotFoundException {

        this.client = client;
        this.appID = appID;
        this.appKey = appKey;
        extractTypeAndContent(shitToParse);
    }

    /**
     * Built the url and perform the call to the API.
     *
     * @return              the API response
     * @throws IOException  if some problem occurs while calling the API
     */
    public String makeItHappen() throws IOException {

        return client.POST("https://api.dandelion.eu/datatxt/nex/v1/?"
                + type + "=" + URLEncoder.encode(content, "UTF-8")
                + (lang != null ? "&lang=" + lang : "")
                + (minConfidence != null ? "&min_confidence=" + minConfidence : "")
                + (minLength != null ? "&min_length=" + minLength : "")
                + ("&social.hashtag=" + socialHashtag)
                + ("&social.mention=" + socialMention)
                + (include != null ? "&include=" + include : "")
                + (extraTypes != null ? "&extra_types=" + extraTypes : "")
                + (country != null ? "&country=" + country: "")
                + (customSpots != null ? "&custom_spots=" + customSpots: "")
                + (epsilon != null ? "&epsilon=" + epsilon: "")
                + "&$app_id=" + appID + "&$app_key=" + appKey);

    }

    private void extractTypeAndContent(String shitToParse) throws FileNotFoundException {

        if (shitToParse == null) {
            throw new IllegalArgumentException("missing text to parse");
        }

        type = shitToParse.substring(0, shitToParse.indexOf("=")).trim();
        content = shitToParse.substring(shitToParse.indexOf("=") + 1).trim();

        if ("file".equals(type)) {

            File file = new File(shitToParse.substring(5));

            if (!file.exists()) {
                throw new IllegalArgumentException(file + " (No such file or directory)");
            }

            String fileContent = new Scanner(file).useDelimiter("\\Z").next();

            type = "text";
            content = fileContent.replace(Constant.NL, " ");

        }

    }

    public EntityExtractionAPIClient setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public EntityExtractionAPIClient setMinConfidence(String minConfidence) {
        this.minConfidence = minConfidence;
        return this;
    }

    public EntityExtractionAPIClient setMinLength(String minLength) {
        this.minLength = minLength;
        return this;
    }

    public EntityExtractionAPIClient setSocialHashtag(boolean socialHashtag) {
        this.socialHashtag = socialHashtag;
        return this;
    }

    public EntityExtractionAPIClient setSocialMention(boolean socialMention) {
        this.socialMention = socialMention;
        return this;
    }

    public EntityExtractionAPIClient setInclude(String include) {
        if (include != null) {
            this.include = include.replaceAll("(\\s*,\\s*)", ",");
        }
        return this;
    }

    public EntityExtractionAPIClient setExtraTypes(String extraTypes) {
        if (extraTypes != null) {
            this.extraTypes = extraTypes.replaceAll("(\\s*,\\s*)", ",");
        }
        return this;
    }

    public EntityExtractionAPIClient setCountry(String country) {
        this.country = country;
        return this;
    }

    public EntityExtractionAPIClient setCustomSpots(String customSpots) {
        this.customSpots = customSpots;
        return this;
    }

    public EntityExtractionAPIClient setEpsilon(String epsilon) {
        this.epsilon = epsilon;
        return this;
    }

    public RestClient getClient() {
        return client;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getLang() {
        return lang;
    }

    public String getMinConfidence() {
        return minConfidence;
    }

    public String getMinLength() {
        return minLength;
    }

    public String getInclude() {
        return include;
    }

    public String getExtraTypes() {
        return extraTypes;
    }

    public String getCountry() {
        return country;
    }

    public String getCustomSpots() {
        return customSpots;
    }

    public String getEpsilon() {
        return epsilon;
    }

    public String getAppID() {
        return appID;
    }

    public String getAppKey() {
        return appKey;
    }

    public boolean isSocialHashtag() {
        return socialHashtag;
    }

    public boolean isSocialMention() {
        return socialMention;
    }

}