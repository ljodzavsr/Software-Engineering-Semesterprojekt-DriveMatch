import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
    },
    testIsolation: false,

    env: {
      user: {
        email: "info@analysit.ch",
        password: "haha123!",
      },
      admin: {
        email: "visar.ljodza@icloud.com",
        password: "haha123!",
      },
    },
  },
});
