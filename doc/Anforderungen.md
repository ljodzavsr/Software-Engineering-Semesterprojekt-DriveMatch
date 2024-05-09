# *USE-CASE DIAGRAMM: DriveMatch*

![Use case diagram](figures/use-case-diagram.drawio.svg)

---

# *USE-CASE BESCHREIBUNG*

## Profil erstellen/verwalten
> **Ziel**: Benutzer können ihre eigenen Profile erstellen und verwalten.
> 
> **Ablauf**:
> 1. Fahrlehrer und Fahrschüller füllen ein Formular mit persönlichen Informationen aus.
> 2. Das System speichert das Profil mittels `POST /users`.

## Fahrlehrer suchen
> **Ziel**: Fahrschüler können mithilfe einer Filterfunktion effektiv nach Fahrlehrern suchen, die ihren spezifischen Bedürfnissen entsprechen.
> 
> **Ablauf**:
> 1. Fahrschüler geben Suchkriterien wie location, vehicleType, transmissionType, fuelType oder rating in die Plattform ein.
> 2. Das System zeigt eine Liste von Fahrlehrern, die den Kriterien entsprechen, basierend auf der `GET /instructors` API.

## Fahrlehrerprofil anzeigen
> **Ziel**: Zugriff auf detaillierte Informationen über einen Fahrlehrer ermöglichen.
> 
> **Ablauf**:
> 1. Fahrschüler wählen einen Fahrlehrer aus der Liste.
> 2. Das System zeigt detaillierte Informationen zum Profil des Fahrlehrers über die `GET /instructors/{id}` API.

## Fahrstunde buchen
> **Ziel**: Fahrschüler können Fahrstunden bei einem Fahrlehrer ihrer Wahl buchen.
> 
> **Ablauf**:
> 1. Fahrschüler wählen einen Fahrlehrer und einen verfügbaren Termin.
> 2. Das System bucht die Fahrstunde mittels `POST /lessons`.

## Bewertung schreiben
> **Ziel**: Fahrschüler können eine Bewertung und einen Kommentar für eine abgeschlossene Fahrstunde direkt über die Seite, auf der sie ihre Buchungen einsehen, verfassen. Diese Bewertungen fließen in das Profil des Fahrlehrers ein und werden dort angezeigt, was die Sichtbarkeit und das Rating des Fahrlehrers verbessert und somit potenzielle neue Buchungen fördert.
> 
> **Ablauf**:
> 1. **Buchungen einsehen**: Fahrschüler loggen sich in ihr Konto ein und navigieren zum Abschnitt „Meine Buchungen“. Das System zeigt eine Liste der gebuchten und vergangenen Fahrstunden an.
> 2. **Bewertung auswählen**: Fahrschüler wählen eine abgeschlossene Fahrstunde aus der Liste aus, für die sie eine Bewertung abgeben möchten.
> 3. **Bewertung eingeben**: Fahrschüler geben eine Sternebewertung und einen Kommentar in das vorgesehene Formular ein.
> 4. **Bewertung speichern**: Die Bewertung wird mittels `POST /reviews` an das System übermittelt. Der Request beinhaltet die ID der Fahrstunde, die Bewertungszahl, den Kommentar und die ID des Fahrschülers.
>    - **Request Body**:
>      ```json
>      {
>        "lessonId": "lessonId123",
>        "studentId": "studentId456",
>        "rating": 5,
>        "comment": "Great experience, highly recommend!"
>      }
>      ```
> 5. **Bewertung in Fahrlehrerprofil einfügen**: Nachdem die Bewertung gespeichert wurde, wird sie automatisch im Profil des Fahrlehrers unterhalb seiner beruflichen Informationen angezeigt. Dies hilft anderen Fahrschülern, Einsicht in die Erfahrungen früherer Schüler zu gewinnen und unterstützt den Fahrlehrer bei einer hohen Bewertung, leichter gefunden zu werden, da die Suche und Filterung nach Ratings optimiert wird.

