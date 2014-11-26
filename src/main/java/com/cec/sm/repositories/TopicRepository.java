package com.cec.sm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cec.sm.domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    public List<Topic> findByName(String name);
    
}
