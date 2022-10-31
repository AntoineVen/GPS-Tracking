package com.antoinev.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Xml
public class Monument {
    @Attribute
    public String country;
    @Attribute
    public String id;
    @Attribute
    public String name;
    @Attribute
    public String municipality;
    @Attribute
    public Double dist;
    @Attribute
    public Double lon;
    @Attribute
    public Double lat;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMunicipality() {
        return municipality;
    }
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
    public Double getDist() {
        return dist;
    }
    public void setDist(Double dist) {
        this.dist = dist;
    }
    public Double getLon() {
        return lon;
    }
    public void setLon(Double lon) {
        this.lon = lon;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
}
