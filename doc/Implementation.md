# Implementation DriveMatch

## Beschreibung des Frontends mit Screenshots der fertigen Applikation



---

## Klassendiagramm mit dem technischen Datenmodell mit Erläuterungen

![Technisches Klassendiagramm](figures/technisches_klassendiagramm.drawio.svg)

### User

- **Attribute:**
  - `id: String`: Eindeutige Identifikationsnummer des Benutzers.
  - `email: String`: E-Mail-Adresse des Benutzers, wird als Login verwendet.
  - `name: String`: Vorname des Benutzers.
  - `lastname: String`: Nachname des Benutzers.
- **Methoden:**
  - `Instructor()`: Konstruktor ohne Parameter.
  - `Instructor(email, name, lastname)`: Konstruktor mit Parametern, initialisiert einen neuen Benutzer.
  - Getter und Setter für alle Attribute.

### Lesson

- **Attribute:**
  - `id: String`: Eindeutige Identifikationsnummer der Fahrstunde.
  - `description: String`: Beschreibung der Fahrstunde.
  - `price: Double`: Preis der Fahrstunde.
  - `lessonType: LessonType`: Typ der Fahrstunde, definiert als Enumeration.
  - `lessonState: LessonState`: Status der Fahrstunde, definiert als Enumeration.
  - `instructor_id: String`: Referenz auf den `User`, der die Fahrstunde leitet oder zugewiesen ist.
- **Methoden:**
  - `Lesson()`: Standardkonstruktor.
  - `Lesson(description, price, lessonType, lessonState, instructor_id)`: Konstruktor mit Parametern zur Initialisierung einer Fahrstunde.
  - Getter und Setter für alle Attribute.

### Enumerations

#### LessonType

- Definiert mögliche Typen von Fahrstunden wie `FUNDAMENTALS`, `URBAN`, `HIGHWAY`, und `PARKING`.

#### LessonState

- Definiert mögliche Zustände einer Fahrstunde, einschließlich `NEW`, `ASSIGNED`, und `DONE`.

### DTOs

#### LessonCreateDTO

- **Attribute:**
  - `description: String`: Beschreibung der Fahrstunde.
  - `price: Double`: Preis der Fahrstunde.
  - `lessonType: LessonType`: Typ der Fahrstunde.
- **Methoden:**
  - `LessonCreateDTO()`: Konstruktor ohne Parameter.

## Beziehungen

- **User zu Lesson**:
  - Ein `User` kann mehrere `Lesson`-Objekte zugeordnet haben (0..*), was die Möglichkeit reflektiert, dass ein Fahrlehrer mehrere Fahrstunden leiten kann.

---

  ## Aufgaben und Funktionen eingebundener Drittsysteme 