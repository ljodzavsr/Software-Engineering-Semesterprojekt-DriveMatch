describe("Manage lessons as admin", () => {
    before(() => {
        cy.clearAllSessionStorage();
        cy.visit("http://localhost:8080");
        cy.get("#username").type(Cypress.env()["admin"].email);
        cy.get("#password").type(Cypress.env()["admin"].password);
        cy.contains("button", "Log in").click();
        cy.get("h1").should("contain", "Welcome");
        cy.request({
            method: "DELETE",
            url: "http://localhost:8080/api/lesson",
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("jwt_token"),
            },
        });
    });
    it("visit lessons page", () => {
        cy.get('a[href="/lessons"]').click();
        cy.location("pathname").should("equal", "/lessons");
    });
})