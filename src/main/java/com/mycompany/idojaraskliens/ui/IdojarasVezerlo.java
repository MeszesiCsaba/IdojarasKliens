/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.ui;

import com.mycompany.idojaraskliens.model.Idojaras;
import com.mycompany.idojaraskliens.model.IdojarasKivetel;
import com.mycompany.idojaraskliens.service.IdojarasSzolgaltatas;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 *
 * @author mcsab
 */
public class IdojarasVezerlo {

    private final IdojarasSzolgaltatas szolgaltatas;
    private final IdojarasNezet nezet;

    public IdojarasVezerlo(
            IdojarasSzolgaltatas szolgaltatas,
            IdojarasNezet nezet
    ) {
        this.szolgaltatas = szolgaltatas;
        this.nezet = nezet;
    }

    public void frissit(double latitude, double longitude) {
        nezet.betoltesAllapot(true);

        SwingWorker<Idojaras, Void> worker =
                new SwingWorker<>() {

            @Override
            protected Idojaras doInBackground()
                    throws IdojarasKivetel {
                return szolgaltatas.aktualis(latitude, longitude);
            }

            @Override
            protected void done() {
                nezet.betoltesAllapot(false);

                try {
                    nezet.idojarasMegjelenitese(get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    nezet.hibaMegjelenitese(
                            "Az időjárás-lekérés megszakadt."
                    );
                } catch (ExecutionException e) {
                    Throwable ok = e.getCause();
                    String uzenet = ok.getMessage();

                    if (uzenet == null || uzenet.isBlank()) {
                        uzenet = "Nem sikerült lekérni az időjárást.";
                    }

                    nezet.hibaMegjelenitese(uzenet);
                }
            }
        };

        worker.execute();
    }
}