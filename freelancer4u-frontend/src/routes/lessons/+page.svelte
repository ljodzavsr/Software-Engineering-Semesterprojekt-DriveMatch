<script>
  import { page } from "$app/stores";
  import axios from "axios";
  import { jwt_token, myInstructorId, user } from "../../store";

  const api_root = $page.url.origin;

  let currentPage;
  let nrOfPages = 0;
  let defaultPageSize = 4;

  let priceMax;
  let lessonType;

  let lessons = [];
  let lesson = {
    description: null,
    detailDescription: null,
    price: null,
    lessonType: null,
    instructorId: null,
    instructor: null,
  };

  let instructor = null;

  // Teilaufgabe 2e)
  //let myInstructorId;

  $: {
    if ($jwt_token !== "") {
      let searchParams = $page.url.searchParams;

      if (searchParams.has("page")) {
        currentPage = searchParams.get("page");
      } else {
        currentPage = "1";
      }
      getLessons();
    }
  }

  /*  onMount(() => {
    // Teilaufgabe 2e)
    // Die Instructor ID könnte man auch im reactive statement holen,
    // dann wird der Endpoint aber unnötig oft aufgerufen.
    getMyInstuctorId();
  }); */

  function getLessons() {
    let query = "?pageSize=" + defaultPageSize + "&pageNumber=" + currentPage;

    if (priceMax) {
      query += "&max=" + priceMax;
    }

    if (lessonType && lessonType !== "ALL") {
      query += "&type=" + lessonType;
    }

    var config = {
      method: "get",
      url: api_root + "/api/lesson" + query,
      headers: { Authorization: "Bearer " + $jwt_token },
    };

    axios(config)
      .then(function (response) {
        getInstructorById(response.data.content)
        nrOfPages = response.data.totalPages;
      })
      .catch(function (error) {
        alert("Could not get lessons");
        console.log(error);
      });
  }

  function getInstructorById(lessonsForInstructors) {
  const instructorPromises = lessonsForInstructors.map((lesson) => {
    if (lesson.instructorId) {
      var config = {
        method: "get",
        url: `${api_root}/api/instructor/${lesson.instructorId}`,
        headers: { Authorization: "Bearer " + $jwt_token },
      };

      return axios(config).then((response) => {
        lesson.instructor = response.data.name + " " + response.data.lastname;
        return lesson;
      }).catch((error) => {
        console.log("Could not get instructors", error);
        return lesson;
      });
    } else {
      return Promise.resolve(lesson);
    }
  });

  Promise.all(instructorPromises).then((updatedLessons) => {
    lessons = updatedLessons;
    console.log(lessons);
  }).catch((error) => {
    console.log("Error updating lessons with instructors", error);
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

  function assignToMe(lessonId) {
    var config = {
      method: "put",
      url: api_root + "/api/service/me/assignlesson?lessonId=" + lessonId,
      headers: { Authorization: "Bearer " + $jwt_token },
    };
    axios(config)
      .then(function (response) {
        getLessons();
      })
      .catch(function (error) {
        alert("Could not assign lesson to me");
        console.log(error);
      });
  }

  function completeMyLesson(lessonId) {
    var config = {
      method: "put",
      url: api_root + "/api/service/me/completelesson?lessonId=" + lessonId,
      headers: { Authorization: "Bearer " + $jwt_token },
    };

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
        getLessons();
      })
      .catch(function (error) {
        alert("Could not mark as completed");
        console.log(error);
      });
  }

  // Teilaufgabe 2e)
  /*  function getMyInstructorId() {
    var config = {
      method: "get",
      url: api_root + "/api/me/instructor",
      headers: { Authorization: "Bearer " + $jwt_token },
    };

    axios(config)
      .then(function (response) {
        myInstructorId = response.data.id;
        console.log(myInstructorId);
      })
      .catch(function (error) {
        alert("Could not get Instructor associated to current user");
        console.log(error);
      });
  }  */
</script>

{#if $user.user_roles && $user.user_roles.length > 0}
  <h1 class="mt-3">Create Lesson</h1>
  <form class="mb-5">
    <div class="row mb-3">
      <div class="col">
        <label class="form-label" for="description">Enter key details for the driving lesson. GPT will generate a detailed description.</label>
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
          <option value="FUNDAMENTALS">FUNDAMENTALS</option>
          <option value="URBAN">URBAN</option>
          <option value="HIGHWAY">HIGHWAY</option>
          <option value="PARKING">PARKING</option>
        </select>
      </div>
      <div class="col">
        <label class="form-label" for="price"
          >Hourly rate offered to the customer</label
        >
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
<div class="row my-3">
  <div class="col-auto">
    <label for="" class="col-form-label">Hourly rate instructor: </label>
  </div>
  <div class="col-3">
    <input
      id="pricefilter"
      class="form-control"
      type="number"
      placeholder="max"
      bind:value={priceMax}
    />
  </div>
  <div class="col-auto">
    <label for="" class="col-form-label">Lesson Type: </label>
  </div>
  <div class="col-3">
    <select bind:value={lessonType} class="form-select" id="type" type="text">
      <option value="ALL" />
      <option value="FUNDAMENTALS">FUNDAMENTALS</option>
      <option value="URBAN">URBAN</option>
      <option value="HIGHWAY">HIGHWAY</option>
      <option value="PARKING">PARKING</option>
    </select>
  </div>

  <div class="col-3">
    <a
      class="btn btn-primary"
      href={"/lessons?page=1&lessonType=" +
        lessonType +
        "&priceMax=" +
        priceMax}
      role="button">Apply</a
    >
  </div>
</div>
<table class="table">
  <thead>
    <tr>
      <!-- <th scope="col">Description</th> -->

      <th scope="col">Description Detail</th>
      <th scope="col">Type</th>
      <th scope="col">Offered Hourly Rate</th>
      <th scope="col">State</th>
      <th scope="col">InstructorId</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    {#each lessons as lesson}
      <tr>
        <!-- <td>{lesson.description}</td> -->

        <td>{lesson.detailDescription}</td>
        <td>{lesson.lessonType}</td>
        <td>{lesson.price}</td>
        <td>{lesson.lessonState}</td>
        <td>{lesson.instructor}</td>
        <td>
          {#if lesson.lessonState === "ASSIGNED"}
            <span class="badge bg-secondary">Assigned</span>
          {:else if lesson.instructorId === null}
            <button
              type="button"
              class="btn btn-primary btn-sm"
              on:click={() => {
                assignToMe(lesson.id);
              }}
            >
              Assign to me
            </button>
          {/if}
          {#if lesson.lessonState === "DONE"}
            <span class="badge bg-secondary">Done</span>
          {:else if lesson.lessonState === "ASSIGNED" && lesson.instructorId === $myInstructorId}
            <button
              type="button"
              class="btn btn-primary btn-sm"
              on:click={() => {
                completeMyLesson(lesson.id);
              }}
            >
              Complete Lesson
            </button>
          {/if}
        </td>
      </tr>
    {/each}
  </tbody>
</table>
<nav>
  <ul class="pagination">
    {#each Array(nrOfPages) as _, i}
      <li class="page-item">
        <a
          class="page-link"
          class:active={currentPage == i + 1}
          href={"/lessons?page=" + (i + 1)}
          >{i + 1}
        </a>
      </li>
    {/each}
  </ul>
</nav>
