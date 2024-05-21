<script>
  import auth from "../auth.service";
  import { isAuthenticated } from "../store";

  let username = "";
  let password = "";
  let loginForm;

  function loginWithUsernameAndPassword() {
    if (loginForm.checkValidity()) {
      auth.login(username, password);
    }
    loginForm.classList.add("was-validated");
  }
</script>

{#if $isAuthenticated}
  <div class="container mt-5">
    <div class="card mb-4 bg-primary text-white">
      <div class="card-header">
        <h1>Welcome to DriveMatch!</h1>
      </div>
      <div class="card-body">
        <p>
          DriveMatch is a platform for the efficient management of driving
          lessons and driving instructors.
        </p>
      </div>
    </div>

    <div class="card mb-4 bg-primary text-white">
      <div class="card-header">
        <h2>Features</h2>
      </div>
      <div class="card-body">
        <h3 class="white-title underline">
          Common for Users (Instructors) and Admins (Driving School Staff)
        </h3>
        <ul>
          <li>
            <strong>Sign Up and Registration:</strong> User management (Sign up,
            Sign in) through Auth0.
          </li>
          <li><strong>Manage Lessons:</strong></li>
          <ul>
            <li>
              <strong>Assign and Complete:</strong> Users and Admins can assign driving
              lessons to themselves and mark them as completed after finishing.
            </li>
            <li>
              <strong>Overview:</strong> Users and Admins can see a list of all driving
              lessons with details, price, assigned user, and status.
            </li>
            <li>
              <strong>Filter by Type and Price:</strong> Users and Admins can filter
              driving lessons by type and their own maximum hourly rate.
            </li>
          </ul>
          <li>
            <strong>Account Page:</strong> Users and Admins can see their name, first
            name, email, and role.
          </li>
          <li>
            <strong>Weather Forecast:</strong> Users and Admins can see the current
            weather forecast and a 5-day forecast for Zurich.
          </li>
        </ul>

        <h3 class="white-title underline">
          Specific for Admins (Driving School Staff)
        </h3>
        <ul>
          <li>
            <strong>Create Lessons:</strong> Admins can create driving lessons with
            basic information and the hourly rate offered to the potential driving
            student.
          </li>
          <li>
            <strong>Detailed Descriptions:</strong> GPT API generates detailed descriptions
            based on the entered information.
          </li>
          <li>
            <strong>Instructor List:</strong> Admins can see a table of all registered
            instructors (users) with first name, last name, and email.
          </li>
        </ul>
      </div>
    </div>

    <div class="card mb-4 bg-primary text-white">
      <div class="card-header">
        <h2>Technical Architecture</h2>
      </div>
      <div class="card-body">
        <ul>
          <li><strong>Backend:</strong> Spring Boot Framework</li>
          <li><strong>Frontend:</strong> Svelte</li>
          <li><strong>Database:</strong> MongoDB on Atlas</li>
          <li><strong>Hosting:</strong> Azure App Service</li>
          <li><strong>Authentication:</strong> Auth0</li>
          <li>
            <strong>Third-Party Systems:</strong>
            <ul>
              <li><strong>Disify API:</strong> For email validation.</li>
              <li>
                <strong>GPT API:</strong> For generating detailed descriptions of
                driving lessons.
              </li>
              <li>
                <strong>OpenWeatherMap API:</strong> For providing weather forecast
                data.
              </li>
            </ul>
          </li>
          <li>
            <strong>Code Analysis:</strong> SonarCloud for code analysis and quality
            assurance.
          </li>
          <li>
            <strong>End-to-End Testing:</strong> Cypress for end-to-end testing.
          </li>
        </ul>
      </div>
    </div>
  </div>
{:else}
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">Login</div>
          <div class="card-body">
            <form
              on:submit|preventDefault={loginWithUsernameAndPassword}
              bind:this={loginForm}
              class="needs-validation"
              novalidate
            >
              <div class="mb-3">
                <label for="username" class="form-label">Email</label>
                <input
                  bind:value={username}
                  type="text"
                  class="form-control"
                  id="username"
                  name="username"
                  required
                />
                <div class="invalid-feedback">Please provide your email.</div>
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input
                  bind:value={password}
                  type="password"
                  class="form-control"
                  id="password"
                  name="password"
                  required
                />
                <div class="invalid-feedback">
                  Please provide your password.
                </div>
              </div>
              <div class="row align-items-end">
                <div class="col">
                  <button type="submit" class="btn btn-primary">Log in</button>
                </div>
                <div class="col-auto">
                  <a href="/signup" class="btn btn-link white-title">Sign up</a>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
{/if}
