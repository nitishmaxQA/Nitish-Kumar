package apiutils;

import config.Configreader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class RestHelper {
    // Base URL fetched from ConfigReader
    private static final String BASE_URL = Configreader.getBaseUrl();

    /**
     * POST request method
     *
     * @param endpoint API endpoint to hit
     * @param body     JSON request body
     * @return Response object
     */
    public static Response post(String endpoint, String body) {
        // Send POST request with JSON body, log request and response

        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()  // Log request details
                .when()
                .post(endpoint)
                .then()
                .log().all()  // Log response details
                .extract()
                .response();
    }

    /**
     * GET request method
     *
     * @param endpoint API endpoint to hit
     * @return Response object
     */
    public static Response get(String endpoint) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * PUT request method
     *
     * @param endpoint API endpoint to hit
     * @param body     JSON request body (updated data)
     * @return Response object
     */
    public static Response put(String endpoint, Object body) {
        // Send PUT request with JSON body
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * DELETE request method
     *
     * @param endpoint API endpoint to hit
     * @return Response object
     */
    public static Response delete(String endpoint) {
        // Send DELETE request
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    // Pause the execution for specified seconds
    public static void gotoSleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
