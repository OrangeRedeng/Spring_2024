package com.example.tortland.service;

import com.example.tortland.model.Role;
import com.example.tortland.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getList() {
        return roleRepository.findAll();
    }

    public Role getOne(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

}
