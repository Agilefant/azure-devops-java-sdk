package org.azd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.azd.git.Git;
import org.azd.utils.AzDDefaultParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GitTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Git g;


    @Before
    public void init() throws IOException {
        String dir = System.getProperty("user.dir");
        File file = new File(dir + "/src/test/java/org/azd/_unitTest.json");
        MockParameters m = MAPPER.readValue(file, MockParameters.class);
        String organization = m.getO();
        String token = m.getT();
        String project = m.getP();
        AzDDefaultParameters defaultParameters = new AzDDefaultParameters(organization, project, token);
        g = new Git(defaultParameters);
    }

    @Test
    public void shouldCreateANewRepository() {
        g.createRepository("testRepository", "00000000-0000-0000-0000-000000000000");
    }

    @Test
    public void shouldDeleteRepository() {
        g.deleteRepository("00000000-0000-0000-0000-000000000000");
    }

    @Test
    public void shouldDeleteRepositoryFromRecycleBin() {
        g.deleteRepositoryFromRecycleBin("00000000-0000-0000-0000-000000000000");
    }

    @Test
    public void shouldGetDeletedGitRepositories() {
        g.getDeletedRepositories();
    }

    @Test
    public void shouldGetRecycleBinRepositories() {
        g.getRecycleBinRepositories();
    }

    @Test
    public void shouldGetRepository() {
        g.getRepository("testRepository");
    }

    @Test
    public void shouldGetRepositories() {
        g.getRepositories();
    }

    @Test
    public void shouldRestoreRepositoryFromRecycleBin() {
        g.restoreRepositoryFromRecycleBin("00000000-0000-0000-0000-000000000000", false);
    }

    @Test
    public void shouldUpdateRepository() {
        g.updateRepository("00000000-0000-0000-0000-000000000000", "newName", "develop");
    }

    @Test
    public void shouldCreateANewPullRequest() {
        g.createPullRequest(g.getRepositories().getRepositories().stream().findFirst().get().getId(),
                "refs/heads/master", "refs/heads/develop", "New feature", "Adding new feature",
                new String[]{ "d6245f20-2af8-44f4-9451-8107cb2767db" });
    }

    @Test
    public void shouldRetrieveAPullRequest() {
        g.getPullRequest(g.getRepositories().getRepositories().stream().findFirst().get().getName(), 2);
    }

    @Test
    public void shouldRetrieveAPullRequestById() {
        g.getPullRequestById(2);
    }

    @Test
    public void shouldRetrieveAllPullRequestsFromARepository() {
        g.getPullRequests(g.getRepositories().getRepositories().stream().findFirst().get().getName());
    }

    @Test
    public void shouldGetPullRequestsByProject() {
        g.getPullRequestsByProject().getPullRequests();
    }
}
