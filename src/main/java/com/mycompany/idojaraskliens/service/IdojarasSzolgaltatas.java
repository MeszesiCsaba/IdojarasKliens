/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.service;

import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.model.IdojarasKivetel;

/**
 *
 * @author mcsab
 */
public interface IdojarasSzolgaltatas {

    Idojaras aktualis(double latitude, double longitude)
            throws IdojarasKivetel;
}
