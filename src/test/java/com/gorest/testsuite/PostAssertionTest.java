package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){

        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .queryParam("page","1")
                .queryParam("per-page","20")
                .when()
                .get("/posts?page=1&per_page=25")
                .then()
                .statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void testNo01() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
    @Test
    public void testNo02() {
        response.body("[3].id", equalTo("Defaeco in carbo decet audeo volutabrum corroboro."));
    }

    //3. Check the single user_id in the Array list (7018214)
    @Test
    public void testNo03() {
       response.body("user_id", hasItem(7018214));
    }

    //4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
    @Test
    public void testNo04() {
        response.body("user_id", hasItems(7018214, 7018214, 7018186));
    }

    //Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus. Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    @Test
    public void testNo05() {
        response.body("[0].body", equalTo("Tondeo recusandae cornu. Laborum cernuus urbs. Cedo trado est. Ipsum sperno careo. Tergo amicitia cunae. Cetera tabesco benigne. Deinde capillus valens. Inflammatio tempore consequatur. Absconditus corona contabesco. Artificiose vita volup. Voluptatem utrimque videlicet. Suffoco terror summopere."));
    }

}
