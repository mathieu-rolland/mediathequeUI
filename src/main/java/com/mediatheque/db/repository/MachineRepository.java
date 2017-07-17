package com.mediatheque.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediatheque.model.impl.Machine;

public interface MachineRepository extends JpaRepository<Machine, Integer>{

}
