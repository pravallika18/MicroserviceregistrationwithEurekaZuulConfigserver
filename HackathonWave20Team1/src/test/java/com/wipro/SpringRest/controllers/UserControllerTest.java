package com.wipro.SpringRest.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sr.assesmentengine.microservice.HackathonWave20Team1Application;
import com.sr.assesmentengine.microservice.domain.User;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HackathonWave20Team1Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {


    @LocalServerPort

    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    User user;
    

    @Before

    public void setUp() throws Exception {
   User user=new User(1,"abcd","abc@gmail.com");
         

    }
    private String createURLWithPort(String uri) {

        return "http://localhost:" + port + uri;

    }

	@Test
	public void testAllUsers() throws Exception{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(

                createURLWithPort("/v1.0/UserProfile/user"),

                HttpMethod.GET, entity, String.class);
       System.out.println(response);
        assertNotNull(response);
	}
	 @Test
	    public void testSaveUser() throws Exception {

	        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

	        ResponseEntity<String> response = restTemplate.exchange(

	                createURLWithPort("/v1.0/UserProfile/user"),

	                HttpMethod.POST, entity, String.class);

	        assertNotNull(response);
	        
	        

	    }
	 @Test
	    public void testUpdateUser() throws Exception {

	        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

	        ResponseEntity<String> response = restTemplate.exchange(

	                createURLWithPort("/v1.0/UserProfile/user"),

	                HttpMethod.PUT, entity, String.class);

	        assertNotNull(response);
	        System.out.println(response);
	        
	        String actual = response.getBody();

	        System.out.println(actual);

	        assertEquals("Successfully user updated",actual);

	    }
	 @Test
	    public void testDeleteUser() throws Exception {

	        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

	        ResponseEntity<String> response = restTemplate.exchange(

	                createURLWithPort("/v1.0/UserProfile/user"),

	                HttpMethod.PUT, entity, String.class);

	        assertNotNull(response);
	        System.out.println(response);
	        
	        String actual = response.getBody();

	        System.out.println(actual);

	        assertEquals("User profile deleted",actual);

	    }
	 @Test
	    public void testgetUserById() throws Exception {

	        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

	        ResponseEntity<String> response = restTemplate.exchange(

	                createURLWithPort("/v1.0/UserProfile/user/1"),

	                HttpMethod.PUT, entity, String.class);

	        assertNotNull(response);
	       
	    }
	 
	 
}
		
