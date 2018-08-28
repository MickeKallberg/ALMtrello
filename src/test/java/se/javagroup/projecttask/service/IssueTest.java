package se.javagroup.projecttask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.javagroup.projecttask.repository.IssueRepository;
import se.javagroup.projecttask.repository.WorkItemRepository;
import se.javagroup.projecttask.repository.data.Issue;
import se.javagroup.projecttask.repository.data.WorkItem;
import se.javagroup.projecttask.repository.data.WorkItemStatus;
import se.javagroup.projecttask.service.exception.BadInputException;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueTest extends BaseTest {

    @Autowired
    Service service;

    @Test
    public void testCreateIssuewithWorkItemDone(){
        List<WorkItem> workItems = service.getAllWorkItems(null, false, null);
        boolean thrown = false;
        try {
            Issue issue1 = new Issue("Redovisa projekt del 1", workItems.get(2));
            service.createIssue(issue1, workItems.get(2).getId());
        }
        catch (BadInputException e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test(expected = BadInputException.class)
    public void testIfWrongWorkItemStatus() {
        List<WorkItem> workItems = service.getAllWorkItems(null, false, null);
        Issue issue1 = new Issue("Redovisa projekt del 1", workItems.get(0));
        Optional<Issue> newIssue =  service.createIssue(issue1, workItems.get(0).getId());
    }
}