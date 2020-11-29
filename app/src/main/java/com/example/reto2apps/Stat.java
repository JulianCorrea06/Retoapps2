package com.example.reto2apps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stat implements Serializable {
    @SerializedName("base_stat")
    private int statValue;

    public Stat() {
    }

    public Stat(int statValue) {
        this.statValue = statValue;
    }

    public int getStatValue() {
        return statValue;
    }

    public void setStatValue(int statValue) {
        this.statValue = statValue;
    }
}
