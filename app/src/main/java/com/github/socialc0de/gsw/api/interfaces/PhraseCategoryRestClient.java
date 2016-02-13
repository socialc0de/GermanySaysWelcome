package com.github.socialc0de.gsw.api.interfaces;

import com.github.socialc0de.gsw.BuildConfig;
import com.github.socialc0de.gsw.customClasses.api.PhraseCategory;

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
public interface PhraseCategoryRestClient extends RestClientErrorHandling {
    @Get("/phrasecategories")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<PhraseCategory> loadPhraseCategoryFromRest();


    // if you need to add some configuration to the Spring RestTemplate.
    RestTemplate getRestTemplate();
}