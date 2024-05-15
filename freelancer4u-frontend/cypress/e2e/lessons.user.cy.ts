describe("Manage lessons as user", () => {
    before(() => {
        cy.clearAllSessionStorage();
        cy.visit("http://localhost:8080");

        cy.get("#username").type(Cypress.env()["user"].email);
        cy.get("#password").type(Cypress.env()["user"].password);

        cy.contains("button", "Log in").click();

        cy.get("h1").should("contain", "Welcome");

    });

    it("visit lessons page", () => {
        cy.get('a[href="/lessons"]').click();
        cy.location("pathname").should("include", "lessons");
    });

    it("lessons form is not displayed", () => {
        cy.get("#description").should("not.exist");
        cy.get("#type").should("not.exist");
        cy.get("#price").should("not.exist");
        cy.contains("Submit").should("not.exist");
    });

    it("lesson is assigned to me", () => {
        cy.get("tbody>tr:nth-child(2) button").click();
        cy.get("tbody>tr:nth-child(2) .badge").should("exist");
    });

    it("filter by type", () => {
        cy.get("#typefilter").select("FUNDAMENTALS");
        cy.contains("Apply").click();
        cy.get("tbody>tr").should("have.length", 2);
    });

    it("filter by price", () => {
        cy.get("#pricefilter").type("110");
        cy.contains("Apply").click();
        cy.get("tbody>tr").should("have.length", 1);
    });
});
