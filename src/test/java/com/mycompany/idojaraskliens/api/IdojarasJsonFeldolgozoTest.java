/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.api;

import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.model.IdojarasKivetel;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mcsab
 */
class IdojarasJsonFeldolgozoTest {

    private final IdojarasJsonFeldolgozo feldolgozo =
            new IdojarasJsonFeldolgozo();

    @Test
    void feldolgozzaAzErvenyesValaszt() throws IdojarasKivetel {
        String json = """
                {
                  "current": {
                    "temperature_2m": 7.3,
                    "wind_speed_10m": 11.2,
                    "time": "2026-10-26T19:30"
                  }
                }
                """;

        Idojaras eredmeny = feldolgozo.feldolgoz(json);

        assertEquals(7.3, eredmeny.homerseklet());
        assertEquals(11.2, eredmeny.szelsebesseg());
        assertEquals(
                LocalDateTime.parse("2026-10-26T19:30"),
                eredmeny.idopont()
        );
    }

    @Test
    void hibatDobHaHianyzikEgyAdat() {
        String json = """
                {
                  "current": {
                    "temperature_2m": 7.3,
                    "time": "2026-10-26T19:30"
                  }
                }
                """;

        assertThrows(
                IdojarasKivetel.class,
                () -> feldolgozo.feldolgoz(json)
        );
    }

    @Test
    void hibatDobHibasJsonEseten() {
        assertThrows(
                IdojarasKivetel.class,
                () -> feldolgozo.feldolgoz("ez nem JSON")
        );
    }
}
