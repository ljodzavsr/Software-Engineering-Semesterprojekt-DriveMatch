<script>
  import axios from "axios";
  import { onMount } from "svelte";

  const api_root = "https://51de3d88-ae18-4d82-a1b8-89d1f1a81403.mock.pstmn.io";

  let instructors = [];
  let instructor = {
    id: null,
    email: null,
    name: null,
  };

  onMount(() => {
    getInstructors();
  });

  function getInstructors() {
    var config = {
      method: "get",
      url: api_root + "/api/instructor",
      headers: {},
    };

    axios(config)
      .then(function (response) {
        instructors = response.data;
      })
      .catch(function (error) {
        alert("Could not get instructors");
        console.log(error);
      });
  }

  function createInstructor() {
    var config = {
      method: "post",
      url: api_root + "/api/instructor",
      headers: {
        "Content-Type": "application/json",
      },
      data: instructor,
    };

    axios(config)
      .then(function (response) {
        alert("Instructor created");
        getInstructors();
      })
      .catch(function (error) {
        alert("Could not create Instructor");
        console.log(error);
      });
  }
</script>

<h1 class="mt-3">Create Instructor</h1>
<form class="mb-5">
  <div class="row mb-3">
    <div class="col">
      <label class="form-label" for="description">Description</label>
      <input
        bind:value={instructor.name}
        class="form-control"
        id="name"
        type="text"
      />
    </div>
  </div>
  <div class="row mb-3">
    <div class="col">
      <label class="form-label" for="email">E-Mail</label>
      <input
        bind:value={instructor.email}
        class="form-control"
        id="price"
        type="email"
      />
    </div>
  </div>
  <button type="button" class="btn btn-primary" on:click={createInstructor}
    >Submit</button
  >
</form>

<h1>All Instructors</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">E-Mail</th>
    </tr>
  </thead>
  <tbody>
    {#each instructors as instructor}
      <tr>
        <td>{instructor.name}</td>
        <td>{instructor.email}</td>
      </tr>
    {/each}
  </tbody>
</table>
