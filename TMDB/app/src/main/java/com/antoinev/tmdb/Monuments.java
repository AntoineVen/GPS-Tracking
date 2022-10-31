package com.antoinev.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Xml
public class Monuments {
    @Element
    public List<Monument> monument;

    public List<Monument> getMonument() {
        return monument;
    }
    public void setMonument(List<Monument> monument) {
        this.monument = monument;
    }
}
