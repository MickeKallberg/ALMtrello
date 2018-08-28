package se.javagroup.projecttask.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.javagroup.projecttask.repository.TeamRepository;
import se.javagroup.projecttask.repository.UserRepository;
import se.javagroup.projecttask.service.exception.NotFoundException;

import javax.ws.rs.core.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceTest {

    @Autowired
    UserResource userResource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Before
    public void cleanDb() {
        teamRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test(expected = NotFoundException.class)
    public void testFromResource() {
        Response userByUserNumber = userResource.getUserByUserNumber(555L);

    }

}