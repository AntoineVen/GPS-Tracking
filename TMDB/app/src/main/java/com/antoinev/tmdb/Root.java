package com.antoinev.tmdb;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.HashMap;
import java.util.Map;

@Xml
public class Root {
    @Element
    public Monuments monuments;
    public Monuments getMonuments() {
        return monuments;
    }
    public void setMonuments(Monuments monuments) {
        this.monuments = monuments;
    }


}