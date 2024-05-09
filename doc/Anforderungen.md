# USE-CASE Diagramm: DriveMatch

![Use case diagram](figures/use-case-diagram.drawio.svg)

---

# USE-CASE BESCHREIBUNG: DriveMatch

## Fahrlehrer suchen
> **Ziel**: Fahrschüler können effektiv nach Fahrlehrern suchen, die ihren spezifischen Bedürfnissen entsprechen.
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

## Profil erstellen/verwalten
> **Ziel**: Benutzer können ihre eigenen Profile erstellen und verwalten.
> 
> **Ablauf**:
> 1. Fahrlehrer und Fahrschüller füllen ein Formular mit persönlichen Informationen aus.
> 2. Das System speichert das Profil mittels `POST /users`.

## Verfügbarkeiten pflegen
> **Ziel**: Fahrlehrer können ihre Verfügbarkeiten aktualisieren.
> 
> **Ablauf**:
> 1. Fahrlehrer loggen sich ein und aktualisieren ihre Verfügbarkeiten.
> 2. Das System speichert die aktualisierten Verfügbarkeiten.

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

## Buchungen verwalten
> **Ziel**: Fahrlehrer können ihre Buchungen einsehen und verwalten.
> 
> **Ablauf**:
> 1. Fahrlehrer loggen sich ein und betrachten ihre bevorstehenden und vergangenen Fahrstunden.
> 2. Das System zeigt die Buchungen basierend auf der `GET /lessons/{id}` API.

## Buchungen einsehen
> **Ziel**: Fahrschüler können ihre aktuellen und vergangenen Buchungen einsehen und überprüfen.
>
> **Ablauf**:
> 1. Fahrschüler loggen sich in ihr Konto ein.
> 2. Sie navigieren zum Abschnitt „Meine Buchungen“ auf der Plattform.
> 3. Das System zeigt eine Liste der gebuchten und vergangenen Fahrstunden an, einschließlich Details wie Datum, Uhrzeit, Fahrlehrer und Status der Buchung (geplant, abgeschlossen, storniert).


---


# FACHLICHES DATENMODELL (ER-MODELL): DriveMatch

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


# Umfassendes Prozessmodell (BPMN) für DriveMatch

## Prozessbeschreibung
Das erweiterte BPMN umfasst alle Kernprozesse der Interaktion zwischen Fahrschülern und Fahrlehrern auf der DriveMatch Plattform, inklusive Profilverwaltung, Such- und Buchungsvorgänge, Bewertungen, sowie Verfügbarkeits- und Buchungsmanagement.

## Kernprozesse

### Profilmanagement
#### Start Event: "Profil erstellen/verwalten"
- **Beschreibung**: Benutzer starten den Prozess der Profilerstellung oder -aktualisierung.
  
#### Aufgabe: "Profilformular ausfüllen"
- **Beschreibung**: Benutzer füllen ein Formular mit persönlichen Daten aus.

#### Aufgabe: "Profil speichern"
- **Beschreibung**: Das System speichert das Profil mittels `POST /students` oder `POST /instructors`.

#### End Event: "Profil erstellt/aktualisiert"
- **Beschreibung**: Bestätigung, dass das Profil erfolgreich erstellt oder aktualisiert wurde.

### Suche und Buchung von Fahrstunden
#### Start Event: "Fahrstunde buchen"
- **Beschreibung**: Fahrschüler starten den Buchungsprozess.

#### Aufgabe: "Fahrlehrer suchen"
- **Beschreibung**: Fahrschüler geben Suchkriterien ein.

#### Exklusives Gateway: "Fahrlehrer gefunden?"
- **Beschreibung**: Überprüfung der Verfügbarkeit.

#### Aufgabe: "Fahrlehrerprofil anzeigen"
- **Beschreibung**: Anzeigen von Profildetails.

#### Aufgabe: "Fahrstunde auswählen und buchen"
- **Beschreibung**: Auswahl von Datum und Uhrzeit.

#### Aufgabe: "Buchungsanfrage senden"
- **Beschreibung**: Die Anfrage wird verarbeitet.

#### Exklusives Gateway: "Buchung bestätigt?"
- **Beschreibung**: Fahrlehrer bestätigt oder lehnt die Buchung ab.

#### End Event: "Buchung erfolgreich/abgelehnt"
- **Beschreibung**: Fahrschüler erhalten eine Rückmeldung.

