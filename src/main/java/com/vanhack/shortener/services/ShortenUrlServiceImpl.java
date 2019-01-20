package com.vanhack.shortener.services;

import com.vanhack.shortener.models.ShortenUrlEntity;
import com.vanhack.shortener.repositories.ShortenUrlRespository;
import com.vanhack.shortener.vos.ShortenUrlVO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.vanhack.shortener.utils.LogUtils.logInfoFinish;
import static com.vanhack.shortener.utils.LogUtils.logInfoInit;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

    @Value("${application.url}")
    private String applicationUrl;

    private static final int RANDOM_STRING_LENGTH = 6;

    @Autowired
    private ShortenUrlRespository shortenUrlRespository;

    @Override
    public String createShortedUrl(final String url) {

        logInfoInit(url);

        final String shortedKeyUrl = createUniqueUrlKey();

        final ShortenUrlEntity shortenUrlEntity = ShortenUrlEntity
                .builder()
                .originalUrl(url)
                .shortedKeyUrl(shortedKeyUrl)
                .build();

        shortenUrlRespository.save(shortenUrlEntity);

        logInfoFinish(url);

        return formatUrl(shortedKeyUrl);
    }

    @Cacheable("urlKeys")
    @Override
    public ShortenUrlVO getOriginalUrl(final String shortedKeyUrl) {

        logInfoInit(shortedKeyUrl);
        final ShortenUrlEntity shortenUrlEntity = shortenUrlRespository.getByShortedKeyUrl(shortedKeyUrl);

        final ShortenUrlVO shortenUrlVO = ShortenUrlVO.convertEntityToVO(shortenUrlEntity);

        logInfoFinish(shortedKeyUrl);

        return shortenUrlVO;
    }

    /**
     * Create unique key string to url.
     * @return a key as result
     */
    private String createUniqueUrlKey() {

        String randomString;

        do{
            randomString = RandomString.make(RANDOM_STRING_LENGTH);

            ShortenUrlVO shortenUrlVO = this.getOriginalUrl(randomString);
            if(shortenUrlVO == null){
                return randomString;
            }
        } while(true);

    }

    /**
     * Create a formatted url
     * @param urlKey key generated and stored in database
     * @return formatted url
     */
    private String formatUrl(final String urlKey){
        return String.format("%s/%s", applicationUrl, urlKey);
    }
}
