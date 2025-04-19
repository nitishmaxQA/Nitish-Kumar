package apitest;

import apiutils.RestHelper;
import config.Configreader;
import customreporter.CustomReporter;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

@Listeners({CustomReporter.class})

public class ApiTest extends BaseApiTest {
    Response response;
    JsonPath jsonResponse;

    /**
     * Test Case 01: Create a new Pet using POST request
     */
    @Test
    public void TC_01CreatePet() {
        response = RestHelper.post(Configreader.postPutEndpoint(), JsonUtil.readJsonFileAsString("CreatePet.json"));
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        // Validate response body fields
        jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.getInt("id"), 10);
        Assert.assertEquals(jsonResponse.getString("name"), "doggie");
        Assert.assertEquals(jsonResponse.getInt("category.id"), 1);
        Assert.assertEquals(jsonResponse.getString("category.name"), "Dogs");
        Assert.assertEquals(jsonResponse.getList("photoUrls").get(0), "string");
        Assert.assertEquals(jsonResponse.getInt("tags[0].id"), 0);
        Assert.assertEquals(jsonResponse.getString("tags[0].name"), "string");
        Assert.assertEquals(jsonResponse.getString("status"), "available");
    }

    /**
     * Test Case 02: Get the created Pet using GET request and validate
     */
    @Test
    public void TC_02testGetPet() {
        RestHelper.gotoSleep(2000); // Wait 2 second before checking
        response = RestHelper.get(Configreader.getDeleteEndpoint());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        // Validate response body fields
        jsonResponse = response.jsonPath();
        softAssert.assertEquals(jsonResponse.getInt("id"), 10);
        softAssert.assertEquals(jsonResponse.getString("name"), "doggie");
        softAssert.assertEquals(jsonResponse.getInt("category.id"), 1);
        softAssert.assertEquals(jsonResponse.getString("category.name"), "Dogs");
        softAssert.assertEquals(jsonResponse.getList("photoUrls").get(0), "string");
        softAssert.assertEquals(jsonResponse.getInt("tags[0].id"), 0);
        softAssert.assertEquals(jsonResponse.getString("tags[0].name"), "string");
        softAssert.assertEquals(jsonResponse.getString("status"), "available");
        softAssert.assertAll();
    }

    /**
     * Test Case 03: Update the existing Pet using PUT request
     * update name , photo url,status.
     */
    @Test
    public void TC_03UpdatePet() {
        RestHelper.gotoSleep(2000);
        response = RestHelper.put(Configreader.postPutEndpoint(), JsonUtil.readJsonFileAsString("PutPet.json"));
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        // Validate updated response body fields
        jsonResponse = response.jsonPath();
        softAssert.assertEquals(jsonResponse.getInt("id"), 10, "ID mismatch");
        softAssert.assertEquals(jsonResponse.getString("name"), "bogo", "Name mismatch");
        softAssert.assertEquals(jsonResponse.getInt("category.id"), 1, "Category ID mismatch");
        softAssert.assertEquals(jsonResponse.getString("category.name"), "Dogs", "Category Name mismatch");
        softAssert.assertEquals(jsonResponse.getList("photoUrls").get(0), "new photo url", "Photo URL mismatch");
        softAssert.assertEquals(jsonResponse.getInt("tags[0].id"), 0, "Tag ID mismatch");
        softAssert.assertEquals(jsonResponse.getString("tags[0].name"), "new name", "Tag Name mismatch");
        softAssert.assertEquals(jsonResponse.getString("status"), "Not available", "Status mismatch");
        softAssert.assertAll();
    }

    /**
     * Test Case 04: Get the updated Pet using GET request
     * validating entire body after updating
     */
    @Test
    public void TC_04GetPetAfterUpdate() {
        RestHelper.gotoSleep(2000);
        response = RestHelper.get(Configreader.getDeleteEndpoint());
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200);
        // Validate updated fields after PUT
        jsonResponse = response.jsonPath();
        softAssert.assertEquals(jsonResponse.getInt("id"), 10, "ID mismatch");
        softAssert.assertEquals(jsonResponse.getString("name"), "bogo", "Name mismatch");
        softAssert.assertEquals(jsonResponse.getInt("category.id"), 1, "Category ID mismatch");
        softAssert.assertEquals(jsonResponse.getString("category.name"), "Dogs", "Category Name mismatch");
        softAssert.assertEquals(jsonResponse.getList("photoUrls").get(0), "new photo url", "Photo URL mismatch");
        softAssert.assertEquals(jsonResponse.getInt("tags[0].id"), 0, "Tag ID mismatch");
        softAssert.assertEquals(jsonResponse.getString("tags[0].name"), "new name", "Tag Name mismatch");
        softAssert.assertEquals(jsonResponse.getString("status"), "Not available", "Status mismatch");
        softAssert.assertAll();
    }

    /**
     * Test Case 05: Delete the Pet using DELETE request
     */
    @Test
    public void TC_05DeletePet() {
        RestHelper.gotoSleep(2000);
        response = RestHelper.delete(Configreader.getDeleteEndpoint());
        // Validate successful deletion
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().asString().trim(), "Pet deleted");
    }

    /**
     * Test Case 06: Verify Pet is deleted by sending GET request
     * validating response body "Pet not found"
     */
    @Test
    public void TC_06GetPetAfterDelete() {
        RestHelper.gotoSleep(2000);
        response = RestHelper.get(Configreader.getDeleteEndpoint());
        // Validate Pet is not found after deletion
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.getBody().asString().trim(), "Pet not found");
    }
}