### Bewertungen
#### Start Event: "Bewertung abgeben"
- **Beschreibung**: Fahrschüler starten den Bewertungsprozess.

#### Aufgabe: "Bewertungsformular ausfüllen"
- **Beschreibung**: Details zur Bewertung werden eingegeben.

#### Aufgabe: "Bewertung speichern"
- **Beschreibung**: Das System speichert die Bewertung mittels `POST /reviews`.

#### End Event: "Bewertung abgeschlossen"
- **Beschreibung**: Die Bewertung wurde gespeichert.

### Verfügbarkeiten pflegen
#### Start Event: "Verfügbarkeiten aktualisieren"
- **Beschreibung**: Fahrlehrer aktualisieren ihre Verfügbarkeiten.

#### Aufgabe: "Verfügbarkeiten eintragen"
- **Beschreibung**: Neue Verfügbarkeiten werden eingetragen.

#### Aufgabe: "Verfügbarkeiten speichern"
- **Beschreibung**: Das System speichert die aktualisierten Verfügbarkeiten.

#### End Event: "Verfügbarkeiten aktualisiert"
- **Beschreibung**: Bestätigung der Aktualisierung.

### Buchungen einsehen (für Fahrschüler)
#### Start Event: "Buchungen einsehen"
- **Beschreibung**: Fahrschüler entscheiden sich, ihre Buchungen einzusehen.

#### Aufgabe: "Login"
- **Beschreibung**: Fahrschüler loggen sich ein.

#### Aufgabe: "Navigieren zur Buchungsübersicht"
- **Beschreibung**: Fahrschüler navigieren zur entsprechenden Seite.

#### Aufgabe: "Buchungen anzeigen"
- **Beschreibung**: Das System zeigt die Buchungen an.

#### End Event: "Buchungen eingesehen"
- **Beschreibung**: Fahrschüler haben eine Übersicht ihrer Buchungen erhalten.

## Erläuterungen
Dieses umfassende BPMN stellt sicher, dass alle Interaktionen und Transaktionen auf der DriveMatch Plattform vollständig abgebildet und in den Entwicklungsprozess integriert werden. Jeder Prozess wird klar definiert und bietet eine detaillierte Grundlage für die Implementierung der Plattform.


---


# UI Mockup für DriveMatch

## Hauptlayout

### Navigationsleiste
- **Position**: Oben am Bildschirm.
- **Elemente**:
  - **Logo**: Links ausgerichtet, klickbar, führt zurück zur Startseite.
  - **Home**: Startseite Link.
  - **Suche**: Zugang zur Suchfunktion.
  - **Meine Buchungen**: Übersicht der gebuchten und vergangenen Fahrstunden.
  - **Profil**: Zugang zu persönlichen Einstellungen und Account-Management.
  - **Logout**: Ausloggen aus dem System.

### Suchbereich
- **Position**: Zentriert auf der Startseite.
- **Funktion**:
  - **Suchfeld**: Ermöglicht das schnelle Finden von Fahrlehrern nach Schlüsselworten.
  - **Erweiterte Suche**: Optionen für detaillierte Suchkriterien (z.B. Standort, Fahrzeugtyp, Verfügbarkeit).

### Profilseiten
- **Fahrlehrer Profil**:
  - **Profilfoto**
  - **Kontaktinformationen**
  - **Qualifikationen**
  - **Verfügbarkeitskalender**
  - **Bewertungen**
- **Fahrschüler Profil**:
  - **Profilfoto**
  - **Kontaktinformationen**
  - **Buchungshistorie**
  - **Bewertungen abgegeben**

### Buchungsseite
- **Kalenderansicht** für Verfügbarkeiten.
- **Auswahlwerkzeuge** für Datum und Uhrzeit.
- **Buchungsbutton** zur Bestätigung der Fahrstunde.

### Bewertungsseite
- **Listendarstellung** aller abgeschlossenen, aber noch nicht bewerteten Fahrstunden.
- **Sternebewertungssystem** und **Textfeld** für Kommentare.

## Designelemente
- **Farbschema**: Beruhigende Farben wie Blau und Grau, die für eine entspannte Nutzererfahrung sorgen.
- **Schriftarten**: Klare, gut lesbare Schriftarten.
- **Buttons**: Groß und farblich abgesetzt, um Benutzeraktionen zu erleichtern.
- **Responsive Design**: Optimiert für verschiedene Geräte, von Desktops bis hin zu Mobiltelefonen.

