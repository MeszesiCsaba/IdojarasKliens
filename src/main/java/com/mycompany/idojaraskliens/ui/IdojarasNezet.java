/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.ui;

import com.mycompany.idojaraskliens.model.Idojaras;

/**
 *
 * @author mcsab
 */
public interface IdojarasNezet {

    void betoltesAllapot(boolean folyamatban);

    void idojarasMegjelenitese(Idojaras idojaras);

    void hibaMegjelenitese(String uzenet);
}