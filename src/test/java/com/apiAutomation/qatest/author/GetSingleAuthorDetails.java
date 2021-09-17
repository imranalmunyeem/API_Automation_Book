package com.apiAutomation.qatest.author;

import com.apiAutomation.qatest.base.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class GetSingleAuthorDetails extends BaseTest {

    @org.junit.Test
    public void shouldPassForValidData() {
        RequestSpecification request = getRequest();
        String id = "4";
        Response response = request.get("/authors/" + id);

        Assert.assertEquals(response.getStatusCode(), 200);
        //basic test
        Assert.assertTrue(isSucceeded(response));
        Assert.assertNotNull(response.jsonPath().getMap("data"));
    }

    @org.junit.Test
    public void makeSureApiNotFailForInvalidData() {
        RequestSpecification request = getRequest();
        String id = "-99";
        Response response = request.get("/authors/" + id);

        Assert.assertEquals(response.getStatusCode(), 404);
        //basic test
        Assert.assertTrue(isNotSucceeded(response));
        Assert.assertEquals(response.jsonPath().getString("message"), "Record not found");
    }

}
