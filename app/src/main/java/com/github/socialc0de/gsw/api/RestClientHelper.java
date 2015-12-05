package com.github.socialc0de.gsw.api;

import android.content.Context;

import com.github.socialc0de.gsw.api.interfaces.FaqCategoryRestClient;
import com.github.socialc0de.gsw.api.interfaces.FaqCategoryRestClient_;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * Created by roman on 05.12.2015.
 */
public class RestClientHelper {

    public static FaqCategoryRestClient createRestClientWithTimeout(final Context context) {
        FaqCategoryRestClient restClient = new FaqCategoryRestClient_(context);

        // set timeout to 20s
        ClientHttpRequestFactory requestFactory = restClient.getRestTemplate().getRequestFactory();
        if(requestFactory instanceof SimpleClientHttpRequestFactory) {
            ((SimpleClientHttpRequestFactory) requestFactory).setConnectTimeout(20 * 1000);
            ((SimpleClientHttpRequestFactory) requestFactory).setReadTimeout(20 * 1000);
        }
        else if(requestFactory instanceof HttpComponentsClientHttpRequestFactory) {
            ((HttpComponentsClientHttpRequestFactory) requestFactory).setReadTimeout(20 * 1000);
            ((HttpComponentsClientHttpRequestFactory) requestFactory).setConnectTimeout(20 * 1000);
        }

        return restClient;
    }

}
