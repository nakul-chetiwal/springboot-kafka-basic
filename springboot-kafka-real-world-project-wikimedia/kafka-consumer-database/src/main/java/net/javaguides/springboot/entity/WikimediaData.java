package net.javaguides.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="wikimedia_recentchange")
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiEventData;



}
