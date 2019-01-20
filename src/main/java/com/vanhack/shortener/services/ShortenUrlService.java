package com.vanhack.shortener.services;

import com.vanhack.shortener.vos.ShortenUrlVO;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
public interface ShortenUrlService {

    String createShortedUrl(String url);

    ShortenUrlVO getOriginalUrl(String shortedUrl);

}
