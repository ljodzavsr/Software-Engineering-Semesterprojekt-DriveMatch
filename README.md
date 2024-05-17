# DriveMatch

DriveMatch is a platform for the efficient management of driving lessons and driving instructors.

## Features

### Common for Users (Instructors) and Admins (Driving School Staff)

- **Sign Up and Registration:** User management (Sign up, Sign in) through Auth0.
- **Manage Lessons:**
  - **Assign and Complete:** Users and Admins can assign driving lessons to themselves and mark them as completed after finishing.
  - **Overview:** Users and Admins can see a list of all driving lessons with details, price, assigned user, and status.
  - **Filter by Type and Price:** Users and Admins can filter driving lessons by type and their own maximum hourly rate.
- **Account Page:** Users and Admins can see their name, first name, email, and role.

### Specific for Admins (Driving School Staff)

- **Create Lessons:** Admins can create driving lessons with basic information and the hourly rate offered to the potential driving student.
- **Detailed Descriptions:** GPT API generates detailed descriptions based on the entered information.
- **Instructor List:** Admins can see a table of all registered instructors (users) with first name, last name, and email.

## Technical Architecture

- **Backend:** Spring Boot Framework
- **Frontend:** Svelte
- **Database:** MongoDB on Atlas
- **Hosting:** Azure App Service
- **Authentication:** Auth0
- **Third-Party Systems:**
  - **Disify API:** For email validation.
  - **GPT API:** For generating detailed descriptions of driving lessons.
- **Code Analysis:** SonarCloud for code analysis and quality assurance.
- **End-to-End Testing:** Cypress for end-to-end testing.
