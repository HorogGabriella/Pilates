package com.example.Pilates.service.impl;

import com.example.Pilates.data.repository.FelhasznaloRepository;
import com.example.Pilates.service.FelhasznaloService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FelhasznaloServiceImpl implements FelhasznaloService {

    FelhasznaloRepository repo;
    public FelhasznaloServiceImpl(FelhasznaloRepository repo)
    {
        this.repo =repo;
    }

    @Override
    public UserDetailsService getUserDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repo.findByEmail(username);
            }
        };
    }
}
