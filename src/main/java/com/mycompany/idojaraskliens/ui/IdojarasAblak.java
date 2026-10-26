/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojaraskliens.ui;

import com.mycompany.idojaraskliens.model.Idojaras;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author mcsab
 */
public class IdojarasAblak extends JFrame implements IdojarasNezet {

    private final IdojarasVezerlo vezerlo;
    private final JComboBox<Varos> varosValaszto;
    private final JButton frissitesGomb;
    private final JLabel homersekletCimke;
    private final JLabel szelsebessegCimke;
    private final JLabel idopontCimke;

    public IdojarasAblak(IdojarasVezerlo vezerlo) {
        this.vezerlo = vezerlo;

        setTitle("Időjárás");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 280);
        setLocationRelativeTo(null);

        varosValaszto = new JComboBox<>(Varos.values());
        frissitesGomb = new JButton("Frissítés");

        homersekletCimke = new JLabel("Hőmérséklet: –");
        szelsebessegCimke = new JLabel("Szélsebesség: –");
        idopontCimke = new JLabel("Időpont: –");

        JPanel valasztoPanel = new JPanel(new FlowLayout());
        valasztoPanel.add(new JLabel("Város:"));
        valasztoPanel.add(varosValaszto);
        valasztoPanel.add(frissitesGomb);

        JPanel eredmenyPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        eredmenyPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        eredmenyPanel.add(homersekletCimke);
        eredmenyPanel.add(szelsebessegCimke);
        eredmenyPanel.add(idopontCimke);

        JLabel cim = new JLabel("Aktuális időjárás", SwingConstants.CENTER);

        add(cim, BorderLayout.NORTH);
        add(valasztoPanel, BorderLayout.CENTER);
        add(eredmenyPanel, BorderLayout.SOUTH);

        frissitesGomb.addActionListener(event -> frissitesInditasa());
        frissitesInditasa();
    }

    private void frissitesInditasa() {
        Varos varos = (Varos) varosValaszto.getSelectedItem();

        if (varos != null) {
            vezerlo.frissit(varos.getLatitude(), varos.getLongitude());
        }
    }

    @Override
    public void betoltesAllapot(boolean folyamatban) {
        frissitesGomb.setEnabled(!folyamatban);
        frissitesGomb.setText(
                folyamatban ? "Lekérés..." : "Frissítés"
        );
    }

    @Override
    public void idojarasMegjelenitese(Idojaras idojaras) {
        homersekletCimke.setText(
                String.format("Hőmérséklet: %.1f °C", idojaras.homerseklet())
        );
        szelsebessegCimke.setText(
                String.format("Szélsebesség: %.1f km/h", idojaras.szelsebesseg())
        );
        idopontCimke.setText(
                "Időpont: " + idojaras.idopont()
        );
    }

    @Override
    public void hibaMegjelenitese(String uzenet) {
        JOptionPane.showMessageDialog(
                this,
                uzenet,
                "Időjárási hiba",
                JOptionPane.ERROR_MESSAGE
        );
    }
}