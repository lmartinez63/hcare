package com.landl.hcare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.DataTableResult;
import com.landl.hcare.entity.MedAppHeaderView;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.Patient;
import com.landl.hcare.repository.MedAppHeaderViewRepository;
import com.landl.hcare.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BrowserServiceImpl implements BrowserService {

    @Autowired
    MedAppHeaderViewRepository medAppHeaderViewRepository;
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
    MedicalAppointmentService medicalAppointmentService;

    @PersistenceContext
    EntityManager entityManager;


    public List<MedAppHeaderView> findAllMedAppHeaderView() throws Exception {
        return medAppHeaderViewRepository.findAll();
    }

    public DataTableResult buildDataTableObject(String queryString, Map<String,Object> browseParameters) throws Exception {
        DataTableResult dataTableResult = new DataTableResult();
        List result = null;
        switch (queryString){
            case "allMedAppHeaderView":
                result = medAppHeaderViewRepository.findByLmatLanguageAndLmasLanguageOrderByMaDateAppointmentDesc("ES_ES","ES_ES");
                break;
            case "allMedicalAreas":
                result = medicalAreaService.findAll();
                break;
            case "allPatients":
                result = patientService.findAll();
                break;
            case "medicalAppointmentsToday":
                result = medicalAppointmentService.findByToday();
                break;
            case "medicalAppointmentsByPatient":
                //result = medicalAppointmentService.findByDocumentNumber(entityId);
                break;
            default:
                result = getQuery(queryString,browseParameters);
                break;

        }
        dataTableResult.setData(result);
        dataTableResult.setDraw(1);

        return dataTableResult;
    }

    public List<ObjectNode> getQuery(String query, Map<String,Object> browseParameters) throws Exception{

        Query q = entityManager.createNativeQuery(query, Tuple.class);
        Iterator<Map.Entry<String, Object>> parameterIterator = browseParameters.entrySet().iterator();
        while (parameterIterator.hasNext()) {
            Map.Entry<String, Object> pair = parameterIterator.next();
            q.setParameter(pair.getKey(),pair.getValue());
        }

        List<Tuple> results = q.getResultList();

        List<ObjectNode> json = _toJson(results);

        return json;
    }

    private static List<ObjectNode> _toJson(List<Tuple> results) throws Exception{

        List<ObjectNode> json = new ArrayList<ObjectNode>();

        ObjectMapper mapper = new ObjectMapper();

        for (Tuple t : results)
        {
            List<TupleElement<?>> cols = t.getElements();

            ObjectNode one = mapper.createObjectNode();

            for (TupleElement col : cols)
            {
                Object tObject = t.get(col.getAlias());
                if(tObject == null){
                    one.putNull(col.getAlias());
                } else if(tObject instanceof String){
                    one.put(col.getAlias(), (String)t.get(col.getAlias()));
                } else if (tObject instanceof BigInteger){
                    one.put(col.getAlias(), (BigInteger)t.get(col.getAlias()));
                } else if (tObject instanceof Integer){
                    one.put(col.getAlias(), (Integer) t.get(col.getAlias()));
                } else if (tObject instanceof Boolean){
                    one.put(col.getAlias(), (Boolean) t.get(col.getAlias()));
                } else if (tObject instanceof Timestamp){
                    //TODO Setup parent
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Timestamp timestamp = (Timestamp) t.get(col.getAlias());
                    String timestampFormated = format.format(timestamp.getTime());
                    one.put(col.getAlias(), timestampFormated);
                } else {
                    throw new Exception("Tipe not supported");
                }
            }

            json.add(one);
        }

        return json;
    }
}
