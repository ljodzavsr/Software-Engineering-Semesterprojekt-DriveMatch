describe("Manage lessons as user", () => {
    before(() => {
        cy.clearAllSessionStorage(); // Clear all session data

        cy.visit("http://localhost:8080"); // Visit the login page

        // Log in as user
        cy.get("#username").type(Cypress.env()["user"].email);
        cy.get("#password").type(Cypress.env()["user"].password);

        cy.contains("button", "Log in").click(); // Click the login button

        // Check if the welcome message is displayed
        cy.get("h1").should("contain", "Welcome");
    });

    it("visit lessons page", () => {
        cy.get('a[href="/lessons"]').click(); // Navigate to the lessons page
        cy.location("pathname").should("include", "lessons"); // Verify the URL contains "lessons"
    });

    it("lessons form is not displayed", () => {
        // Check that the lesson creation form is not displayed for a user
        cy.get("#description").should("not.exist"); // Verify the description field does not exist
        cy.get("#price").should("not.exist"); // Verify the price field does not exist
        cy.contains("Submit").should("not.exist"); // Verify the submit button does not exist
    });
})
