package org.example.integrations;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;

public class JiraService {
    private final JiraRestClient client;

    public JiraService(String jiraUrl, String username, String password) {
        URI jiraServerUri = URI.create(jiraUrl);
        this.client = new AsynchronousJiraRestClientFactory()
            .createWithBasicHttpAuthentication(jiraServerUri, username, password);
    }
}
