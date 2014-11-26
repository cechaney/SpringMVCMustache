package com.cec.sm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cec.sm.domain.Player;

@Repository
public interface ProfileRepository extends JpaRepository<Player, Long> {

    public List<Player> findByEmail(String email);

}
