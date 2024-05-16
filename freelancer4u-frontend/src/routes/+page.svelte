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
    <h1>Welcome to DriveMatch!</h1>
    <p>This platform allows driving school staff to manage instructors and lessons. Instructors can assign lessons to themselves and manage their schedules effectively.</p>
    <img src="/images/vite.svg" alt="Vite Logo" />
  {:else}
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">Login</div>
            <div class="card-body">
              <form on:submit|preventDefault={loginWithUsernameAndPassword} bind:this={loginForm} class="needs-validation" novalidate>
                <div class="mb-3">
                  <label for="username" class="form-label">Email</label>
                  <input bind:value={username} type="text" class="form-control" id="username" name="username" required />
                  <div class="invalid-feedback">Please provide your username.</div>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input bind:value={password} type="password" class="form-control" id="password" name="password" required />
                  <div class="invalid-feedback">Please provide your password.</div>
                </div>
                <div class="row align-items-end">
                  <div class="col">
                    <button type="submit" class="btn btn-primary">Log in</button>
                  </div>
                  <div class="col-auto">
                    <a href="/signup">Sign up</a>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  {/if}
  