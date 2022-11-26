package com.example.clswwearosgpsmemo2022.WikiLovesMonument;

import android.util.Log;

import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Xml;

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
    @Attribute
    public String image;

    public String getName() {
        return name;
    }
    public String getParsedName(){
        if(name == null) return new String("No name found");
        String parsed = name.replaceAll("[\\[\\]]", "");
        parsed = parsed.split("[\\u007C\\(]")[0];
        parsed = parsed.split("&lt;br")[0];
        if(parsed.length()>80) name = name.substring(0, 80);
        Log.d("name", parsed);
        return parsed;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getImageURL(){
        return "https://commons.wikimedia.org/wiki/Special:FilePath/" + image + "?width=200";
    }
}
