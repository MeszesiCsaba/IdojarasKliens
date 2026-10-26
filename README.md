# Időjárás-kliens

Java Swing alapú asztali alkalmazás, amely az Open-Meteo API-ból kér le aktuális időjárási adatokat.

## Funkciók
- Városválasztás és időjárás-frissítés.
- Valódi API és mock mód (`--mock`).
- Háttérben futó lekérések `SwingWorker` segítségével.
- Hibakezelés és JSON-feldolgozás.

## Futtatás
Nyisd meg a projektet NetBeansben Maven-projektként, majd indítsd el a `com.mycompany.idojaraskliens.IdojarasKliens` főosztályt.

Mock mód: `--mock`

Tesztek futtatása:

```bash
mvn test
```

## API
[Open-Meteo](https://open-meteo.com/)

## AI-használat
Nagyrészt használtam az ai-t, nélküle nem hiszem megtudtam volna oldani
