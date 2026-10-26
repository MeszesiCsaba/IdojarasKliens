/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.service;

import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.model.IdojarasKivetel;
import java.time.LocalDateTime;

/**
 *
 * @author mcsab
 */
public class MockIdojaras implements IdojarasSzolgaltatas {

    private final Idojaras valasz;
    private final boolean hibatDobjon;

    public MockIdojaras(Idojaras valasz, boolean hibatDobjon) {
        this.valasz = valasz;
        this.hibatDobjon = hibatDobjon;
    }

    @Override
    public Idojaras aktualis(double latitude, double longitude)
            throws IdojarasKivetel {
        if (hibatDobjon) {
            throw new IdojarasKivetel("A tesztelt időjárás-szolgáltatás hibát jelzett.");
        }

        if (valasz == null) {
            return new Idojaras(18.5, 12.0, LocalDateTime.now());
        }

        return valasz;
    }
}
