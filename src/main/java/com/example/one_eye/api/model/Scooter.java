package com.example.one_eye.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "Scooter")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "serial")
    private String key;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    @Builder
    public Scooter(String key, double lat, double lng){
        this.key = key;
        this.lat = lat;
        this.lng = lng;
    }
}
