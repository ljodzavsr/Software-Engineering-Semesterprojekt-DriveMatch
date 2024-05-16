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
      <p>DriveMatch ist eine Plattform zur effizienten Verwaltung von Fahrstunden und Fahrlehrern.</p>
    </div>
  </div>
  
  <div class="card mb-4 bg-primary text-white">
    <div class="card-header">
      <h2>Funktionen</h2>
    </div>
    <div class="card-body">
      <h3 class="white-title">Gemeinsam für User (Fahrlehrer) und Admin (Mitarbeiter der Fahrschule)</h3>
      <ul>
        <li><strong>Anmeldung und Registrierung:</strong> Usermanagement (Sign up, Sign in) durch Auth0.</li>
        <li><strong>Fahrstunden verwalten:</strong></li>
        <ul>
          <li><strong>Zuweisen und Abschließen:</strong> User und Admin können sich Fahrstunden zuweisen und nach Abschluss als erledigt markieren.</li>
          <li><strong>Übersicht:</strong> User und Admin sehen eine Liste aller Fahrstunden mit Details, Preis, zugewiesenem Nutzer und Status.</li>
          <li><strong>Filtern nach Typ und Preis:</strong> User und Admin können Fahrstunden nach Typ und ihrem eigenen maximalen Stundensatz filtern.</li>
        </ul>
        <li><strong>Account Page:</strong> User und Admin sehen Name, Vorname, E-Mail und Rolle.</li>
      </ul>
      
      <h3 class="white-title">Spezifisch für Admin (Mitarbeiter der Fahrschule)</h3>
      <ul>
        <li><strong>Erstellen von Fahrstunden:</strong> Admins erstellen Fahrstunden mit grundlegenden Infos und dem individuell angebotenen Preis an den potenziellen Fahrschüler pro Stunde.</li>
        <li><strong>Detailbeschreibungen:</strong> GPT-API generiert detaillierte Beschreibungen aus den eingegebenen Informationen.</li>
        <li><strong>Fahrlehrerliste:</strong> Admins sehen eine Tabelle aller registrierten Fahrlehrer (User) mit Vorname, Name und E-Mail.</li>
        <li><strong>Fahrschülerverwaltung:</strong> Admins sehen eine Liste aller registrierten Fahrschüler.</li>
      </ul>
    </div>
  </div>
  
  <div class="card mb-4 bg-primary text-white">
    <div class="card-header">
      <h2>Technische Architektur</h2>
    </div>
    <div class="card-body">
      <ul>
        <li><strong>Backend:</strong> Spring Boot Framework</li>
        <li><strong>Frontend:</strong> Svelte</li>
        <li><strong>Datenbank:</strong> MongoDB auf Atlas</li>
        <li><strong>Hosting:</strong> Azure App Service</li>
        <li><strong>Authentifizierung:</strong> Auth0</li>
        <li><strong>Drittsysteme:</strong>
          <ul>
            <li><strong>Disify API:</strong> Zur Validierung von E-Mail-Adressen.</li>
            <li><strong>GPT API:</strong> Zur Generierung detaillierter Fahrstundenbeschreibungen.</li>
          </ul>
        </li>
        <li><strong>Code-Analyse:</strong> SonarCloud zur Code-Analyse und Qualitätssicherung.</li>
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
