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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "key")
    private String key;

    // TODO: 2023/01/13 외래키 설정
    @Column(name = "name")
    private String name;

    @Column(name = "battery")
    private Float battery;

    @Column(name = "xCoordinate")
    private Float xCoordinate;

    @Column(name = "yCoordinate")
    private Float yCoordinate;

    @CreationTimestamp
    @Column(name = "createAt")
    private Date createAt;

    @UpdateTimestamp
    @Column(name = "updateAt")
    private Date updateAt;

    @Column(name = "status")
    private Character status;
}
