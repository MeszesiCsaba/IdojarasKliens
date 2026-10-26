/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.ui;

/**
 *
 * @author mcsab
 */
public enum Varos {
    MAROSVASARHELY("Marosvásárhely", 46.54, 24.56),
    KOLOZSVAR("Kolozsvár", 46.77, 23.59),
    BUKAREST("Bukarest", 44.43, 26.10),
    BUDAPEST("Budapest", 47.50, 19.04),
    BECS("Bécs", 48.21, 16.37);

    private final String nev;
    private final double latitude;
    private final double longitude;

    Varos(String nev, double latitude, double longitude) {
        this.nev = nev;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNev() {
        return nev;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return nev;
    }
}
