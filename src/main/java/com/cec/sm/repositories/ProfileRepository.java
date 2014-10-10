package com.cec.sm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cec.sm.domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    public List<Profile> findByEmail(String email);

}
