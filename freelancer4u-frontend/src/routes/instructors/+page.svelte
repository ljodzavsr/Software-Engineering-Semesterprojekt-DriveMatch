<script>
  import { page } from "$app/stores";
  import axios from "axios";
  import { jwt_token } from "../../store";

  const api_root = $page.url.origin;

  let currentPage;
  let nrOfPages = 0;
  let defaultPageSize = 4;

  let instructors = [];
  let instructor = {
    id: null,
    email: null,
    name: null,
    lastname: null,
  };

  $: {
    if ($jwt_token !== "") {
      let searchParams = $page.url.searchParams;

      if (searchParams.has("page")) {
        currentPage = searchParams.get("page");
      } else {
        currentPage = "1";
      }
      getInstructors();
    }
  }

  function getInstructors() {
    let query = "?pageSize=" + defaultPageSize + "&pageNumber=" + currentPage;

    var config = {
      method: "get",
      url: api_root + "/api/instructor" + query,
      headers: { Authorization: "Bearer " + $jwt_token },
    };

    axios(config)
      .then(function (response) {
        instructors = response.data.content;
        nrOfPages = response.data.totalPages;
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
        Authorization: "Bearer " + $jwt_token,
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

  function validateEmailAndcreateInstructor() {
    var config = {
      method: "get",
      url: "https://disify.com/api/email/" + instructor.email,
    };

    axios(config)
      .then(function (response) {
        console.log("Validated email " + instructor.email);
        console.log(response.data);
        if (
          response.data.format &&
          !response.data.disposable &&
          response.data.dns
        ) {
          createInstructor();
        } else {
          alert("Email " + instructor.email + " is not valid.");
        }
      })
      .catch(function (error) {
        alert("Could not validate email");
        console.log(error);
      });
  }
</script>

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
        <td>{instructor.name} {instructor.lastname}</td>
        <td>{instructor.email}</td>
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
          href={"/instructors?page=" + (i + 1)}
          >{i + 1}
        </a>
      </li>
    {/each}
  </ul>
</nav>
