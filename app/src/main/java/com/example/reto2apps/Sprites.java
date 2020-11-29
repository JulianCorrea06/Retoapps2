package com.example.reto2apps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sprites implements Serializable {


    @SerializedName("front_default")
    private String frontSprite;

    public Sprites() {
    }

    public Sprites(String frontSprite) {
        this.frontSprite = frontSprite;
    }

    public String getFrontSprite() {
        return frontSprite;
    }

    public void setFrontSprite(String frontSprite) {
        this.frontSprite = frontSprite;
    }
}
