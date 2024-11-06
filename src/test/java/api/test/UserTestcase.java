package api.test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payloads.UserPayload;

import io.restassured.response.Response;

public class UserTestcase {
	public UserPayload payload;
	public Faker f;
	public Logger logger;// for logs

	@BeforeClass
	public void setUserPayload() {

		f = new Faker();
		payload = new UserPayload();
		payload.setId(f.idNumber().hashCode());
		payload.setUsername(f.name().username());
		payload.setFirstName(f.name().firstName());
		payload.setLastName(f.name().lastName());
		payload.setEmail(f.internet().safeEmailAddress());
		payload.setPassword(f.internet().password());
		payload.setPhone(f.phoneNumber().phoneNumber());
		payload.setId(0);

		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void validateCreateUser() {
		logger.info("**********user is creating ***********");
		Response res = UserEndpoint.Createuser(payload);
		System.out.println(this.payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("**********user is created ***********");

	}

	@Test(priority = 2)
	public void validateGetUser() {
		logger.info("**********Get user details ***********");
		System.out.println(this.payload.getUsername());
		Response res = UserEndpoint.getUser(this.payload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("********** user info is displayed ***********");
	}

	@Test(priority = 3)
	public void ValidateUpdateUserDetails() {
		logger.info("**********updating user details ***********");
		payload.setFirstName(f.name().firstName());
		payload.setLastName(f.name().lastName());
		payload.setEmail(f.internet().safeEmailAddress());

		Response response = UserEndpoint.updateUser(this.payload.getUsername(), payload);
		response.then().statusCode(200).log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		// get updated user details

		Response resafterUpdate = UserEndpoint.getUser(this.payload.getUsername());
		resafterUpdate.then().statusCode(200).log().all();
		logger.info("**********  user details are updated***********");
	}

	@Test(priority = 4)
	public void validateDeleteUserDetails() {
		logger.info("**********deleting user details ***********");
		Response res = UserEndpoint.deleteUser(this.payload.getUsername());
		res.then().statusCode(200).log().all();
		logger.info("**********deleted user details ***********");
	}

}