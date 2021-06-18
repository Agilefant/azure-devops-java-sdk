# Work

- [REST API](https://docs.microsoft.com/en-us/rest/api/azure/devops/work/?view=azure-devops-rest-6.1)
- API Version: 6.1-preview.3

## Example

Before getting started you require personal access token to authenticate with **Azure DevOps** services REST API.
You can grab one by following the [documentation](https://docs.microsoft.com/en-us/azure/devops/organizations/accounts/use-personal-access-tokens-to-authenticate?WT.mc_id=docs-github-dbrown&view=azure-devops&tabs=preview-page).

You are required to set the default parameters before calling Core class in the library.

```java
public class Main {
    public static void main(String[] args) {
        String organisation = "myOrganisationName";
        String project = "myProject";
        String personalAccessToken = "accessToken";
        
        // instantiate AzDDefaultParameters with organisation name, project and personal access token.
        AzDDefaultParameters defaultParameters = new AzDDefaultParameters(organisation, project, personalAccessToken);
    
        // call API with the default parameters;
        WorkApi work = new WorkApi(defaultParameters);
        try {
            // Get iterations settings for a team
            work.getTeamSettingsIterations("my-team");

            // Get iteration settings for a team with iteration id
            var iterationId = work.getTeamSettingsIterations("my-team")
                    .getIterations().stream().findFirst().get().getId();

            work.getTeamSettingsIteration("my-team", iterationId);
            
            // Get workitems for an iteration
            work.getTeamIterationWorkItems("my-team", iterationId);

            // delete an iteration
            work.deleteTeamSettingsIteration("my-team", iterationId);
            
        } catch (AzDException | DefaultParametersException e) {
            e.printStackTrace();
        }
    }
}
```