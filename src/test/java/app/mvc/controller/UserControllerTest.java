package app.mvc.controller;


import app.mvc.service.UserService;
import app.mvc.util.UserUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Mock
    private UserService userService;

    @Before
    public void contextLoads() {
        assertThat(userController).isNotNull();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldPrintWelcome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    @WithMockUser()
    public void shouldRedirectToPersonalCabOnUserRole() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/personal"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void shouldRedirectToAllUsersOnAdminRole() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/allusers"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void shouldReturnAdminCab() throws Exception {
        when(userService.getAllUsers()).thenReturn(UserUtils.createUserList());

        mockMvc.perform(get("/allusers"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminCab"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attributeExists("message"));

    }

    @Test
    @WithMockUser
    public void shouldNReturnNotFoundAdminCab() throws Exception {
        mockMvc.perform(get("/allusers"))
                .andExpect(status().isForbidden());
    }

}
