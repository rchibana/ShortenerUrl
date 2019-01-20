package com.vanhack.shortener.vos;

import com.vanhack.shortener.models.ShortenUrlEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortenUrlVO {

    private String originalUrl;
    private String shortedKeyUrl;

    public static ShortenUrlVO convertEntityToVO(final ShortenUrlEntity entity){

        ShortenUrlVO shortenUrlVO = null;

        if(entity != null) {
            shortenUrlVO = ShortenUrlVO.builder()
                .originalUrl(entity.getOriginalUrl())
                .shortedKeyUrl(entity.getShortedKeyUrl())
                .build();
        }
        return shortenUrlVO;
    }

}
