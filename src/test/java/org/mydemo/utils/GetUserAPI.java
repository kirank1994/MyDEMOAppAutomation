package org.mydemo.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserAPI {

    public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://reqres.in";

        // API call
        Response response = RestAssured
            .given()
                .header("x-api-key", "reqres-free-v1")
            .when()
                .get("/api/users/2")
            .then()
                .statusCode(200)
                .extract().response();

        // Print response
        System.out.println("✅ Status Code: " + response.getStatusCode());
        System.out.println("✅ Response Body:\n" + response.asPrettyString());

        // Extract specific fields
        String email = response.path("data.email");
        String firstName = response.path("data.first_name");
        String lastName = response.path("data.last_name");

        System.out.println("\n📌 Extracted User Info:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name : " + lastName);
        System.out.println("Email     : " + email);
    }
}
