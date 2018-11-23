package com.webbfontaine.githubanalizer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Arsen Aleksanyan on 11/20/18.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() { return "home"; }
}
