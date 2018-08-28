package se.javagroup.projecttask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.javagroup.projecttask.repository.WorkItemRepository;
import se.javagroup.projecttask.repository.data.WorkItem;
import se.javagroup.projecttask.repository.data.WorkItemStatus;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkitemTest extends BaseTest {

    @Autowired
    Service service;

    @Autowired
    WorkItemRepository workItemRepository;

    @Test
    public void testOnlyWorkItemsWithStatusTypeUnstartedIsReturned() {
        List<WorkItem> workItems =  service.getAllWorkItems("unstarted", false, null);
        assertThat(workItems, hasSize(1));
        assertTrue(workItems.stream().allMatch( workItem -> WorkItemStatus.UNSTARTED == workItem.getWorkItemStatus()));
        assertFalse(workItems.stream().anyMatch( workItem -> WorkItemStatus.DONE == workItem.getWorkItemStatus()));
        assertFalse(workItems.stream().anyMatch( workItem -> WorkItemStatus.STARTED == workItem.getWorkItemStatus()));
    }

    @Test
    public void testOnlyWorkItemsWithStatusTypeStartedInUpperCaseIsReturned() {
        List<WorkItem> workItems =  service.getAllWorkItems("STARTED", false, null);
        assertThat(workItems, hasSize(1));
        assertTrue(workItems.stream().allMatch( workItem -> WorkItemStatus.STARTED == workItem.getWorkItemStatus()));
        assertFalse(workItems.stream().anyMatch( workItem -> WorkItemStatus.UNSTARTED == workItem.getWorkItemStatus()));
        assertFalse(workItems.stream().anyMatch( workItem -> WorkItemStatus.DONE == workItem.getWorkItemStatus()));
    }

    @Test
    public void testWorkItemsWithStatusTypeThatDontExists() {
        List<WorkItem> workItems =  service.getAllWorkItems("test", false, null);
        assertThat(workItems, is(empty()));
    }

    @Test
    public void testWorkItemsWithStatusParamterNullReturnsAllWorkItems() {
        List<WorkItem> workItems =  service.getAllWorkItems(null, false, null);
        assertThat(workItems, is(not(empty())));
        assertThat(workItems, hasSize(3));
        assertTrue(workItems.stream().anyMatch( workItem -> WorkItemStatus.STARTED == workItem.getWorkItemStatus()));
        assertTrue(workItems.stream().anyMatch( workItem -> WorkItemStatus.UNSTARTED == workItem.getWorkItemStatus()));
        assertTrue(workItems.stream().anyMatch( workItem -> WorkItemStatus.DONE == workItem.getWorkItemStatus()));
    }

}
