package com.landl.hcare.component;

import com.landl.hcare.entity.UserAuthenticated;
import com.landl.hcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public abstract class  CustomProcess {

    @Autowired
    MedicalAppointmentService medicalAppointmentService;

    @Autowired
    PatientService patientService;

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
