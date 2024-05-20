# Anforderungen und Spezifikationen für DriveMatch

## Use-Case Diagramm und Use-Case Beschreibung

![Use case diagram](figures/use-case-diagram.drawio.svg)

### Haupt-Use-Cases:

1. **Fahrstunden selbst zuweisen**
   - **Akteure**: Fahrlehrer, Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Nutzer und Admins weisen sich selbst Fahrstunden zu und markieren diese nach Abschluss als vollendet.

2. **Fahrstunden anzeigen**
   - **Akteure**: Fahrlehrer, Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Alle Nutzer können eine Liste aller Fahrstunden mit Details zur Fahrstunde, dem Fahrstunden-Typ, dem Kunden angebotenen Stundensatz, zugewiesenem Nutzer und Status einsehen.

3. **Fahrstunden filtern**
   - **Akteure**: Fahrlehrer, Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Nutzer können Fahrstunden nach Typ und ihrem maximalen Stundensatz filtern.

4. **Fahrstunden erstellen**
   - **Akteure**: Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Admins erstellen Fahrstunden, indem sie grundlegende Informationen, den Fahrstunden-Typ und den angebotenen Stundensatz eingeben.

5. **Registrieren und Anmelden**
   - **Akteure**: Fahrlehrer, Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Nutzer verwalten ihre Konten durch Registrierung und Anmeldung mittels Auth0.

6. **Account-Detailseite anzeigen**
   - **Akteure**: Fahrlehrer, Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Nutzer können ihre Account-Details wie Name, Vorname, E-Mail und Rolle einsehen.

7. **Liste aller Fahrlehrer anzeigen**
   - **Akteure**: Fahrschulmitarbeiter (Admins)
   - **Beschreibung**: Admins können eine Liste aller registrierten Fahrlehrer mit Details wie Vorname, Nachname und E-Mail einsehen.

---

## Fachliches Datenmodell (ER-Diagramm) mit Erläuterungen

![ER-diagram](figures/ER-diagram.svg)

### Entitäten und Beziehungen:

- **User**
  - Attribute: id, name, lastname, email, role
  - Beziehungen: Kann mehrere Fahrstunden sich selbst zuweisen. Kann auf Account-Details und Fahrlehrerliste (nur Admins) zugreifen.

- **Lesson**
  - Attribute: id, description, price, lessonStatus, lessonType, instructor_id
  - Beziehungen: Wird von einem Fahrlehrer oder Admin selbst zugewiesen und entsprechend abgeschlossen.

---

## Prozessmodell (BPMN-Diagramm) mit Erläuterungen

![BPMN-diagram](figures/BPMN-diagram.svg)

### Zustände und Übergänge

Das Zustandsdiagramm veranschaulicht den Lebenszyklus einer Fahrstunde auf der DriveMatch Plattform. Nachfolgend die Beschreibung der einzelnen Zustände und Übergänge:

#### Zustände

- **NEW**: Die Fahrstunde ist neu erfasst und noch keinem Nutzer (Fahrlehrer oder Admin) zugewiesen.
- **ASSIGNED**: Ein Nutzer hat die Fahrstunde übernommen und ist nun dafür verantwortlich.
- **DONE**: Die Fahrstunde wurde von dem Nutzer, der sie übernommen hatte, als abgeschlossen markiert.

#### Übergänge

- **Fahrstunde wird erfasst**: Der Startpunkt des Prozesses, wenn eine neue Fahrstunde im System erfasst wird. Dieser Schritt wird typischerweise von einem Administrator durchgeführt, der eine Fahrstunde mit den notwendigen Details wie Beschreibung, Typ und Preis erstellt.
- **User übernimmt Fahrstunde**: Ein Nutzer, sei es ein Fahrlehrer oder ein Administrator mit entsprechenden Berechtigungen, nimmt die Fahrstunde an sich. Dieser Übergang führt zum Zustand `ASSIGNED`.
- **User markiert die Fahrstunde als erledigt**: Nach Durchführung der Fahrstunde markiert der Nutzer diese als `DONE`. Dies schließt den Lebenszyklus der Fahrstunde ab und zeigt an, dass keine weiteren Aktionen erforderlich sind.

---

## UI-Mockup

### Skizze des User Interfaces

#### Link: https://app.uizard.io/p/a8cf3f2c
