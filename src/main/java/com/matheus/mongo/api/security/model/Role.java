package com.matheus.mongo.api.security.model;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {


    public Role(String name) {
        this.name = name;
    }
    public Role() {
    }

    @Id
    private Long id;
    private String name;


    @Override
    public String getAuthority() {
        return  this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}