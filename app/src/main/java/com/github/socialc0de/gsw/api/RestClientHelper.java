package com.github.socialc0de.gsw.api;

import android.content.Context;

import com.github.socialc0de.gsw.api.interfaces.AudienceRestClient;
import com.github.socialc0de.gsw.api.interfaces.AudienceRestClient_;
import com.github.socialc0de.gsw.api.interfaces.EmergencyEntryRestClient;
import com.github.socialc0de.gsw.api.interfaces.EmergencyEntryRestClient_;
import com.github.socialc0de.gsw.api.interfaces.FaqCategoryRestClient;
import com.github.socialc0de.gsw.api.interfaces.FaqCategoryRestClient_;
import com.github.socialc0de.gsw.api.interfaces.FaqEntryRestClient;
import com.github.socialc0de.gsw.api.interfaces.FaqEntryRestClient_;
import com.github.socialc0de.gsw.api.interfaces.PhraseCategoryRestClient;
import com.github.socialc0de.gsw.api.interfaces.PhraseCategoryRestClient_;
import com.github.socialc0de.gsw.api.interfaces.PhraseEntryRestClient;
import com.github.socialc0de.gsw.api.interfaces.PhraseEntryRestClient_;
import com.github.socialc0de.gsw.api.interfaces.PoiCategoryRestClient;
import com.github.socialc0de.gsw.api.interfaces.PoiCategoryRestClient_;
import com.github.socialc0de.gsw.api.interfaces.PoiEntryRestClient;
import com.github.socialc0de.gsw.api.interfaces.PoiEntryRestClient_;
import com.github.socialc0de.gsw.customClasses.api.EmergencyEntry;

import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by roman on 05.12.2015.
 */
public class RestClientHelper {

    public static FaqCategoryRestClient createFaqCategoryRestClientWithTimeout(final Context context) {
        FaqCategoryRestClient restClient = new FaqCategoryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static PoiCategoryRestClient createPoiCategoryRestClientWithTimeout(final Context context) {
        PoiCategoryRestClient restClient = new PoiCategoryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static PhraseCategoryRestClient createPhraseCategoryRestClientWithTimeout(final Context context) {
        PhraseCategoryRestClient restClient = new PhraseCategoryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static AudienceRestClient createAudienceRestClientWithTimeout(final Context context) {
        AudienceRestClient restClient = new AudienceRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static FaqEntryRestClient createFaqEntryRestClientWithTimeout(final Context context) {
        FaqEntryRestClient restClient = new FaqEntryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static PoiEntryRestClient createPoiEntryRestClientWithTimeout(final Context context) {
        PoiEntryRestClient restClient = new PoiEntryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static PhraseEntryRestClient createPhraseEntryRestClientWithTimeout(final Context context) {
        PhraseEntryRestClient restClient = new PhraseEntryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static EmergencyEntryRestClient createEmergencyEntryRestClientWithTimeout(final Context context) {
        EmergencyEntryRestClient restClient = new EmergencyEntryRestClient_(context);

        setTimeout(restClient.getRestTemplate(), 20);

        return restClient;
    }

    public static void setTimeout(RestTemplate restTemplate, int seconds){
        ClientHttpRequestFactory requestFactory = restTemplate.getRequestFactory();
        if(requestFactory instanceof SimpleClientHttpRequestFactory) {
            ((SimpleClientHttpRequestFactory) requestFactory).setConnectTimeout(seconds * 1000);
            ((SimpleClientHttpRequestFactory) requestFactory).setReadTimeout(seconds * 1000);
        }
        else if(requestFactory instanceof HttpComponentsClientHttpRequestFactory) {
            ((HttpComponentsClientHttpRequestFactory) requestFactory).setReadTimeout(seconds * 1000);
            ((HttpComponentsClientHttpRequestFactory) requestFactory).setConnectTimeout(seconds * 1000);
        }
    }
}