## Buchungen einsehen
> **Ziel**: Fahrschüler können ihre aktuellen und vergangenen Buchungen einsehen und überprüfen.
>
> **Ablauf**:
> 1. Fahrschüler loggen sich in ihr Konto ein.
> 2. Sie navigieren zum Abschnitt „Meine Buchungen“ auf der Plattform.
> 3. Das System zeigt eine Liste der gebuchten und vergangenen Fahrstunden an, einschließlich Details wie Datum, Uhrzeit, Fahrlehrer und Status der Buchung (geplant, abgeschlossen, storniert).

## Verfügbarkeiten pflegen
> **Ziel**: Fahrlehrer können ihre Verfügbarkeiten aktualisieren.
> 
> **Ablauf**:
> 1. Fahrlehrer loggen sich ein und aktualisieren ihre Verfügbarkeiten.
> 2. Das System speichert die aktualisierten Verfügbarkeiten.

## Buchungen verwalten
> **Ziel**: Fahrlehrer können ihre Buchungen einsehen und verwalten.
> 
> **Ablauf**:
> 1. Fahrlehrer loggen sich ein und betrachten ihre bevorstehenden und vergangenen Fahrstunden.
> 2. Das System zeigt die Buchungen basierend auf der `GET /lessons/{id}` API.


---


# *FACHLICHES DATENMODELL (ER-MODELL)*

## Entitäten und Beziehungen

### Student
- **Beschreibung**: Repräsentiert einen Fahrschüler, der Fahrstunden buchen und Bewertungen abgeben kann.
- **Attribute**:
  - `id`: Eindeutige Identifikationsnummer (Primärschlüssel).
  - `name`: Name des Fahrschülers.
  - `email`: E-Mail-Adresse des Fahrschülers.
- **Beziehungen**:
  - Bucht mehrere `Lessons`.
  - Schreibt mehrere `Reviews`.
  - Sieht `Lessons` ein, die er gebucht hat.

### Instructor
- **Beschreibung**: Repräsentiert einen Fahrlehrer, der Fahrstunden anbietet und deren Verfügbarkeiten pflegt.
- **Attribute**:
  - `id`: Eindeutige Identifikationsnummer (Primärschlüssel).
  - `name`: Name des Fahrlehrers.
  - `email`: E-Mail-Adresse des Fahrlehrers.
  - `availability`: Verfügbarkeitszeiten des Fahrlehrers, gespeichert im serialisierten Format oder verlinkt zu einer separaten Verfügbarkeitstabelle.
- **Beziehungen**:
  - Lehrt mehrere `Lessons`.

### Lesson
- **Beschreibung**: Stellt eine Fahrstunde dar, die von einem Fahrschüler gebucht und von einem Fahrlehrer gelehrt wird.
- **Attribute**:
  - `id`: Eindeutige Identifikationsnummer (Primärschlüssel).
  - `date`: Datum der Fahrstunde.
  - `time`: Uhrzeit der Fahrstunde.
  - `duration`: Dauer der Fahrstunde in Minuten.
  - `studentId`: Referenz auf den `Student` (Fremdschlüssel).
  - `instructorId`: Referenz auf den `Instructor` (Fremdschlüssel).
  - `status`: Status der Fahrstunde, kann `SCHEDULED`, `COMPLETED` oder `CANCELLED` sein (Enumeration).
- **Beziehungen**:
  - Kann von dem Fahrschüler, der sie gebucht hat, eingesehen werden.
  - Kann mehrere `Reviews` erhalten.

### Review
- **Beschreibung**: Bewertung einer Fahrstunde durch einen Fahrschüler.
- **Attribute**:
  - `id`: Eindeutige Identifikationsnummer (Primärschlüssel).
  - `lessonId`: Referenz auf die `Lesson`, die bewertet wird (Fremdschlüssel).
  - `studentId`: Referenz auf den `Student`, der die Bewertung schreibt (Fremdschlüssel).
  - `rating`: Bewertungspunktzahl.
  - `comment`: Kommentar zur Bewertung.

