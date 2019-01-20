package com.vanhack.shortener.services;

import com.vanhack.shortener.models.ShortenUrlEntity;
import com.vanhack.shortener.repositories.ShortenUrlRespository;
import com.vanhack.shortener.vos.ShortenUrlVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlServiceImplTest {

    private static final String FULL_URL = "https://www.google.com/search?hl=en&sugexp=les;&gs_nf=1&gs_mss=" +
            "how%20do%20I%20iron%20a%20s&tok=POkeFnEdGVTAw_InGMW-Og&cp=21&gs_id=2j&xhr=t&q=how%20do%20I%20iron" +
            "%20a%20shirt&pf=p&sclient=psy-ab&oq=how+do+I+iron+a+shirt&gs_l=&pbx=1&bav=on.2,or.r_gc.r_pw.r_cp." +
            "r_qf.&biw=1600&bih=775&cad=h";

    private static final String URL_KEY = "123";

    @Mock
    private ShortenUrlRespository shortenUrlRespository;

    @InjectMocks
    private ShortenUrlServiceImpl shortenUrlService;

    @Test
    public void createShortedUrl() {

        final String urlKey = shortenUrlService.createShortedUrl(FULL_URL);
        Assert.assertNotNull(urlKey);

    }

    @Test
    public void getOriginalUrl() {
        final ShortenUrlEntity shortenUrlEntityMock = Mockito.mock(ShortenUrlEntity.class);

        when(shortenUrlEntityMock.getOriginalUrl()).thenReturn(FULL_URL);
        when(shortenUrlEntityMock.getShortedKeyUrl()).thenReturn(URL_KEY);

        when(shortenUrlRespository.getByShortedKeyUrl(URL_KEY)).thenReturn(shortenUrlEntityMock);

        final ShortenUrlVO shortenUrlVO = shortenUrlService.getOriginalUrl(URL_KEY);

        Assert.assertEquals("Url must be equals the original Url", FULL_URL, shortenUrlVO.getOriginalUrl());

    }

    @Test
    public void getOriginalUrlNotFound() {

        when(shortenUrlRespository.getByShortedKeyUrl(URL_KEY)).thenReturn(null);

        final ShortenUrlVO shortenUrlVO = shortenUrlService.getOriginalUrl(URL_KEY);

        Assert.assertNull("When don't find the url key, must return null", shortenUrlVO);

    }

}