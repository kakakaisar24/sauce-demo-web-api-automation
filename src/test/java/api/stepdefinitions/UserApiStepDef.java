package api.stepdefinitions;

import api.clients.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class UserApiStepDef {

    private final UserApi userApi = new UserApi();

    private Response response;
    private String userId;
    private String firstName;

    @Given("I have an existing user ID")
    public void iHaveAnExistingUserId() {
        response = userApi.getUsers();

        assertEquals(200, response.getStatusCode());

        userId = response
                .jsonPath()
                .getString("data[0].id");

        assertNotNull(userId);
        assertFalse(userId.isBlank());
    }

    @Given("I have a non-existing user ID")
    public void iHaveANonExistingUserId() {
        userId = "000000000000000000000000";
    }

    @When("I request the user by ID")
    public void iRequestTheUserById() {
        response = userApi.getUserById(userId);
    }

    @Then("the API response status should be 200")
    public void theApiResponseStatusShouldBe200() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the API response status should be 404")
    public void theApiResponseStatusShouldBe404() {
        assertEquals(404, response.getStatusCode());
    }

    @Then("the response should contain the user ID")
    public void theResponseShouldContainTheUserId() {
        String responseUserId =
                response.jsonPath().getString("id");

        assertEquals(userId, responseUserId);
    }

    @Given("I prepare a new user")
    public void iPrepareANewUser() {
        firstName = "Automation";
    }

    @When("I create the user")
    public void iCreateTheUser() {
        String lastName = "Student";

        String email =
                "automation" + System.currentTimeMillis() + "@example.com";

        response = userApi.createUser(
                firstName,
                lastName,
                email
        );

        userId = response
                .jsonPath()
                .getString("id");
    }

    @Then("the response should contain the created user")
    public void theResponseShouldContainTheCreatedUser() {
        assertNotNull(userId);
        assertFalse(userId.isBlank());

        String responseFirstName =
                response.jsonPath().getString("firstName");

        assertEquals(firstName, responseFirstName);
    }

    @When("I update the created user")
    public void iUpdateTheCreatedUser() {
        firstName = "Updated";

        response = userApi.updateUser(
                userId,
                firstName
        );
    }

    @Then("the response should contain the updated first name")
    public void theResponseShouldContainTheUpdatedFirstName() {
        String responseFirstName =
                response.jsonPath().getString("firstName");

        assertEquals(firstName, responseFirstName);
    }

    @When("I delete the created user")
    public void iDeleteTheCreatedUser() {
        response = userApi.deleteUser(userId);
    }

    @When("I request the list of tags")
    public void iRequestTheListOfTags() {
        response = userApi.getTags();
    }

    @Then("the response should contain a list of tags")
    public void theResponseShouldContainAListOfTags() {
        Object data = response.jsonPath().get("data");

        assertNotNull(data);

        assertFalse(
                response.jsonPath()
                        .getList("data")
                        .isEmpty()
        );
    }
}