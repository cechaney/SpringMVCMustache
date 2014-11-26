package com.cec.sm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cec.sm.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public List<Team> findByName(String name);
    
    @Query("select t from Team t where t.captain.id = :id")    
    public List<Team> findByCaptainById(@Param(value="id") Long id);
    
    @Query("select t from Team t where t.captain.email = :email")    
    public List<Team> findByCaptainByEmail(@Param(value="email") String email);    

    @Query("select t from Team t where t.captain.name like :name%")    
    public List<Team> findByCaptainByName(@Param(value="name") String name);     
}
