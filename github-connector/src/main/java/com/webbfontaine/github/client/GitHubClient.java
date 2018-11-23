package com.webbfontaine.github.client;

import com.webbfontaine.github.api.GHCommitInfoDataList;
import com.webbfontaine.github.api.GHRepoSearchResultData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * API client for github
 * Created by Arsen Aleksanyan on 11/20/18.
 */
public class GitHubClient extends RestClient {

    private String gitHubApiUrl;

    public GitHubClient(final RestTemplate restTemplate, final String gitHubApiUrl){
        super(restTemplate);
        this.gitHubApiUrl = gitHubApiUrl;
    }

    /**
     * Searches public github repositories
     * @param query The name pattern of the repository
     * @param pageNumber page number
     * @param pageSize per page result size
     * @return
     */
    public ResponseEntity<GHRepoSearchResultData> searchPublicRepository(final String query, final Integer pageNumber, final Integer pageSize){
        return getForEntity("/search/repositories?q={q}&page={pageNumber}&per_page={pageSize}", GHRepoSearchResultData.class, query, pageNumber, pageSize);
    }

    /**
     * Returns list of commiters for the given repository name and owner name
     * @param repositoryName The full repository name
     * @param ownerName The owner login name
     * @param pageNumber page number
     * @param pageSize per page result size
     * @return
     */
    public ResponseEntity<GHCommitInfoDataList> listOfCommiters(final String repositoryName, final String ownerName, final Integer pageNumber, final Integer pageSize){
        return getForEntity("/repos/{ownerName}/{repositoryName}/commits?page={pageNumber}&per_page={pageSize}", GHCommitInfoDataList.class, ownerName, repositoryName, pageNumber, pageSize);
    }

    @Override
    public String buildUrl(String path) {
        return gitHubApiUrl + path;
    }
}
