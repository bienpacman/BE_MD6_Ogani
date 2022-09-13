package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.Seller;
import com.codegym.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    IAppUserRepo iAppUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iAppUserRepo.findByUsername(username);
        return new User(appUser.getUsername(), appUser.getPassword(), appUser.getRoles());
    }

    public List<AppUser> getAll() {
        return (List<AppUser>) iAppUserRepo.findAll();
    }

    public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();
        for (AppUser e : getAll()) {
            emails.add(e.getUsername());
        }
        return emails;
    }

    public AppUser findByUserName(String username) {
        AppUser appUser = iAppUserRepo.findByUsername(username);
        return appUser;
    }

    public Optional<AppUser> findByUserId(Long id) {
        Optional<AppUser> appUser = iAppUserRepo.findById(id);
        return appUser;
    }

    public List<AppUser> getAppUserAsSeller() {
        return iAppUserRepo.getAppUserAsSeller();
    }


    public AppUser save(AppUser appUser) {
        return iAppUserRepo.save(appUser);
    }

    public void delete(Long id) {
        iAppUserRepo.deleteById(id);
    }


}
