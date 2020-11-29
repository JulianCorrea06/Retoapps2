package com.example.reto2apps;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {

    private String name;
    private String dbId;
    private Sprites sprites;
    private List<Stat> stats;
    private List<Type> types;
    private long time;

    public Pokemon() {
    }

    public Pokemon(String name, Sprites sprites, List<Stat> stats, List<Type> types) {
        this.name = name;
        this.sprites = sprites;
        this.stats = stats;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Type> getType() {
        return types;
    }

    public void setType(List<Type> type) {
        this.types = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
