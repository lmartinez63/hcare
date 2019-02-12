package com.landl.hcare.controller;

import com.landl.hcare.component.CustomProcessSelector;
import com.landl.hcare.config.TokenProvider;
import com.landl.hcare.entity.*;
import com.landl.hcare.component.CustomProcess;
import com.landl.hcare.component.ProcessStatus;
import com.landl.hcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ContentController {

    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private EmailTemplateService emailTemplateService;


    @Autowired
    private EmailService emailService;

    @Autowired
    private PageService pageService;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MedicalAreaService medicalAreaService;

    @Autowired
    private UserService userService;

    @Autowired
    private BrowserService browserService;

    @Autowired
    private DataTableService dataTableService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private CustomProcessSelector customProcessSelector;

    //@PreAuthorize("hasRole('SECRETARY') or hasRole('DOCTOR') or hasRole('ADMIN')")
    /*
    @GetMapping("/getContent")
    public Content getContent(@PathVariable String pageCode) throws Exception {
    */
    @PostMapping("/getContent")
    public Content getContent(@RequestBody Map<String,Object> requestMap) throws Exception{
        //Credential from Authorization
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //Metadata
        MetadataContent metadataContent = new MetadataContent();
        String requestPage = requestMap.get("requestPage").toString();
        String processName = requestMap.get("processName").toString();
        Map requestDataMap = (Map)requestMap.get("data");
        requestDataMap.put("userAuthenticated",new UserAuthenticated((User) authentication.getPrincipal()));
        Page page = pageService.findPageSectionAndFieldsByPageCodeAndUserName(requestPage,username);
        //Verify if use is authorizated
        //pageService.verifyIfCurrentUserIsAuthorizated(page,dataContent.getDataMap());
        if (page == null){
            throw new AccessDeniedException("You are not allowed to view this page");
        }
        Content content = new Content();
        content.setMetadataContent(metadataContent);
        //Choose the customProcess to get Data
        CustomProcess customProcess = customProcessSelector.getCustomProcessClass(processName);
        //Process data and store in customProcess.resultMap
        customProcess.executeProcess(requestDataMap);
        if(!customProcess.getProcessStatus().equals(ProcessStatus.SUCCESS) && !customProcess.getProcessStatus().equals(ProcessStatus.OK_WITH_DATA_ERRORS)){
            throw new Exception("Process "+customProcess.getClass().getName()+" has not ended correctly "+customProcess.getProcessStatus());
        } else {
            DataContent dataContent = new DataContent();
            dataContent.setDataMap(customProcess.getResultMap());
            pageService.processFields(page, dataContent.getDataMap());
            metadataContent.setPage(page);
            content.setDataContent(dataContent);
        }
        return content;
    }

    @PostMapping("/getBrowseContent")
    public Browse getBrowseContent(@RequestBody Map<String,Object> requestMap) throws Exception{
        Map requestDataMap = (Map)requestMap.get("data");
        String browseName = requestDataMap.get("browseName").toString();
        Browse browse = new Browse();
        browse.setMetaDataBrowse(dataTableService.findByDataTableCode(browseName));
        browse.setDataBrowse(browserService.buildDataTableObject(browseName));
        return browse;
    }

    @GetMapping("/loadProperties")
    public Map loadProperties() throws Exception{
        //Credential from Authorization
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //Metadata
        Map resultMap = propertyService.getPropertiesGroupByModule();
        return resultMap;
    }

    @GetMapping("/getMedicalAreas")
    public List<MedicalArea> getMedicalAreas() throws Exception{
        return medicalAreaService.findAll();
    }

    @GetMapping("/getDoctors")
    public List<UserProfile> getDoctors() throws Exception{
        return userService.findByRole("DOCTOR");
    }
}
