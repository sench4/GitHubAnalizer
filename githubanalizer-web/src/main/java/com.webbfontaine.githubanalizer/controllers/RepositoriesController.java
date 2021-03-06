package com.webbfontaine.githubanalizer.controllers;

import com.webbfontaine.github.api.GHRepoSearchResultData;
import com.webbfontaine.github.client.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * This controller is responsible to return repository data from GitHub API client
 * Created by Arsen Aleksanyan on 11/20/18.
 */
@Controller
@RequestMapping("/repositories")
public class RepositoriesController extends AbstractGHHttpClientController {

    @Autowired
    private GitHubClient gitHubClient;

    /**
     * Searches public repository in GitHub for given repository name and returns the result in JSON format
     * @param repo repository name
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<GHRepoSearchResultData> searchPublicRepository(
            @RequestParam("repo") final String repo,
            @RequestParam(value = "pageNumber", defaultValue = "1") final Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") final Integer pageSize){

        return new ResponseEntity<>(this.gitHubClient.searchPublicRepository(repo, pageNumber, pageSize).getBody(), HttpStatus.OK);
    }

    /**
     * Searches public repository in GitHub for given repository name and
     * forwards to the search result page
     * @param repo
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/search-result")
    public ModelAndView searchResult(
            @RequestParam("repo") final String repo,
            @RequestParam(value = "pageNumber", defaultValue = "1") final Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "100") final Integer pageSize){

        final Map<String, GHRepoSearchResultData> model = new HashMap<>();
        model.put("repositories", this.gitHubClient.searchPublicRepository(repo, pageNumber, pageSize).getBody());

        return new ModelAndView("searchResults", model);
    }
}
