package com.fedorusha.appsstore;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.model.Role;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.repository.RoleRepository;
import com.fedorusha.appsstore.repository.UserRepository;
import com.fedorusha.appsstore.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class AppsStoreApplicationTests {


    @MockBean
    private UserRepository userRep;

    @MockBean
    private RoleRepository roleRep;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testFindByUsername(){
        List<User> users = new ArrayList();
        User u = new User();
        u.setUsername("USER_TEST");
        users.add(u);
        User u2 = new User();
        u2.setUsername("ADMIN_TEST");
        users.add(u2);

        when(userRep.findByUsername("ADMIN_TEST")).thenReturn(u2);
        Assert.assertEquals(userService.findByUsername("ADMIN_TEST"), u2);
    }

    @Test
    void testFindByEmail(){
        List<User> users = new ArrayList();
        User u = new User();
        u.setEmail("lera@a");
        users.add(u);
        User u2 = new User();
        u2.setEmail("vika@b");
        users.add(u2);

        when(userRep.findByEmail("lera@a")).thenReturn(u);
        Assert.assertEquals(userService.findByEmail("lera@a"), u);
    }


}
