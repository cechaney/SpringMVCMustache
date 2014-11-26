package com.cec.sm.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cec.sm.domain.LoginAttempt;
import com.cec.sm.domain.Player;
import com.cec.sm.services.PlayerService;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Player> findAllProfile(
            HttpServletRequest req,
            HttpServletResponse res) {

        return playerService.findAll();

    }

    @RequestMapping(value = "/profile/generate", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Player generateProfile(
            HttpServletRequest req,
            HttpServletResponse res) {

        Player profile = new Player();

        profile.setEmail(UUID.randomUUID() + "@email.com");
        profile.setPassword(UUID.randomUUID().toString());

        return playerService.create(profile);

    }

    @RequestMapping(value = "/profile/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    LoginAttempt login(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email) {

        if (id != null) {
            return playerService.loginById(id);
        } else if (email != null) {
            return playerService.loginByEmail(email);
        } else {
            return null;
        }

    }

}
