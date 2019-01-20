package com.vanhack.shortener.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortenUrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column
    private String originalUrl;

    @Column(unique = true)
    private String shortedKeyUrl;

    @Column
    private Date dateCreation;

    @PrePersist
    private void setDateCreation(){
        this.dateCreation = Calendar.getInstance().getTime();
    }

}
