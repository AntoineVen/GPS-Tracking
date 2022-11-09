package com.example.clswwearosgpsmemo2022;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

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
