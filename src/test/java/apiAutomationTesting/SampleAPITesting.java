package apiAutomationTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SampleAPITesting
{

    String jsonBody= """
            {
                "name": "firstNames2345212",
                "gender": "male",
                "email": "firstNames1234442@15ce.com",
                "status": "active"
            }
            """;

    int userID=0;
    @Test(description = "Performing Post Operations",priority = 1)
    public void performPOSTOperations()
    {
        RestAssured.baseURI="https://gorest.co.in";

        //BDD Approach:

        //1. given() --> Set of activities that needs to be performed before the API Request is made
        //2. when() ---> Set of activities that needs to be performed during the API Request
        //3. then() ---> Set of activities that needs to be performed after the API Request is completed

        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 9ee31d62ba2aa3329d51d054896e96ad9f132437c190f2a42145dfb659f8c431")
                .body(jsonBody)
        .when()
                .post("public/v2/users")
        .then()
                .statusCode(201).extract().response();


        System.out.println(response.getBody().asPrettyString());

        userID=response.getBody().jsonPath().getInt("id");
    }

    @Test(description = "Performing GET Operations",priority = 2)
    public void performGETOperations()
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 9ee31d62ba2aa3329d51d054896e96ad9f132437c190f2a42145dfb659f8c431")
                .pathParam("UserID",userID)
                .when()
                .get("public/v2/users/{UserID}")
                .then()
                .statusCode(200).extract().response();

        System.out.println(response.getBody().asPrettyString());
    }

    @Test(description = "Performing DELETE Operations",priority = 3)
    public void performDeleteOperations()
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 9ee31d62ba2aa3329d51d054896e96ad9f132437c190f2a42145dfb659f8c431")
                .pathParam("UserID",userID)
                .when()
                .delete("public/v2/users/{UserID}")
                .then()
                .statusCode(204).extract().response();

        System.out.println(response.getBody().asPrettyString());
    }
}