## Erläuterungen
Das ER-Modell von DriveMatch beschreibt die Kernentitäten `Student`, `Instructor`, `Lesson` und `Review` sowie ihre Beziehungen zueinander. Jede Entität hat spezifische Attribute, die ihre Eigenschaften definieren, und Beziehungen, die ihre Interaktionen mit anderen Entitäten darstellen. Dieses Modell ermöglicht es der Plattform, komplexe Geschäftslogiken wie das Buchen, Verwalten und Bewerten von Fahrstunden effizient zu verarbeiten und unterstützt die Sichtbarkeit der Buchungen für die Fahrschüler.


---


# *PROZESSMODELL (BPMN)*

## Prozessbeschreibung
Das erweiterte BPMN umfasst alle Kernprozesse der Interaktion zwischen Fahrschülern und Fahrlehrern auf der DriveMatch Plattform, inklusive Profilverwaltung, Such- und Buchungsvorgänge, Bewertungen, sowie Verfügbarkeits- und Buchungsmanagement.

### 1. Profilverwaltung
- **Start Event**: Der Benutzer erreicht die Profilverwaltung.
- **Task**: Benutzer füllt das Profilformular aus.
- **End Event**: Das System speichert das Profil mittels `POST /users`.

### 2. Fahrlehrer suchen
- **Start Event**: Fahrschüler startet die Suche.
- **Task**: Eingabe der Suchkriterien durch den Fahrschüler.
- **Task**: System führt Suche durch (`GET /instructors`).
- **End Event**: Anzeige der Suchergebnisse.

### 3. Fahrlehrerprofil anzeigen
- **Start Event**: Fahrschüler wählt einen Fahrlehrer aus der Suchliste.
- **Task**: Anzeigen des Profils des Fahrlehrers (`GET /instructors/{id}`).
- **End Event**: Detaillierte Informationen werden angezeigt.

### 4. Fahrstunde buchen
- **Start Event**: Fahrschüler entscheidet sich, eine Stunde zu buchen.
- **Task**: Auswahl von Fahrlehrer und Termin.
- **Task**: System bucht die Stunde (`POST /lessons`).
- **End Event**: Bestätigung der Buchung.

### 5. Bewertung schreiben
- **Start Event**: Fahrschüler sieht Buchungen ein.
- **Task**: Fahrschüler wählt eine abgeschlossene Fahrstunde.
- **Exclusive Gateway**: Entscheidung, ob eine Bewertung abgegeben wird.
- **Task**: Eingabe der Bewertung.
- **Task**: System speichert die Bewertung (`POST /reviews`).
- **End Event**: Bewertung wird im Fahrlehrerprofil angezeigt.

### 6. Buchungen einsehen (Fahrschüler)
- **Start Event**: Fahrschüler loggt sich ein.
- **Task**: Navigation zum Abschnitt „Meine Buchungen“.
- **Task**: System zeigt Buchungen an.
- **End Event**: Anzeige der Buchungsliste.

### 7. Verfügbarkeiten pflegen (Fahrlehrer)
- **Start Event**: Fahrlehrer loggt sich ein.
- **Task**: Fahrlehrer aktualisiert Verfügbarkeiten.
- **Task**: System speichert die aktualisierten Verfügbarkeiten.
- **End Event**: Verfügbarkeiten sind aktualisiert.

### 8. Buchungen verwalten (Fahrlehrer)
- **Start Event**: Fahrlehrer loggt sich ein.
- **Task**: Anzeigen bevorstehender und vergangener Fahrstunden.
- **Task**: System zeigt die Buchungen basierend auf `GET /lessons/{id}`.
- **End Event**: Fahrlehrer hat eine Übersicht der Buchungen.

