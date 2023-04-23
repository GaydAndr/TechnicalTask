package com.example.technicalTask.service;

import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.exception.PatientAlreadyExistException;
import com.example.technicalTask.exception.PatientNotFoundException;
import com.example.technicalTask.repository.PatientRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepo patientRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ObjectMapper objectMapper;

    public List<PatientEntity> getAllPatient(){
        return patientRepo.findAll();
    }

    public PatientEntity addPatient(PatientEntity patient) throws PatientAlreadyExistException {
       if(patientRepo.findPatientEntityByEmail(patient.getEmail()).isPresent()){
            throw new PatientAlreadyExistException("Пацієнт з таким E-mail вже існує");
			}
        PatientEntity newpatient = patientRepo.insert(patient);

        System.out.println(newpatient);
        return newpatient;
    }

    public Optional<PatientEntity> getOne(String id) throws PatientNotFoundException{
        PatientEntity patient = patientRepo.findById(id).get();
                if (patient==null){
            throw new PatientNotFoundException("Пацієнта не знайдено");
        }
        return patientRepo.findById(id);
    }

    public String delete(String id){
        patientRepo.deleteById(String.valueOf(id));
        return id;
    }



    public Object upDate(Object newData) throws PatientNotFoundException{
        PatientEntity patient = new PatientEntity();
        ObjectMapper mapper = new ObjectMapper();
        mapper.convertValue(newData, PatientEntity.class);
        System.out.println(mapper);
//        patient = patientRepo.findById(newData);
//        if (patient==null){
//            throw new PatientNotFoundException("Пацієнта не знайдено");
//        }
//
//        Query query =new Query();
//        query.addCriteria(Criteria.where("id").is(newData.));
//
//        Update update = new Update();
//        update.set("age",newData.age);
//
//        PatientEntity newPatient = mongoOperations.findAndModify(
//                query,update,
//                new FindAndModifyOptions().returnNew(true), PatientEntity.class
//        );
////        System.out.println( newPatient.getComments();
//
//        return Optional.of(newPatient);
        return newData;
    }

    public String addComment(String newComment){

//        Query query =new Query();
//        query.addCriteria(Criteria.where("id").is(newComment.getId()));
//
//        Update update = new Update();
//        update.push("comments",newComment.getComments());
//
//        PatientEntity newPatient = mongoOperations.findAndModify(
//                query,update,
//                new FindAndModifyOptions().returnNew(true), PatientEntity.class
//        );
//        System.out.println(newComment.getComments());

        return newComment;
    }
}
