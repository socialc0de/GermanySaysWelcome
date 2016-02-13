package com.github.socialc0de.gsw.api.interfaces;

import com.github.socialc0de.gsw.BuildConfig;
import com.github.socialc0de.gsw.customClasses.api.FaqCategory;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by roman on 05.12.2015.
 */

@Rest(rootUrl = BuildConfig.API_URL, converters = GsonHttpMessageConverter.class)
public interface FaqCategoryRestClient extends RestClientErrorHandling {
    @Get("/faqcategories")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<FaqCategory> loadFaqCategoryFromRest();


    // if you need to add some configuration to the Spring RestTemplate.
    RestTemplate getRestTemplate();
}