/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.model.IdojarasKivetel;
import java.time.LocalDateTime;

/**
 *
 * @author mcsab
 */
public class IdojarasJsonFeldolgozo {

    public Idojaras feldolgoz(String json) throws IdojarasKivetel {
        try {
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonObject current = root.getAsJsonObject("current");

            if (current == null
                    || !current.has("temperature_2m")
                    || !current.has("wind_speed_10m")
                    || !current.has("time")) {
                throw new IdojarasKivetel(
                        "Az időjárási válaszból hiányzik egy szükséges adat."
                );
            }

            double homerseklet =
                    current.get("temperature_2m").getAsDouble();
            double szelsebesseg =
                    current.get("wind_speed_10m").getAsDouble();
            LocalDateTime idopont =
                    LocalDateTime.parse(current.get("time").getAsString());

            return new Idojaras(homerseklet, szelsebesseg, idopont);
        } catch (IdojarasKivetel e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IdojarasKivetel(
                    "Nem sikerült feldolgozni az időjárási adatokat.", e
            );
        }
    }
}
