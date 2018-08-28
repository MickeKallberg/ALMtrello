package se.javagroup.projecttask.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import se.javagroup.projecttask.repository.IssueRepository;
import se.javagroup.projecttask.repository.WorkItemRepository;
import se.javagroup.projecttask.repository.data.WorkItem;
import se.javagroup.projecttask.repository.data.WorkItemStatus;

public class BaseTest {

    @Autowired
    WorkItemRepository workItemRepository;

    @Autowired
    IssueRepository issueRepository;

    @Before
    public void setUpWorkitems() {
        workItemRepository.deleteAll();
        WorkItem workItem1 = new WorkItem(null, "Göra projekt del 2", WorkItemStatus.UNSTARTED);
        WorkItem workItem2 = new WorkItem(null, "Göra saker", WorkItemStatus.STARTED);
        WorkItem workItem3 = new WorkItem(null, "Göra klart projekt del 1", WorkItemStatus.DONE);
        workItemRepository.save(workItem1);
        workItemRepository.save(workItem2);
        workItemRepository.save(workItem3);
    }
}
