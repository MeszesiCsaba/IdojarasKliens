/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.api;

import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.model.IdojarasKivetel;
import com.mycompany.idojaraskliens.service.IdojarasSzolgaltatas;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 *
 * @author mcsab
 */
public class OpenMeteoKliens implements IdojarasSzolgaltatas {

    private final HttpClient httpClient;
    private final IdojarasJsonFeldolgozo jsonFeldolgozo;

    public OpenMeteoKliens(
            HttpClient httpClient,
            IdojarasJsonFeldolgozo jsonFeldolgozo
    ) {
        this.httpClient = httpClient;
        this.jsonFeldolgozo = jsonFeldolgozo;
    }

    @Override
    public Idojaras aktualis(double latitude, double longitude)
            throws IdojarasKivetel {

        if (latitude < -90 || latitude > 90
                || longitude < -180 || longitude > 180) {
            throw new IdojarasKivetel("Érvénytelen földrajzi koordináta.");
        }

        String url = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&current=temperature_2m,wind_speed_10m"
                + "&timezone=auto";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new IdojarasKivetel(
                        "Az időjárás-szerver hibát adott vissza: "
                        + response.statusCode()
                );
            }

            return jsonFeldolgozo.feldolgoz(response.body());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IdojarasKivetel(
                    "Az időjárás-lekérés megszakadt.", e
            );
        } catch (IOException e) {
            throw new IdojarasKivetel(
                    "Nem sikerült elérni az időjárás-szervert.", e
            );
        }
    }
}
