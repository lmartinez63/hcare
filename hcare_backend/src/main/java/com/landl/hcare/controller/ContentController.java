package com.landl.hcare.controller;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.component.CustomProcessSelector;
import com.landl.hcare.config.TokenProvider;
import com.landl.hcare.entity.*;
import com.landl.hcare.component.CustomProcess;
import com.landl.hcare.component.ProcessStatus;
import com.landl.hcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
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
    private RoleService roleService;

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
    public Content getContent(@RequestBody Map<String,Object> requestMap){
        try {
        //Credential from Authorization
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //Metadata
        MetadataContent metadataContent = new MetadataContent();
        String requestPage = requestMap.get("requestPage").toString();
        String processName = requestMap.get("processName").toString();
        Map requestDataMap = (Map)requestMap.get("data");
        requestDataMap.put("userAuthenticated",new UserAuthenticated((User) authentication.getPrincipal()));
        requestDataMap.put("userProfileAuthenticated",userService.findByUsername(username));
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
            customProcess.getResultMap().put("userProfileAuthenticated",userService.findByUsername(username));
            dataContent.setDataMap(customProcess.getResultMap());
            pageService.processFields(page, dataContent.getDataMap());
            metadataContent.setPage(page);
            content.setDataContent(dataContent);
        }
        return content;
        }
        catch (Exception exc) {
            //TODO Add Log4j2 and register this error
            exc.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
    }

    @PostMapping("/uploadAttachment")
    public Attachment uploadAttachment(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName, @RequestParam("entity") String entity, @RequestParam("entityId") Long entityId) throws Exception{
        //attachment.setEntity();
        //attachment.setEntityId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Attachment attachment = new Attachment(fileName, entity, entityId);
        attachment.setContent(file.getBytes());
        attachment.setContentType(file.getContentType());
        return attachmentService.save(attachment);
    }

    @GetMapping(value = "/downloadAttachment/{attachmentId}")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Long attachmentId) throws Exception {

        Attachment attachment = attachmentService.findById(attachmentId).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        byte[] output = attachment.getContent();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(attachment.getContentType()));
        responseHeaders.setContentLength(output.length);
        responseHeaders.set("Content-disposition", "attachment; filename="+attachment.getFileName());

        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/getBrowseContent")
    public Browse getBrowseContent(@RequestBody Map<String,Object> requestMap){
        try {
            //Credential from Authorization
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Map requestDataMap = (Map) requestMap.get("data");
            String browseName = UtilityTools.isNull((String) requestDataMap.get("browseName").toString());
            Map<String, Object> browseParameters = (Map<String, Object>) requestDataMap.get("browseParameters");
            Browse browse = new Browse();
            DataTable dataTable = dataTableService.findByDataTableCodeAndUsername(browseName, username);
            if (dataTable.getDataColumns() == null || dataTable.getDataColumns().size() < 1) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Insufficient Privileges or Data Columns are not defined", null);
            }
            browse.setMetaDataBrowse(dataTable);
            browse.setDataBrowse(browserService.buildDataTableObject(dataTable.getQueryString(), browseParameters));
            return browse;
        }
        catch (Exception exc) {
            //TODO Add Log4j2 and register this error
            exc.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
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
    @GetMapping("/getRoles")
    public List<Role> getRoles() throws Exception{
        return roleService.findAll();
    }
}
