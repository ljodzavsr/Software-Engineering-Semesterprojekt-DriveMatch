<script>
  import { page } from "$app/stores";
  import axios from "axios";
  import { onMount } from "svelte";
  import { jwt_token } from "../../store";

  const api_root = $page.url.origin; 

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
      headers: {Authorization: "Bearer "+$jwt_token},
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
        Authorization: "Bearer "+$jwt_token,
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
