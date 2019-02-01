package com.landl.hcare.service;

import com.landl.hcare.entity.UserProfile;
import com.landl.hcare.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;


    public UserProfile findByUsername(String username){
        return userProfileRepository.findByUsername(username);
    }

    //TODO put on cache
    public UserProfile getPageAndFieldsAssigned(String username){
        UserProfile userProfile = findByUsername(username);
        List<Object[]> pages = userProfileRepository.getPageAndSectionsAssigned(userProfile.getUsername());
        //Map<Integer, BigDecimal> dpaMap = adjustments.stream().collect(Collectors.toMap(a -> (Integer)a[0], a -> BigDecimal.valueOf((Float) a[1])));
        Map<String,List<String>> pageSectionMap = new HashMap<>();
        for(Object[] page:pages){
            String pageCode = (String) page[0];
            String sectionCode = (String) page[1];
            if(pageSectionMap.containsKey(pageCode)){
                pageSectionMap.get(pageCode).add(sectionCode);
            } else {
                List<String> newSectionList = new ArrayList<String>();
                newSectionList.add(sectionCode);
                pageSectionMap.put(pageCode,newSectionList);
            }
        }
        userProfile.setPageSectionMap(pageSectionMap);
        return userProfile;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = findByUsername(username);
        if(userProfile == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userProfile.getUsername(), userProfile.getPassword(), getAuthority(userProfile));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserProfile userProfile) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userProfile.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<UserProfile> findAll() {
        List<UserProfile> list = new ArrayList<>();
        userProfileRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<UserProfile> findByRole(String roleName) {
        List<UserProfile> list = new ArrayList<>();
        userProfileRepository.findByRoleName(roleName).iterator().forEachRemaining(list::add);
        return list;
    };

    @Override
    public void delete(long id) {
        userProfileRepository.deleteById(id);
    }

    @Override
    public UserProfile findOne(String username) {
        return userProfileRepository.findByUsername(username);
    }

    @Override
    public UserProfile findById(Long id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        userProfile.setPassword(bcryptEncoder.encode(userProfile.getPassword()));
        return userProfileRepository.save(userProfile);
    }
}
