package com.omdbapi.trendyol;

import com.omdbapi.common.ITestLogger;
import com.omdbapi.common.TestLogger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public  class RestAssuredConfiguration {


    protected ITestLogger testLogger;
    public RestAssuredConfiguration(){
        testLogger = new TestLogger();
    }

    @BeforeSuite(alwaysRun = true)
    public void configure() {

    }

    public RequestSpecification getRequestSpecification() {

        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response getResponse(RequestSpecification requestSpecification, String endpoint, int
            status){

        Response response = requestSpecification.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),status);
        testLogger.info("Status Code:",response.statusCode());

        //Loglama yapıldı.
        response.then().log().all();

        return response;
    }
}
