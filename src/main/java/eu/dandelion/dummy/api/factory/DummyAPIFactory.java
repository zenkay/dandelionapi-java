package eu.dandelion.dummy.api.factory;

import eu.dandelion.dummy.api.APIClient;
import eu.dandelion.dummy.api.EntityExtractionAPIClient;
import eu.dandelion.dummy.common.Constant;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Simple implementation to get the proper instance of {@link APIClient} for a given API.
 */
public class DummyAPIFactory {

    /**
     * Return an implementation of {@link APIClient} based on the API option.
     *
     * @param api       the chosen API to be called
     * @param options   an optional list of key value options that can added API
     * @param appID     application ID
     * @param appKey    application Key
     * @return          the proprer implementation of {@link APIClient}
     * @throws IllegalArgumentException if the API specified is not supported
     */
    public APIClient getClientAPI(Constant.OPTION api, Map<Constant.OPTION, String> options, String appID, String appKey) {

        switch (api) {

            case ee:

                String shitToParse = options.get(Constant.OPTION.ee);

                EntityExtractionAPIClient ee;
                try {
                    ee = new EntityExtractionAPIClient(shitToParse, appID, appKey);
                } catch (FileNotFoundException e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }

                ee  .setLang(options.get(Constant.OPTION.lang))
                    .setMinConfidence(options.get(Constant.OPTION.min_confidence))
                    .setMinLength(options.get(Constant.OPTION.min_length))
                    .setSocialHashtag(options.get(Constant.OPTION.social_hashtag) != null)
                    .setSocialMention(options.get(Constant.OPTION.social_mention) != null)
                    .setInclude(options.get(Constant.OPTION.include))
                    .setExtraTypes(options.get(Constant.OPTION.extra_types))
                    .setCountry(options.get(Constant.OPTION.country))
                    .setCustomSpots(options.get(Constant.OPTION.custom_spots))
                    .setEpsilon(options.get(Constant.OPTION.epsilon));

                return ee;

            case ts:
                break;
            case ld:
                break;
            case sa:
                break;
            default:
                throw new IllegalArgumentException("unsupported api: " + api);

        }

        return null;

    }

}