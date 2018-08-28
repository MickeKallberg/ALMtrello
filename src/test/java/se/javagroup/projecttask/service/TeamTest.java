package se.javagroup.projecttask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.javagroup.projecttask.repository.TeamRepository;
import se.javagroup.projecttask.repository.UserRepository;
import se.javagroup.projecttask.repository.WorkItemRepository;
import se.javagroup.projecttask.repository.data.Team;
import se.javagroup.projecttask.repository.data.User;
import se.javagroup.projecttask.repository.data.WorkItem;
import se.javagroup.projecttask.repository.data.WorkItemStatus;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamTest {

    User user1;
    User user2;
    User user3;

    @Autowired
    Service service;

    @Autowired
    WorkItemRepository workItemRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    Team team;
    @Before
    public void setUpUsersWorkitemsAndTeam() {
        workItemRepository.deleteAll();
        userRepository.deleteAll();
        teamRepository.deleteAll();

        team = new Team("America", true);
        teamRepository.save(team);

        user1 = new User();
        user1.setFirstName("Donald");
        user1.setLastName("Trump");
        user1.setUsername("ih8mueller");
        user1.setUserNumber(222L);
        user1.setStatus(true);
        user1.setTeam(team);
        userRepository.save(user1);

        user2 = new User();
        user2.setFirstName("Donald");
        user2.setLastName("Trump");
        user2.setUsername("ih8mueller");
        user2.setUserNumber(333L);
        user2.setStatus(true);
        user2.setTeam(team);
        userRepository.save(user2);

        user3 = new User();
        user3.setFirstName("Donald");
        user3.setLastName("Trump");
        user3.setUsername("ih8mueller");
        user3.setUserNumber(444L);
        user3.setStatus(true);
        user3.setTeam(team);
        userRepository.save(user3);
    }

    @Test
    public void testAllThreeWorkItemsFromTeamOwnedByOneUser(){
        WorkItem workItem1 = new WorkItem(null, "Foobar1", WorkItemStatus.UNSTARTED,
                user1);
        WorkItem workItem2 = new WorkItem(null, "Foobar2", WorkItemStatus.STARTED,
                user1);
        WorkItem workItem3 = new WorkItem(null, "Foobar3", WorkItemStatus.DONE,
                user1);
        workItemRepository.save(workItem1);
        workItemRepository.save(workItem2);
        workItemRepository.save(workItem3);

        List<WorkItem> workItems =  service.getAllWorkItemsForTeam(team.getId());
        assertThat(workItems, is(not(empty())));
        assertEquals(3, workItems.size());
    }

    @Test
    public void testAllThreeWorkItemsFromTeamOwnedByTwoUser(){
        WorkItem workItem1 = new WorkItem(null, "Foobar1", WorkItemStatus.UNSTARTED,
                user1);
        WorkItem workItem2 = new WorkItem(null, "Foobar2", WorkItemStatus.STARTED,
                user2);
        WorkItem workItem3 = new WorkItem(null, "Foobar3", WorkItemStatus.DONE,
                user2);
        workItemRepository.save(workItem1);
        workItemRepository.save(workItem2);
        workItemRepository.save(workItem3);

        List<WorkItem> workItems =  service.getAllWorkItemsForTeam(team.getId());
        assertThat(workItems, is(not(empty())));
        assertEquals(3, workItems.size());
    }

    @Test
    public void testAllThreeWorkItemsFromTeamOwnedByThreeUser(){
        WorkItem workItem1 = new WorkItem(null, "Foobar1", WorkItemStatus.UNSTARTED,
                user1);
        WorkItem workItem2 = new WorkItem(null, "Foobar2", WorkItemStatus.STARTED,
                user2);
        WorkItem workItem3 = new WorkItem(null, "Foobar3", WorkItemStatus.DONE,
                user3);
        workItemRepository.save(workItem1);
        workItemRepository.save(workItem2);
        workItemRepository.save(workItem3);

        List<WorkItem> workItems =  service.getAllWorkItemsForTeam(team.getId());
        assertThat(workItems, is(not(empty())));
        assertEquals(3, workItems.size());
    }
}



