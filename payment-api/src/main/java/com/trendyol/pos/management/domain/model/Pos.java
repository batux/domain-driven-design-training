package com.trendyol.pos.management.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

// <<Aggregate Root>>
@NoArgsConstructor
public class Pos {
    private String id;
    private String name;
    private boolean enabled;
    private Date createdDate;
    @Setter
    private Date updatedDate;
    private Type type;
    @Getter
    private ProviderType providerType;
    private Secret secrets;
    private Url urls;

    public Pos(String name, Type type, ProviderType providerType, Secret secrets, Url urls) {
        this.id = UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.enabled = true;
        this.name = name;
        if(type == null) {
            throw new RuntimeException("Type can not be null");
        }
        this.type = type;
        if(providerType == null) {
            throw new RuntimeException("Provider type can not be null!");
        }
        this.providerType = providerType;
        if(secrets == null || !secrets.isValid()) {
            throw new RuntimeException("Secret values are invalid!");
        }
        this.secrets = secrets;
        if(urls == null || !urls.isValid()) {
            throw new RuntimeException("Url values are invalid!");
        }
        this.urls = urls;
    }

    public void passivate() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public PosDetail buildDetails() {
        return new PosDetail(this.id, this.type, this.providerType);
    }
}
