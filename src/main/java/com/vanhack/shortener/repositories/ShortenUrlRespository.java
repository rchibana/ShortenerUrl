package com.vanhack.shortener.repositories;

import com.vanhack.shortener.models.ShortenUrlEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
public interface ShortenUrlRespository extends CrudRepository<ShortenUrlEntity, Long> {

    ShortenUrlEntity getByShortedKeyUrl(String shortedKeyUrl);

}
