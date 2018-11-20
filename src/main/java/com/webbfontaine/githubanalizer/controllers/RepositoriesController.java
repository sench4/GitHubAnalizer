package com.webbfontaine.githubanalizer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Arsen Aleksanyan on 11/20/18.
 */

@RestController("/repositories")
public class RepositoriesController {

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam("repo") final String repo){


        return null;
    }
}
