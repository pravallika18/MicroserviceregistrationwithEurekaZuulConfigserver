package com.wipro.SpringRest.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.never;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.sr.assesmentengine.microservice.domain.User;
import com.sr.assesmentengine.microservice.repository.UserRepository;
import com.sr.assesmentengine.microservice.service.UserServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplSpyTest {

   @Spy
   private UserServiceImpl userServiceImpl;
   @Mock
   private UserRepository userRepository;
   @Mock
   private User user;

 @Test(expected=NullPointerException.class)
   public void shouldThrowNullPointerException_whenGetUserByIdIsCalledWithoutContext() throws Exception {
       //Act
       User retrievedUser = userServiceImpl.getById(5);
       //Assert
       assertThat(retrievedUser, is(equalTo(user)));
   }
 @Test(expected=NullPointerException.class)
 public void shouldThrowNullPointerException_whenCreateUserIsCalledWithoutContext() throws Exception {
       //Arrange
       Mockito.doReturn(user).when(userRepository).save(user);
       //Act
       User savedUser = userServiceImpl.createProfile(user);
       //Assert
       assertThat(savedUser, is(equalTo(user)));
   }

  @Test
   public void shouldVerifyThatGetUserByIdIsCalled() throws Exception {
       //Arrange
       Mockito.doReturn(user).when(userServiceImpl).getById(5);
       //Act
       User retrievedUser=userServiceImpl.getById(5);
       //Assert
       Mockito.verify(userServiceImpl).getById(5);
   }
   @Test
   public void shouldVerifyThatSaveUserIsNotCalled() throws Exception {
       //Arrange
       Mockito.doReturn(user).when(userServiceImpl).getById(5);
       //Act
       User retrievedUser=userServiceImpl.getById(5);
       //Assert
       Mockito.verify(userServiceImpl,never()).createProfile(user);
   }
   @Test
   public void shouldDeleteUser() throws Exception{
	   Mockito.doReturn("User profile deleted").when(userServiceImpl).deleteProfile(5);
       //Act
       String status=userServiceImpl.deleteProfile(5);
       //Assert
     assertEquals("User profile deleted", status);
	   
   }
   @Test
   public void shouldUpdateUser() throws Exception{
	   Mockito.doReturn("Successfully user updated").when(userServiceImpl).updateProfile(user);
       //Act
       String status=userServiceImpl.updateProfile(user);
       //Assert
     assertEquals("Successfully user updated", status);
	   
   }
}