## Erläuterungen
Dieses umfassende BPMN stellt sicher, dass alle Interaktionen und Transaktionen auf der DriveMatch Plattform vollständig abgebildet und in den Entwicklungsprozess integriert werden. Jeder Prozess wird klar definiert und bietet eine detaillierte Grundlage für die Implementierung der Plattform.


---


# *FAHRSCHÜLER UI-MOCKUP*

## Hauptnavigation
- **Logo**: Klick führt zurück zur Startseite.
- **Menüpunkte**:
  - **Suche**: Zugriff auf erweiterte Suche, um Fahrlehrer nach verschiedenen Kriterien zu filtern.
  - **Meine Buchungen**: Übersicht über alle gebuchten und vergangenen Fahrstunden.
  - **Profil**: Verwaltung des eigenen Profils und Einstellungen.
  - **Logout**: Abmelden von der Plattform.

## Startseite
- **Willkommensnachricht**: Kurze Einführung und Empfehlungen basierend auf bisherigen Aktivitäten.
- **Schnellsuche**: Einfaches Suchfeld für schnelle Suchanfragen.

## Suchergebnisseite
- **Suchergebnisse**: Liste der Fahrlehrer, die den Suchkriterien entsprechen.
- **Filteroptionen**: Ermöglicht das Verfeinern der Suche basierend auf Standort, Fahrzeugtyp, Getriebeart, Kraftstoffart und Bewertung.
- **Profilansicht-Button**: Direkter Link zur detaillierten Ansicht des Fahrlehrerprofils.

## Buchungsseite
- **Kalender**: Auswahl von verfügbaren Terminen des gewählten Fahrlehrers.
- **Buchungsdetails**: Formular zur Eingabe spezifischer Wünsche für die Fahrstunde.
- **Buchungsbestätigung**: Übersicht und Bestätigung der getätigten Buchung.

## Mein Profil
- **Persönliche Daten**: Formular zur Bearbeitung persönlicher Informationen wie Name und Kontakt.
- **Passwort ändern**: Option zur Aktualisierung des Passworts.
- **Speichern**: Button zum Speichern der Änderungen.

## Meine Buchungen
- **Liste der Buchungen**: Anzeige aller aktuellen und vergangenen Buchungen.
- **Bewertung abgeben**: Möglichkeit, abgeschlossene Fahrstunden zu bewerten und zu kommentieren.

# *FAHRLEHRER UI-MOCKUP*

## Hauptnavigation
- **Logo**: Klick führt zurück zur Startseite.
- **Menüpunkte**:
  - **Dashboard**: Übersicht über anstehende Fahrstunden und Bewertungen.
  - **Verfügbarkeiten verwalten**: Einstellen und Aktualisieren der eigenen Verfügbarkeiten.
  - **Meine Bewertungen**: Einblick in die erhaltenen Bewertungen und Feedback.
  - **Profil**: Verwaltung des eigenen Profils und Einstellungen.
  - **Logout**: Abmelden von der Plattform.

## Dashboard
- **Anstehende Fahrstunden**: Liste der bevorstehenden Buchungen mit Datum, Uhrzeit und Schülerinformationen.
- **Letzte Bewertungen**: Anzeige der neuesten Schülerbewertungen.

## Verfügbarkeiten verwalten
- **Kalender**: Interaktiver Kalender zum Hinzufügen und Bearbeiten verfügbarer Zeiten.
- **Speichern**: Button zum Speichern der geänderten Verfügbarkeiten.

## Meine Bewertungen
- **Bewertungsliste**: Anzeige aller erhaltenen Bewertungen, sortiert nach Datum.
- **Detailansicht**: Möglichkeit, Kommentare und die detaillierte Bewertung einzusehen.

## Mein Profil
- **Profilinformationen**: Formular zur Bearbeitung der eigenen Daten, wie Qualifikationen und spezielle Fähigkeiten.
- **Passwort ändern**: Option zur Aktualisierung des Passworts.
- **Speichern**: Button zum Speichern der Änderungen.
