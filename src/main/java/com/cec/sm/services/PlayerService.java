package com.cec.sm.services;

import com.cec.sm.domain.LoginAttempt;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cec.sm.domain.Player;
import com.cec.sm.repositories.LoginAttemptRepository;
import com.cec.sm.repositories.PlayerRepository;

@Service
@Repository
@Transactional
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    
    @Autowired
    LoginAttemptRepository loginAttemptRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Player findOne(Long id) {
        return playerRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Player create(Player player) {
        return playerRepository.saveAndFlush(player);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Player update(Player player) {
        return playerRepository.saveAndFlush(player);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        playerRepository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LoginAttempt loginById(Long id) {

        LoginAttempt result = null;

        Player player = playerRepository.findOne(id);

        if (player != null) {
            result = new LoginAttempt();
            result.setAttemptDate(new Date());
            result.setPlayer(player);

            result = loginAttemptRepository.saveAndFlush(result);
        }

        return result;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LoginAttempt loginByEmail(String email) {

        LoginAttempt result = null;

        List<Player> profiles = playerRepository.findByEmail(email);

        if (profiles != null && profiles.size() > 0) {
            result = loginById(profiles.get(0).getId());
        }

        return result;
    }
}
