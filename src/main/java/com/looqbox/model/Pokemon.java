/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.looqbox.model;

import org.springframework.stereotype.Component;

@Component
public class Pokemon {

    public Pokemon() {
    }

    public Pokemon(String name, String highlight) {
        this.name = name;
        this.highlight = highlight;
    }

    private String name;
    private String highlight;

    public String getName() {
        return name;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

}
