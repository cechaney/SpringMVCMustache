package com.cec.sm.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cec.sm.domain.LoginAttempt;
import com.cec.sm.domain.Profile;
import com.cec.sm.repositories.LoginAttemptRepository;
import com.cec.sm.repositories.ProfileRepository;

@Service
@Repository
@Transactional
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    LoginAttemptRepository loginAttemptRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Profile findOne(Long id) {
        return profileRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Profile create(Profile profile) {
        return profileRepository.saveAndFlush(profile);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Profile update(Profile profile) {
        return profileRepository.saveAndFlush(profile);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        profileRepository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LoginAttempt loginById(Long id) {

        LoginAttempt result = null;

        Profile profile = profileRepository.findOne(id);

        if (profile != null) {
            result = new LoginAttempt();
            result.setAttemptDate(new Date());
            result.setProfile(profile);

            result = loginAttemptRepository.saveAndFlush(result);
        }

        return result;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LoginAttempt loginByEmail(String email) {

        LoginAttempt result = null;

        List<Profile> profiles = profileRepository.findByEmail(email);

        if (profiles != null && profiles.size() > 0) {
            result = loginById(profiles.get(0).getId());
        }

        return result;
    }
}
