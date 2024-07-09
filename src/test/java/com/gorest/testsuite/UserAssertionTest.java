package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){

        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .queryParam("page","1")
                .queryParam("per-page","20")
                .when()
                .get("/users?page=1&per_page=20")
                .then()
                .statusCode(200);
    }
    //1. Verify if the total record is 20
    @Test
    public void testNo01() {
        response.body("size()", equalTo(20));
    }

    //2. Verify  if the name of id = 7015089 is equal to ”Dev Tagore”
    @Test
    public void testNo02() {
        response.body("[0].name", equalTo("Vidya Dutta"));
    }

    //3. Check the single ‘Name’ in the Array list (Somu Arora)
    @Test
    public void testNo03() {
        response.body("[1].name", equalTo("Jaya Gandhi DVM"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dev Tagore, Somu Arora, Oormila Shah )
    @Test
    public void testNo04() { response.body("name", hasItems("Vidya Dutta", "Jaya Gandhi DVM", "Chakravartee Varma"));}

    //5. Verify the email of userid =  7015089 is equal “dev_tagore@kessler-lebsack.test”
    @Test
    public void testNo05() {
        response.body("[0].email", equalTo("vidya_dutta@hyatt.test"));
    }

    //6. Verify the status is “Active” of user name is “Enakshi Gowda”
    @Test
    public void testNo06() {
        response.body("[4].status", equalTo("active"));
    }

    //7. Verify the Gender = male of user name is “Dev Tagore”
    @Test
    public void testNo07() {
        response.body("[2].gender", equalTo("male"));
    }



}
