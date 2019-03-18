package com.landl.hcare.component;

import com.landl.hcare.entity.UserAuthenticated;
import com.landl.hcare.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class  CustomProcess {

    @Autowired
    MedicalAppointmentService medicalAppointmentService;

    @Autowired
    MedicalAreaService medicalAreaService;

    @Autowired
    PatientService patientService;

    @Autowired
    PageService pageService;

    @Autowired
    SectionService sectionService;

    @Autowired
    FieldService fieldService;

    @Autowired
    LabelService labelService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    MedicalHistoryService medicalHistoryService;

    @Autowired
    AttachmentService attachmentService;

    private ProcessStatus processStatus;

    private UserAuthenticated userAuthenticated;

    private Map<String,Object> resultMap  = new HashMap<String,Object>();

    public CustomProcess() {
        this.processStatus = ProcessStatus.INITIALIZED;
    }

    public void executeProcess(Map<String, Object> requestMap) throws Exception{
        //TODO set Logs in the benning and in the end
        try{
            this.setProcessStatus(ProcessStatus.RUNNING);
            setUserAuthenticated((UserAuthenticated)requestMap.get("setUserAuthenticated"));
            this.executeCustomProcess(requestMap);
            this.setProcessStatus(ProcessStatus.SUCCESS);
        } catch (Exception e){
            this.setProcessStatus(ProcessStatus.FAILED);
            throw e;
        }
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public Map getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map resultMap) {
        this.resultMap = resultMap;
    }

    public void addDataToResultMap(String objectName,Object objectResult) {
        this.resultMap.put(objectName,objectResult);
    }

    public UserAuthenticated getUserAuthenticated() {
        return userAuthenticated;
    }

    public void setUserAuthenticated(UserAuthenticated userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }
}
