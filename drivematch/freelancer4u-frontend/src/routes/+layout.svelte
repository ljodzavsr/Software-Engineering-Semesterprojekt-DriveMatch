<script>
  import "./styles.css";
</script>

<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">DriveMatch</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon" />
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="/instructors"
            >Instructors</a
          >
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/lessons">Lessons</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-3">
  <slot />
</div>
