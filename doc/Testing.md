# Testing

## Modul- und Integrationstests

Modul- und Integrationstests wurden erstellt, um die Funktionalität der einzelnen Komponenten und deren Zusammenspiel zu überprüfen. Diese Tests stellen sicher, dass die verschiedenen Module korrekt integriert und funktional sind.

### Implementierung

- **LessonControllerTest.java**: Testet die Funktionalität des Lesson Controllers, einschliesslich der Erstellung und Verwaltung von Fahrstunden.
- **FiveBucksVoucherTest.java**: Überprüft die Rabattlogik für den FiveBucksVoucher.
- **PercentageVoucherTest.java**: Überprüft die Rabattlogik für den PercentageVoucher.
- **TwoForOneVoucherTest.java**: Überprüft die Rabattlogik für den TwoForOneVoucher.

**Hinweis**: Die Voucher-Funktionen sind derzeit nur im Backend implementiert und getestet. Sie wurden im Frontend noch nicht integriert.

## End-to-End Tests

End-to-End Tests wurden mit Cypress durchgeführt, um die gesamte Benutzererfahrung zu überprüfen. Diese Tests simulieren Benutzeraktionen und stellen sicher, dass die Plattform wie erwartet funktioniert.

### Implementierung

- **lessons.admin.cy.ts**: Testet die Verwaltung der Fahrstunden durch einen Administrator, einschliesslich Erstellung, Zuweisung und Filterung von Fahrstunden.
- **lessons.user.cy.ts**: Testet die Verwaltung der Fahrstunden durch einen normalen Benutzer, einschliesslich der Anzeige und Filterung von Fahrstunden.

## Erkenntnisse aus der Analyse mit SonarQube

### Analysebericht

Die Analyse mit SonarQube zeigte insgesamt eine gute Codequalität. Es wurden jedoch einige Probleme identifiziert:

- **Sicherheit**: 1 offenes Sicherheitsproblem mit hoher Priorität, das behoben werden muss.
- **Zuverlässigkeit**: 13 offene Probleme, hauptsächlich von mittlerer Priorität, die sich auf die Zuverlässigkeit der Anwendung auswirken könnten.
- **Wartbarkeit**: 57 offene Probleme, die die Wartbarkeit des Codes beeinträchtigen, darunter auch 2 mit hoher Priorität.

Link zum öffentlichen SonarQube: https://sonarcloud.io/project/overview?id=zhaw-semesterprojekt_ch-zhaw-ljodzvis-drivematch
