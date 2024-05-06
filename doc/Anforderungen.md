# USE CASE BESCHREIBUNG: DriveMatch

![Use case diagram](figures/use-case-diagram.drawio.svg)

## Fahrlehrer suchen
> **Ziel**: Fahrschüler können effektiv nach Fahrlehrern suchen, die ihren spezifischen Bedürfnissen entsprechen.
> 
> **Ablauf**:
> 1. Fahrschüler geben Suchkriterien wie Verfügbarkeit, Ort und Bewertung in die Plattform ein.
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
> 1. Benutzer füllen ein Formular mit persönlichen Informationen aus.
> 2. Das System speichert das Profil mittels `POST /students` oder `POST /instructors`.

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

## Bewertungen lesen
> **Ziel**: Fahrschüler können Bewertungen von Fahrlehrern lesen.
> 
> **Ablauf**:
> 1. Fahrschüler wählen einen Fahrlehrer aus.
> 2. Das System zeigt alle verfügbaren Bewertungen über die `GET /reviews/lesson/{lessonId}` API.

## Bewertung schreiben
> **Ziel**: Fahrschüler können eine Bewertung für eine abgeschlossene Fahrstunde schreiben.
> 
> **Ablauf**:
> 1. Fahrschüler wählen die abgeschlossene Fahrstunde aus.
> 2. Fahrschüler geben eine Bewertung und einen Kommentar ein.
> 3. Das System speichert die Bewertung mittels `POST /reviews`.

## Buchungen verwalten
> **Ziel**: Fahrlehrer können ihre Buchungen einsehen und verwalten.
> 
> **Ablauf**:
> 1. Fahrlehrer loggen sich ein und betrachten ihre bevorstehenden und vergangenen Fahrstunden.
> 2. Das System zeigt die Buchungen basierend auf der `GET /lessons/{id}` API.

## Feedback geben
> **Ziel**: Fahrschüler geben Feedback zu einer Fahrstunde.
> 
> **Ablauf**:
> 1. Fahrschüler wählen die abgeschlossene Fahrstunde aus.
> 2. Fahrschüler geben Feedback in einem vorgegebenen Format.
> 3. Das System speichert das Feedback.


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

Das ER-Modell von DriveMatch beschreibt die Kernentitäten `Student`, `Instructor`, `Lesson` und `Review` sowie ihre Beziehungen zueinander. Jede Entität hat spezifische Attribute, die ihre Eigenschaften definieren, und Beziehungen, die ihre Interaktionen mit anderen Entitäten darstellen. Dieses Modell ermöglicht es der Plattform, komplexe Geschäftslogiken wie das Buchen, Verwalten und Bewerten von Fahrstunden effizient zu verarbeiten.


---


# Prozessmodell (BPMN) für DriveMatch: Buchung einer Fahrstunde

## Prozessbeschreibung

Der Buchungsprozess einer Fahrstunde auf der DriveMatch Plattform umfasst mehrere Schritte, die von der Suche nach einem Fahrlehrer bis zur finalen Buchungsbestätigung reichen.

### Schritte des Buchungsprozesses

1. **Start Event**: "Buchung starten"
   - Der Fahrschüler entscheidet sich, eine Fahrstunde zu buchen.

2. **Aufgabe**: "Fahrlehrer suchen"
   - Der Fahrschüler gibt Suchkriterien wie Standort, Verfügbarkeit und Bewertungen ein.

3. **Exklusives Gateway**: "Ist der Fahrlehrer verfügbar?"
   - Überprüfung, ob basierend auf den eingegebenen Kriterien Fahrlehrer verfügbar sind.

4. **Aufgabe**: "Fahrlehrerprofil anzeigen"
   - Bei Verfügbarkeit zeigt das System die Profile verfügbarer Fahrlehrer.
   - Bei Nichtverfügbarkeit wird der Prozess mit einer entsprechenden Benachrichtigung beendet.

5. **Aufgabe**: "Fahrstunde buchen"
   - Der Fahrschüler wählt einen Fahrlehrer aus und gibt gewünschte Zeit und Datum an.

6. **Aufgabe**: "Buchungsanfrage senden"
   - Die Buchungsanfrage wird an den ausgewählten Fahrlehrer gesendet.

7. **Exklusives Gateway**: "Buchung bestätigt?"
   - Der Fahrlehrer prüft die Anfrage und entscheidet, ob die Stunde bestätigt oder abgelehnt wird.

8. **Endereignisse**:
   - **Bestätigung**: Die Buchung wird bestätigt, und der Fahrschüler erhält eine Bestätigungsmitteilung. (End Event: "Buchung erfolgreich")
   - **Ablehnung**: Die Buchung wird nicht bestätigt, und der Fahrschüler erhält eine Ablehnungsmitteilung. (End Event: "Buchung abgelehnt")


   ---


   # Mockup oder Skizze des UIs für DriveMatch

   ...