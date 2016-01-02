package com.github.socialc0de.gsw.api.interfaces;

import com.github.socialc0de.gsw.customClasses.api.PhraseEntry;
import com.github.socialc0de.gsw.customClasses.api.PoiEntry;

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

@Rest(rootUrl = "https://gsw.pajowu.de/api", converters = GsonHttpMessageConverter.class)
public interface PhraseEntryRestClient extends RestClientErrorHandling {
    @Get("/phrasebook")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<PhraseEntry> loadPhraseEntriesFromRest();

    @Get("/phrasebook/by-category/{category}")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<PhraseEntry> loadPhraseEntriesByCategoryFromRest(int category);


    // if you need to add some configuration to the Spring RestTemplate.
    RestTemplate getRestTemplate();
}