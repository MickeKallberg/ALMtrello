package se.javagroup.projecttask.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.javagroup.projecttask.resource.IssueResource;
import se.javagroup.projecttask.resource.TeamResource;
import se.javagroup.projecttask.resource.UserResource;
import se.javagroup.projecttask.resource.WorkItemResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(IssueResource.class);
        register(TeamResource.class);
        register(TeamResource.class);
        register(UserResource.class);
        register(WorkItemResource.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new ParameterNamesModule());
    }
}
