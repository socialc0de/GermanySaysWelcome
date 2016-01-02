package com.github.socialc0de.gsw.api.interfaces;

import com.github.socialc0de.gsw.customClasses.api.FaqEntry;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
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
public interface FaqEntryRestClient extends RestClientErrorHandling {
    @Get("/faq/by-category/{category}")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<FaqEntry> loadFaqEntriesByCategoryFromRest(int category);

    @Get("/faq/by-audience/{audience}")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<FaqEntry> loadFaqEntriesByAudienceFromRest(int audience);

    @Get("/faq")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader({"User-Agent"})
    ArrayList<FaqEntry> loadFaqEntriesFromRest();

    @Post("/faq")
    void addFaqEntry(FaqEntry entry);


    // if you need to add some configuration to the Spring RestTemplate.
    RestTemplate getRestTemplate();
}