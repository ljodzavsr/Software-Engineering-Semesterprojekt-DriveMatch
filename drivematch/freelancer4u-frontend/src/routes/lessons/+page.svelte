<script>
  import { page } from "$app/stores";
  import axios from "axios";
  import { onMount } from "svelte";
  import { jwt_token, user } from "../../store";

  const api_root = $page.url.origin;

  let lessons = [];
  let lesson = {
    description: null,
    price: null,
    lessonType: null,
  };

  onMount(() => {
    getLessons();
  });

  function getLessons() {
    var config = {
      method: "get",
      url: api_root + "/api/lesson",
      headers: { Authorization: "Bearer " + $jwt_token },
    };

    axios(config)
      .then(function (response) {
        lessons = response.data;
      })
      .catch(function (error) {
        alert("Could not get lessons");
        console.log(error);
      });
  }

  function createLesson() {
    var config = {
      method: "post",
      url: api_root + "/api/lesson",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + $jwt_token,
      },
      data: lesson,
    };

    axios(config)
      .then(function (response) {
        alert("Lesson created");
        getLessons();
      })
      .catch(function (error) {
        alert("Could not create Lesson");
        console.log(error);
      });
  }
</script>

{#if $user.user_roles && $user.user_roles.length > 0}
  <h1 class="mt-3">Create Lesson</h1>
  <form class="mb-5">
    <div class="row mb-3">
      <div class="col">
        <label class="form-label" for="description">Description</label>
        <input
          bind:value={lesson.description}
          class="form-control"
          id="description"
          type="text"
        />
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <label class="form-label" for="type">Type</label>
        <select bind:value={lesson.lessonType} class="form-select" id="type">
          <option value="OTHER">OTHER</option>
          <option value="TEST">TEST</option>
          <option value="IMPLEMENT">IMPLEMENT</option>
          <option value="REVIEW">REVIEW</option>
        </select>
      </div>
      <div class="col">
        <label class="form-label" for="price">Price</label>
        <input
          bind:value={lesson.price}
          class="form-control"
          id="price"
          type="number"
        />
      </div>
    </div>
    <button type="button" class="btn btn-primary" on:click={createLesson}
      >Submit</button
    >
  </form>
{/if}

<h1>All Lessons</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Description</th>
      <th scope="col">Type</th>
      <th scope="col">Price</th>
      <th scope="col">State</th>
      <th scope="col">InstructorId</th>
    </tr>
  </thead>
  <tbody>
    {#each lessons as lesson}
      <tr>
        <td>{lesson.description}</td>
        <td>{lesson.lessonType}</td>
        <td>{lesson.price}</td>
        <td>{lesson.lessonState}</td>
        <td>{lesson.instructorId}</td>
      </tr>
    {/each}
  </tbody>
</table>
