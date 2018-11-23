package com.webbfontaine.githubanalizer.controllers;

import com.webbfontaine.github.api.GHCommitInfoData;
import com.webbfontaine.github.api.GHCommitInfoDataList;
import com.webbfontaine.github.client.GitHubClient;
import com.webbfontaine.githubanalizer.data.response.CommitData;
import com.webbfontaine.githubanalizer.data.response.CommitDataList;
import com.webbfontaine.githubanalizer.data.response.CommiterData;
import com.webbfontaine.githubanalizer.data.response.CommiterDataSet;
import com.webbfontaine.githubanalizer.data.response.ImpactData;
import com.webbfontaine.githubanalizer.data.response.ImpactDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private GitHubClient gitHubClient;

    @GetMapping("")
    public ModelAndView analyticPage(@RequestParam(name = "repositoryName") final String repositoryName,
                                     @RequestParam(name = "ownerName") final String ownerName,
                                     @RequestParam(name = "tab", defaultValue = "commiters") final String tab){

        final Map<String, String> model = new HashMap<>();
        model.put("repository", repositoryName);
        model.put("owner", ownerName);
        model.put("tab", tab);

        return new ModelAndView("analytics", model);
    }

    @GetMapping("/commiters")
    @ResponseBody
    public ResponseEntity<CommiterDataSet> commiters(@RequestParam(name = "repositoryName") final String repositoryName,
                                                     @RequestParam(name = "ownerName") final String ownerName){

        final int pageSize = 100;
        int pageNumber = 1;

        CommiterDataSet commiters = new CommiterDataSet();

        GHCommitInfoDataList commitInfos = gitHubClient.listOfCommiters(repositoryName, ownerName, pageNumber, pageSize).getBody();

        while (commitInfos.size() > 0){
            commiters.addAll(
                    commitInfos.stream().map(ci -> {
                        return new CommiterData(ci);}).collect(Collectors.toList()));

            commitInfos = gitHubClient.listOfCommiters(repositoryName, ownerName, ++pageNumber, pageSize).getBody();
        }

        return new ResponseEntity<>(commiters, HttpStatus.OK);
    }

    @GetMapping("/impacts")
    @ResponseBody
    public ResponseEntity<ImpactDataList> impacts(@RequestParam(name = "repositoryName") final String repositoryName,
                                                  @RequestParam(name = "ownerName") final String ownerName){

        final int pageSize = 100;
        int pageNumber = 1;

        Map<String, ImpactData> impacts = new LinkedHashMap<>();
        GHCommitInfoDataList commitInfos = gitHubClient.listOfCommiters(repositoryName, ownerName, pageNumber, pageSize).getBody();

        for(GHCommitInfoData ci : commitInfos){

            ImpactData impactData = impacts.get(ci.getCommit().getAuthor().getEmail());
            if (impactData == null){
                impactData = new ImpactData(new CommiterData(ci));
                impacts.put(ci.getCommit().getAuthor().getEmail(), impactData);
            }
            else{
                impactData.addImpact();
            }
        }

        final ImpactDataList imactList = new ImpactDataList(impacts.values());
        Collections.sort(imactList);

        return new ResponseEntity<>(imactList, HttpStatus.OK);
    }

    @GetMapping("/commits")
    @ResponseBody
    public ResponseEntity<CommitDataList> commits(@RequestParam(name = "repositoryName") final String repositoryName,
                                                  @RequestParam(name = "ownerName") final String ownerName){

        final int pageSize = 100;
        int pageNumber = 1;

        GHCommitInfoDataList commitInfos = gitHubClient.listOfCommiters(repositoryName, ownerName, pageNumber, pageSize).getBody();

        final CommitDataList commits = new CommitDataList();

        for(final GHCommitInfoData ci : commitInfos){
            commits.add(new CommitData(new CommiterData(ci), ci.getCommit().getMessage()));
        }

        return new ResponseEntity<>(commits, HttpStatus.OK);
    }
}
