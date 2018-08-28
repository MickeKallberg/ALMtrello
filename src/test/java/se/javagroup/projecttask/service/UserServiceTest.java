package se.javagroup.projecttask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.javagroup.projecttask.repository.TeamRepository;
import se.javagroup.projecttask.repository.UserRepository;
import se.javagroup.projecttask.repository.data.Team;
import se.javagroup.projecttask.repository.data.User;



import java.util.List;



import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    Service service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;


    @Before
    public void cleanDb() {

        userRepository.deleteAll();
    }

    @Test
    public void testFindUserByUsernumber()  {

        Team team = new Team("sjysstaTeamet", true);

        teamRepository.save(team);

        User user = new User();
        user.setFirstName("Joakim");
        user.setLastName("Anell");
        user.setUsername("HejsanHoppsan");
        user.setUserNumber(555L);
        user.setStatus(true);
        user.setTeam(team);

        userRepository.save(user);

        assertEquals(userRepository.findAll().size(), 1);

        List<User> users = service.getAllUsers(null, null, null, null, "555");

        assertEquals(users.size(), 1);
    }


    @Test
    public void testCreateUser(){
        User user = new User();
        user.setFirstName("Trix");
        user.setLastName("Hamilton");
        user.setUsername("TrixHamilton2018");
        user.setUserNumber(455L);
        user.setStatus(true);

        userRepository.save(user);

        assertEquals(userRepository.findAll().size(), 1);

        List<User> users2 = service.getAllUsers("Trix", "Hamilton", "TrixHamilton2018", null, "455");

        assertEquals(users2.size(), 1);
    }



    @Test
    public void testAddUserToTeam(){

        Team team1 = new Team("Frontend",true);
        teamRepository.save(team1);


        User user = new User();
        user.setFirstName("Trix");
        user.setLastName("Hamilton");
        user.setUsername("TrixHamilton2018");
        user.setUserNumber(455L);
        userRepository.save(user);

        Long teamID = team1.getId();

        Long userNumber = user.getUserNumber();
        service.addUserToTeam(teamID, userNumber);
        Iterable<Team> getAllTeams = service.getAllTeams();
        assertTrue(getAllTeams.iterator().next().getId() == teamID);
        List<User> users = service.getAllUsers(null, null, null, null, "455");
        assertEquals(users.size(), 1);

    }


}