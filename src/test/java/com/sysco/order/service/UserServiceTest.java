package com.sysco.order.service;

import com.sysco.order.dao.UserDetailDao;
import com.sysco.order.exception.EmptyUserException;
import com.sysco.order.exception.UserCreationException;
import com.sysco.order.model.TempUser;
import com.sysco.order.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletResponse;

import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    private User user1 = new User();
    private TempUser tempuser1 = new TempUser();
    HttpServletResponse httpServletResponse;

    @Mock
    UserDetailDao userDetailDao;

    @InjectMocks
    UserService userService;

    @Before
    public void setUp(){
        tempuser1.setId("1");
        tempuser1.setUserName("cola");
        tempuser1.setpassword("cola");

        user1.setId("1");
        user1.setUserName("cola");
        user1.setpassword("cola");
    }

    @Test
    public void logIn_should_ReturnResponseEntityWithUser_When_CorrectCredetialsGiven(){

        when(userDetailDao.findByUserName("cola")).thenReturn(user1);
        ResponseEntity userResponse = userService.logIn(httpServletResponse, tempuser1);
        Assert.assertNotNull(userResponse);
        Assert.assertEquals(user1, userResponse.getBody());
        Assert.assertEquals(200,userResponse.getStatusCodeValue());
    }

    @Test
    public void logIn_should_ReturnResponseEntityWithAnErrorMessage_When_IncorrectCredentialsGiven(){

        when(userDetailDao.findByUserName("cola")).thenReturn(null);
        ResponseEntity userResponse = userService.logIn(httpServletResponse, tempuser1);
        Assert.assertNotNull(userResponse);
        Assert.assertEquals("invalid credentials", userResponse.getBody());
        Assert.assertEquals(401,userResponse.getStatusCodeValue());
    }

    @Test
    public void logIn_Should_ReturnResponseEntityWithAnExceptionMessage_When_IncorrectCredentialsGiven(){

        when(userDetailDao.findByUserName("cola")).thenThrow(EmptyUserException.class);
        ResponseEntity userResponse = userService.logIn(httpServletResponse, tempuser1);
        Assert.assertNotNull(userResponse);
        Assert.assertNull(userResponse.getBody());
        Assert.assertEquals(404,userResponse.getStatusCodeValue());
    }

    @Test
    public void createUser_Should_ReturnResponseEntityWithSuccess_When_NewUsernameGiven(){

        when(userDetailDao.findByUserName("cola5")).thenReturn(null);
        when(userDetailDao.createUser(user1.getUserName(), user1.getpassword())).thenReturn(true);
        ResponseEntity userResponse = userService.createUser(user1);
        Assert.assertNotNull(userResponse);
        Assert.assertEquals(true,userResponse.getBody());
        Assert.assertEquals(200,userResponse.getStatusCodeValue());
    }
    @Test
    public void createUser_Should_ReturnResponseEntityWithAnErrorMessage_When_ExistingUsernameGiven(){

        when(userDetailDao.findByUserName("cola")).thenReturn(user1);
        ResponseEntity userResponse = userService.createUser(user1);
        Assert.assertNotNull(userResponse);
        Assert.assertEquals("user already exists",userResponse.getBody());
        Assert.assertEquals(403,userResponse.getStatusCodeValue());
    }

    @Test
    public void createUser_Should_ReturnResponseEntityWithAnExceptionMessage_When_DBFailure(){

        when(userDetailDao.findByUserName("cola")).thenThrow(UserCreationException.class);
        ResponseEntity userResponse = userService.createUser(user1);
        Assert.assertNotNull(userResponse);
        Assert.assertNull(userResponse.getBody());
        Assert.assertEquals(500,userResponse.getStatusCodeValue());
    }
}
