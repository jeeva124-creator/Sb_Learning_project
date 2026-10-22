package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(
            UserProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public UserProfile getMyProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return profileService.getMyProfile(email);
    }

    @GetMapping("/all")
    public List<UserProfile> getAllProfiles() {
        return profileService.getAllProfiles();
    }
    @PutMapping("/me")
    public UserProfile updateMyProfile(
            Authentication authentication,
            @RequestBody UserProfile userProfile) {

        String email = authentication.getName();

        return profileService.updateMyProfile(email, userProfile);
    }
    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyProfile(Authentication authentication) {

        String email = authentication.getName();

        profileService.deleteMyProfile(email);
    }
}