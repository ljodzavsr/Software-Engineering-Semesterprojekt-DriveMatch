# DriveMatch

DriveMatch ist eine Plattform für die effiziente Verwaltung von Fahrstunden und Fahrlehrern.

## Funktionen

### Gemeinsam für Benutzer (Fahrlehrer) und Administratoren (Fahrschulpersonal)

- **Anmeldung und Registrierung:** Benutzerverwaltung (Anmeldung, Einloggen) durch Auth0.
- **Verwalten von Fahrstunden:**
  - **Zuweisen und Abschliessen:** Benutzer und Administratoren können Fahrstunden sich selbst zuweisen und sie nach Abschluss als abgeschlossen markieren.
  - **Übersicht:** Benutzer und Administratoren können eine Liste aller Fahrstunden mit Details, Preis, zugewiesenem Benutzer und Status sehen.
  - **Filtern nach Typ und Preis:** Benutzer und Administratoren können Fahrstunden nach Typ und ihrem eigenen maximalen Stundensatz filtern.
- **Kontoseite:** Benutzer und Administratoren können ihren Namen, Vornamen, E-Mail und Rolle sehen.
- **Wettervorhersage:** Benutzer und Administratoren können die aktuelle Wettervorhersage und eine 5-Tage-Vorhersage für Zürich sehen.

### Spezifisch für Administratoren (Fahrschulpersonal)

- **Erstellen von Fahrstunden:** Administratoren können Fahrstunden mit grundlegenden Informationen und den dem potenziellen Fahrschüler angebotenen Stundensatz erstellen.
- **Detaillierte Beschreibungen:** Die GPT-API generiert detaillierte Beschreibungen basierend auf den eingegebenen Informationen.
- **Fahrlehrerliste:** Administratoren können eine Tabelle aller registrierten Fahrlehrer (Benutzer) mit Vorname, Nachname und E-Mail sehen.

## Technische Architektur

- **Backend:** Spring Boot Framework
- **Frontend:** Svelte
- **Datenbank:** MongoDB auf Atlas
- **Hosting:** Azure App Service
  - **Link:** <https://drivematch.azurewebsites.net/>

- **Drittsysteme:**
  - **Auth0:** Benutzerverwaltung, Authentifizierung/Autorisierung
  - **SonarCloud:** Code-Analyse und Qualitätssicherung
  - **Cypress:** End-to-End-Tests
  - **Postman:** API-Dokumentation
  - **Disify API:** E-Mail-Validierung
  - **GPT API:** Generierung detaillierter Beschreibungen von Fahrstunden
  - **OpenWeatherMap API:** Wettervorhersagedaten
