package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveUserProfileInfo")
public class RetrieveUserProfileInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final UserProfile userProfileRequest = mapper.convertValue(requestMap.get("userProfile"), UserProfile.class);
        UserProfile userProfile;
        if(userProfileRequest.getId() != null) {
            userProfile = userService.findById(userProfileRequest.getId());
        } else {
            userProfile = userService.createUserProfile();
        }
        addDataToResultMap("userProfile",userProfile);
    }
}
