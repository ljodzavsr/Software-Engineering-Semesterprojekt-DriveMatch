describe("Manage lessons as admin", () => {
    before(() => {
        cy.clearAllSessionStorage(); // Clear all session data

        cy.visit("http://localhost:8080"); // Visit the login page

        // Log in as admin
        cy.get("#username").type(Cypress.env("admin").email);
        cy.get("#password").type(Cypress.env("admin").password);

        cy.contains("button", "Log in").click();

        // Check if the welcome message is displayed
        cy.get("h1").should("contain", "Welcome");

        // Wait until the token is set in sessionStorage
        cy.window().then((win) => {
            const token = win.sessionStorage.getItem("jwt_token");
            cy.log("JWT Token:", token); // Log the token for verification
            if (token) {
                cy.request({
                    method: "DELETE",
                    url: "http://localhost:8080/api/lesson", // Corrected URL
                    headers: {
                        Authorization: "Bearer " + token,
                    },
                    failOnStatusCode: false // Prevents aborting on 403 or 405 errors
                }).then((response) => {
                    if (response.status === 403) {
                        cy.log("Authorization failed. Status code: 403");
                    } else if (response.status === 405) {
                        cy.log("Method Not Allowed. Status code: 405");
                    } else {
                        cy.log("Lessons deleted successfully");
                    }
                });
            } else {
                cy.log("No JWT token found in sessionStorage");
            }
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

        // create fifth lesson
        cy.get("#description").clear().type("Fifth Lesson");
        cy.get("#type").select("HIGHWAY");
        cy.get("#price").clear().type("90");
        cy.contains("Submit").click();

        // table (first page) should now have 4 elements
        cy.get("tbody>tr").should("have.length", 4);

        // go to second page
        cy.contains(".page-link", "2").click();

        // second page should have one element
        cy.get("tbody>tr").should("have.length", 1);
    });

    it("lesson is assigned to me", () => {
        cy.get("tbody>tr:first-child button").click();
        cy.get("tbody>tr:first-child .badge").should("exist");
    });
});
