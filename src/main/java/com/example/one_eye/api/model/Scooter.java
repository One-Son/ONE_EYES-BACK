package com.example.one_eye.api.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Scooter")
@Getter
@Setter
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

    @Column(name = "xCoordinate")
    private Float coordinateX;

    @Column(name = "yCoordinate")
    private Float coordinateY;
}
