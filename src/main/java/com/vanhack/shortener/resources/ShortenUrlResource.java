package com.vanhack.shortener.resources;

import com.vanhack.shortener.exceptions.InvalidUrlException;
import com.vanhack.shortener.exceptions.UrlNotFoundException;
import com.vanhack.shortener.services.ShortenUrlService;
import com.vanhack.shortener.vos.ShortenUrlVO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@RestController
@RequestMapping("/")
@Log4j2
public class ShortenUrlResource {

    @Autowired
    private ShortenUrlService service;

    @GetMapping("{urlKey}")
    public ResponseEntity<String> resolveUrl(@PathVariable String urlKey) throws UrlNotFoundException {
        final ShortenUrlVO shortenUrlVO = service.getOriginalUrl(urlKey);

        if(shortenUrlVO == null){
            throw new UrlNotFoundException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, shortenUrlVO.getOriginalUrl());
        return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);

    }

    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestParam("url") final String url) throws InvalidUrlException {

        if(!UrlValidator.getInstance().isValid(url)){
            throw new InvalidUrlException();
        }

        final String keyShortedUrl = service.createShortedUrl(url);

        return ResponseEntity.ok().body(keyShortedUrl);
    }

}
