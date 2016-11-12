package com.perso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perso.model.impl.Machine;

public interface MachineRepository extends JpaRepository<Machine, Integer>{

}
