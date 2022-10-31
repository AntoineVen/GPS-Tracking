package com.antoinev.tmdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Monuments {
    private List<Monument> monument = new ArrayList<Monument>();
    public List<Monument> getMonument() {
        return monument;
    }
    public void setMonument(List<Monument> monument) {
        this.monument = monument;
    }
}
