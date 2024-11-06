package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndpoint;
import api.payloads.UserPayload;
import io.restassured.response.Response;
import api.utilities.DataProviders;

public class DDTest {
	 UserPayload payload;

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String UserID, String username, String fName, String lName, String useremail, String password, String userPhone) {
         payload = new UserPayload();

        payload.setId(Integer.parseInt(UserID));
        payload.setUsername(username);
        payload.setFirstName(fName);
        payload.setLastName(lName);
        payload.setEmail(useremail);
        payload.setPassword(password);
        payload.setPhone(userPhone);

        Response res = UserEndpoint.Createuser(payload);
        System.out.println(payload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    
    @Test(priority = 2, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testUpdateUser(String UserID, String username, String fName, String lName, String useremail, String password, String userPhone) {
    	 payload = new UserPayload();


        payload.setId(Integer.parseInt(UserID));
        payload.setUsername(username);
        payload.setFirstName(fName);
        payload.setLastName(lName);
        payload.setEmail(useremail);
        payload.setPassword(password);
        payload.setPhone(userPhone);

        Response res = UserEndpoint.updateUser(this.payload.getUsername(),payload);
        System.out.println(payload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    
    
    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void deleteCreatedUser(String UserName) {
        Response res = UserEndpoint.deleteUser(UserName);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
