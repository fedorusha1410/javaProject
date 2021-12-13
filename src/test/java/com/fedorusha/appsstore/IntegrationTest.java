package com.fedorusha.appsstore;

import com.fedorusha.appsstore.model.Role;
import com.fedorusha.appsstore.model.StatusUser;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.security.JwtUserDetailsService;
import com.fedorusha.appsstore.security.jwt.JwtTokenProvider;
import com.fedorusha.appsstore.security.jwt.JwtUserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private JwtUserDetailsService ser;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testJwtTokenProviderWithTwoRolesByAdmin() throws Exception {
        setUp();

        List<Role> roles = new ArrayList<>();
        Role admin_r= new Role();
        admin_r.setName("ROLE_ADMIN");
        Role user_r= new Role();
        user_r.setName("ROLE_USER");

        roles.add(admin_r);
        roles.add(user_r);
        User user = new User();
        user.setStatus(StatusUser.ACTIVE);
        user.setUsername("test");
        user.setRoles(roles);
        when(ser.loadUserByUsername("test")).thenReturn(JwtUserFactory.create(user));

        String token = jwtTokenProvider.createToken("test", roles );
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/apps").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }

    @Test
    void testJwtTokenProviderWithTwoRolesByUser() throws Exception {
        setUp();

        List<Role> roles = new ArrayList<>();
        Role admin_r= new Role();
        admin_r.setName("ROLE_ADMIN");
        Role user_r= new Role();
        user_r.setName("ROLE_USER");

        roles.add(admin_r);
        roles.add(user_r);
        User user = new User();
        user.setStatus(StatusUser.ACTIVE);
        user.setUsername("test");
        user.setRoles(roles);
        when(ser.loadUserByUsername("test")).thenReturn(JwtUserFactory.create(user));

        String token = jwtTokenProvider.createToken("test", roles );
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/apps").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }
}
