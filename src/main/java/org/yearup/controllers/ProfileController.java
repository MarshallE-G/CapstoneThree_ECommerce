package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profiles")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")
public class ProfileController
{
    private ProfileDao profileDao;
    private UserDao userDao;
    
    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao)
    {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }
    
    @GetMapping("")
    public Profile getProfile(Principal principal) {
        try {
            String userName = principal.getName();
            User   user     = userDao.getByUserName(userName);
            int    userId   = user.getId();
            
            var profile = profileDao.getByUserId(userId);
    
            if(profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
            return profile;
        }
        catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
