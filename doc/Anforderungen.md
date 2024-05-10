# *USE-CASE DIAGRAMM: DriveMatch*

![Use case diagram](figures/use-case-diagram.drawio.svg)

---

## Use-Case Beschreibungen

### Mitarbeiter DriveMatch

#### Fahrstunden anzeigen
> **Ziel**: Ermöglicht es Mitarbeitern, alle geplanten Fahrstunden einzusehen.
> 
> **Ablauf**:
> 1. Der Mitarbeiter navigiert zur Option "Fahrstunden anzeigen".
> 2. Das System listet alle geplanten Fahrstunden auf.

#### Fahrstunde planen
> **Ziel**: Mitarbeiter können neue Fahrstunden im System eintragen und planen.
> 
> **Ablauf**:
> 1. Der Mitarbeiter navigiert zur Option "Fahrstunde planen".
> 2. Er gibt Details wie Datum, Uhrzeit, Fahrlehrer und Typ der Fahrstunde ein.
> 3. Das System speichert die Fahrstunde und bestätigt die Eingabe.

#### Alle Fahrlehrer anzeigen
> **Ziel**: Ermöglicht es Mitarbeitern, eine Liste aller registrierten Fahrlehrer einzusehen.
> 
> **Ablauf**:
> 1. Der Mitarbeiter wählt die Option "Alle Fahrlehrer anzeigen".
> 2. Das System zeigt eine Liste mit Details zu jedem Fahrlehrer.

#### Dashboard Status
> **Ziel**: Überblick über den aktuellen Status von Fahrstunden und Fahrlehrern.
> 
> **Ablauf**:
> 1. Der Mitarbeiter öffnet das Dashboard.
> 2. Das System präsentiert aktuelle Statistiken und Statusinformationen zu Fahrstunden und Verfügbarkeiten.

### Fahrlehrer

#### Registrieren
> **Ziel**: Ermöglicht neuen Fahrlehrern, sich im System zu registrieren.
> 
> **Ablauf**:
> 1. Der Fahrlehrer gibt seine persönlichen Informationen in ein Registrierungsformular ein.
> 2. Das System erstellt ein neues Fahrlehrerprofil und bestätigt die Registrierung.

#### Eigene Daten anzeigen
> **Ziel**: Fahrlehrer können ihre persönlichen und beruflichen Daten einsehen und aktualisieren.
> 
> **Ablauf**:
> 1. Der Fahrlehrer wählt "Eigene Daten anzeigen".
> 2. Das System zeigt die gespeicherten Informationen an, die der Fahrlehrer bei Bedarf aktualisieren kann.

#### Fahrstunde buchen
> **Ziel**: Ermöglicht Fahrlehrern, verfügbare Fahrstunden zu buchen.
> 
> **Ablauf**:
> 1. Der Fahrlehrer wählt eine verfügbare Fahrstunde aus.
> 2. Das System bestätigt die Buchung und aktualisiert den Status der Fahrstunde.

#### Fahrstunde abschließen
> **Ziel**: Markiert eine durchgeführte Fahrstunde als abgeschlossen.
> 
> **Ablauf**:
> 1. Der Fahrlehrer gibt an, dass eine Fahrstunde abgeschlossen wurde.
> 2. Das System aktualisiert den Status der Fahrstunde und informiert den Fahrschüler.

## Fachliches Datenmodell (ER-Modell)

### Entitäten und Beziehungen

#### Fahrlehrer
- **Attribute**:
  - `id`: Eindeutige Identifikationsnummer
  - `name`: Name des Fahrlehrers
  - `email`: E-Mail-Adresse
- **Beziehungen**:
  - Unterrichtet `Fahrstunden`

#### Fahrstunde
- **Attribute**:
  - `id`: Eindeutige Identifikationsnummer
  - `date`: Datum der Fahrstunde
  - `time`: Uhrzeit der Fahrstunde
  - `type`: Typ der Fahrstunde (Theorie, Praxis)
  - `status`: Status (geplant, gebucht, abgeschlossen)
- **Beziehungen**:
  - Wird unterrichtet von `Fahrlehrer`

## Prozessmodell (BPMN)

### Prozessbeschreibung

#### 1. Fahrstunde planen
- **Start Event**: Mitarbeiter erreicht das Planungsmodul.
- **Task**: Eingabe der Fahrstundendetails.
- **End Event**: Fahrstunde ist geplant und gespeichert.

#### 2. Fahrstunde buchen
- **Start Event**: Fahrlehrer wählt verfügbare Fahrstunde.
- **Task**: Auswahl und Buchung der Fahrstunde.
- **End Event**: Fahrstunde ist erfolgreich gebucht.

#### 3. Fahrstunde abschließen
- **Start Event**: Fahrlehrer markiert Fahrstunde als abgeschlossen.
- **Task**: Bestätigung des Abschlusses der Fahrstunde.
- **End Event**: Fahrstunde ist im System als abgeschlossen markiert.
