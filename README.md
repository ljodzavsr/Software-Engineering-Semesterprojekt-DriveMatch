# DriveMatch

DriveMatch ist eine Plattform zur effizienten Verwaltung von Fahrstunden und Fahrlehrern. 

## Funktionen

### Gemeinsam für User (Fahrlehrer) und Admin (Mitarbeiter der Fahrschule)

- **Anmeldung und Registrierung:** Usermanagement (Sign up, Sign in) durch Auth0.
- **Fahrstunden verwalten:** 
  - **Zuweisen und Abschließen:** User und Admin können sich Fahrstunden zuweisen und nach Abschluss als erledigt markieren. 
  - **Übersicht:** User und Admin sehen eine Liste aller Fahrstunden mit Details, Preis, zugewiesenem Nutzer und Status.
  - **Filtern nach Typ und Preis:** User und Admin können Fahrstunden nach Typ und ihrem eigenen maximalen Stundensatz filtern.
- **Account Page:** User und Admin sehen Name, Vorname, E-Mail und Rolle.

### Spezifisch für Admin (Mitarbeiter der Fahrschule)

- **Erstellen von Fahrstunden:** Admins erstellen Fahrstunden mit grundlegenden Infos und dem individuell angebotenen Preis an den potenziellen Fahrschüler pro Stunde.
- **Detailbeschreibungen:** GPT-API generiert detaillierte Beschreibungen aus den eingegebenen Informationen.
- **Fahrlehrerliste:** Admins sehen eine Tabelle aller registrierten Fahrlehrer (User) mit Vorname, Name und E-Mail.
- **Fahrschülerverwaltung:** Admins sehen eine Liste aller registrierten Fahrschüler.

## Technische Architektur

- **Backend:** Spring Boot Framework
- **Frontend:** Svelte
- **Datenbank:** MongoDB auf Atlas
- **Hosting:** Azure App Service
- **Authentifizierung:** Auth0
- **Drittsysteme:**
  - **Disify API:** Zur Validierung von E-Mail-Adressen.
  - **GPT API:** Zur Generierung detaillierter Fahrstundenbeschreibungen.
- **Code-Analyse:** SonarCloud zur Code-Analyse und Qualitätssicherung.

