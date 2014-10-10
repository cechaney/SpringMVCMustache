/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.cec.sm.domain.Profile;
import com.cec.sm.services.ProfileService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public 
    ModelAndView sayHello(HttpServletRequest req, HttpServletResponse res) {
        
        ModelAndView result;
        
        result = new ModelAndView();
        result.setViewName("hello");
        result.addObject("name", req.getParameter("name"));
        
        return result;
        
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Profile> findAllProfile(
            HttpServletRequest req,
            HttpServletResponse res) {

        return profileService.findAll();

    }

    @RequestMapping(value = "/profile/generate", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Profile generateProfile(
            HttpServletRequest req,
            HttpServletResponse res) {

        Profile profile = new Profile();

        profile.setEmail(UUID.randomUUID() + "@email.com");
        profile.setPassword(UUID.randomUUID().toString());

        return profileService.create(profile);

    }

    @RequestMapping(value = "/profile/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    LoginAttempt login(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email) {

        if (id != null) {
            return profileService.loginById(id);
        } else if (email != null) {
            return profileService.loginByEmail(email);
        } else {
            return null;
        }

    }

}
