package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;
@Repository("fake")
public class FakeApplicationUserDaoServices implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder ;

    @Autowired
    public FakeApplicationUserDaoServices(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();

    }


    private List<ApplicationUser> getApplicationUsers(){

        return Lists.newArrayList(
                new ApplicationUser(
                        STUDENT.getGrantedAuthorities(),
                        "ahmed",
                        passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true


                ),
                new ApplicationUser(
                        Admin.getGrantedAuthorities(),
                        "mohamed",
                        passwordEncoder.encode("password123"),
                        true,
                        true,
                        true,
                        true


                ),
                new ApplicationUser(
                        AdminTraine.getGrantedAuthorities(),
                        "tom",
                        passwordEncoder.encode("password123"),
                        true,
                        true,
                        true,
                        true


                )
        );
    }


}
