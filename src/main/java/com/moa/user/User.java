package com.moa.domain_user;

import javax.persistence.*;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //pk >auto increament

    @Column(length = 500, nullable = false)         //varchar(255)가 기본 > 500으로 늘려줌
    private String title;

    @Column(columnDefinition = )