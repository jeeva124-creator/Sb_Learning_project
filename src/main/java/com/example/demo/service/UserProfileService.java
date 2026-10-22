package com.example.demo.service;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserProfileService {

    private UserProfileRepository userProfileRepository;


    public UserProfileService(UserProfileRepository repository) {
        this.userProfileRepository = repository;
    }

    public UserProfile getMyProfile(String email) {
        return userProfileRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Profile not found"
                        ));
    }

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }




    public UserProfile updateMyProfile(
            String email, UserProfile updatedProfile) {

        UserProfile existingProfile = userProfileRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Profile not found"));

        existingProfile.setName(updatedProfile.getName());
        existingProfile.setPhone(updatedProfile.getPhone());
        existingProfile.setBio(updatedProfile.getBio());

        return userProfileRepository.save(existingProfile);
    }

    public void deleteMyProfile(String email) {

        UserProfile existingProfile = userProfileRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Profile not found"));

        userProfileRepository.delete(existingProfile);
    }

}