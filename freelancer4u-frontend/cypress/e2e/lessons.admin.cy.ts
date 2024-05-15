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

        cy.location("pathname").should("include", "lessons");
    });

    it("lessons are created", () => {
        // create first lesson
        cy.get("#description").clear().type("First Lesson");
        cy.get("#type").select("HIGHWAY");
        cy.get("#price").clear().type("150");
        cy.contains("Submit").click();

        // create second lesson
        cy.get("#description").clear().type("Second Lesson");
        cy.get("#type").select("HIGHWAY");
        cy.get("#price").clear().type("100");
        cy.contains("Submit").click();

        // create third lesson
        cy.get("#description").clear().type("Third Lesson");
        cy.get("#type").select("HIGHWAY");
        cy.get("#price").clear().type("140");
        cy.contains("Submit").click();

        // table should now have 3 elements
        cy.get("tbody>tr").should("have.length", 3);
    });

    it("second page exists and has one row", () => {
        // create fourth lesson
        cy.get("#description").clear().type("Fourth Lesson");
        cy.get("#type").select("HIGHWAY");
        cy.get("#price").clear().type("110");
        cy.contains("Submit").click();

        // create fifth Lesson
        cy.get("#description").clear().type("Fifth Lesson");
        cy.get("#type").select("HIGHWAY");
        cy.get("#price").clear().type("90");
        cy.contains("Submit").click();

        // table (first page) should now have 4 elements
        cy.get("tbody>tr").should("have.length", 4);

        // go to second page
        cy.contains(".page-link", "2").click();

        // second page should have one element and its content should be 'test everything'
        cy.get("tbody>tr").should("have.length", 1);
        cy.get("tr>td:first-child").should("contain", "test everything");
    });

    it("lesson is assigned to me", () => {
        cy.get("tbody>tr:first-child button").click();
        cy.get("tbody>tr:first-child .badge").should("exist");
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
