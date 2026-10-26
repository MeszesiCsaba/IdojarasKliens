/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.idojaraskliens;

import com.mycompany.idojaraskliens.api.IdojarasJsonFeldolgozo;
import com.mycompany.idojaraskliens.api.OpenMeteoKliens;
import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.service.IdojarasSzolgaltatas;
import com.mycompany.idojaraskliens.service.MockIdojaras;
import com.mycompany.idojaraskliens.ui.IdojarasAblak;
import java.net.http.HttpClient;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.swing.SwingUtilities;

/**
 *
 * @author mcsab
 */
public class IdojarasKliens {

    public static void main(String[] args) {
        boolean mockMod = Arrays.asList(args).contains("--mock");

        SwingUtilities.invokeLater(() -> {
            IdojarasSzolgaltatas szolgaltatas;

            if (mockMod) {
                Idojaras tesztAdat = new Idojaras(
                        18.5,
                        12.0,
                        LocalDateTime.now()
                );

                szolgaltatas = new MockIdojaras(tesztAdat, false);
            } else {
                HttpClient httpClient = HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(10))
                        .build();

                szolgaltatas = new OpenMeteoKliens(
                        httpClient,
                        new IdojarasJsonFeldolgozo()
                );
            }

            IdojarasAblak ablak = new IdojarasAblak(szolgaltatas);
            ablak.setVisible(true);
        });
    }
}
