package com.example.one_eye.api.model;

import java.time.LocalDateTime;
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
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Scooter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "serial")
    private String key;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    @Builder
    public Scooter(String key, String lat, String lng){
        this.key = key;
        this.lat = lat;
        this.lng = lng;
    }
}
