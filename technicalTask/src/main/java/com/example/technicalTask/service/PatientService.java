package com.example.technicalTask.service;

import com.example.technicalTask.entity.Comments;
import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.exception.PatientAlreadyExistException;
import com.example.technicalTask.exception.PatientNotFoundException;
import com.example.technicalTask.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public List<PatientEntity> getAllPatient(){
        return patientRepo.findAll();
    }

    public PatientEntity addPatient(PatientEntity patient) throws PatientAlreadyExistException {
       if(patientRepo.findPatientEntityByEmail(patient.getEmail()).isPresent()){
            throw new PatientAlreadyExistException("Пацієнт з таким E-mail вже існує");
			}
            patientRepo.insert(patient);
//            patientRepo.save(patient);
        System.out.println(patient);
        return patient;
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



    public Optional<PatientEntity> upDate(PatientEntity newData) throws PatientNotFoundException{
        PatientEntity patient = patientRepo.findById(newData.getId()).get();
        if (patient==null){
            throw new PatientNotFoundException("Пацієнта не знайдено");
        }

        Query query =new Query();
        query.addCriteria(Criteria.where("id").is(newData.getId()));

        Update update = new Update();
        update.set("age",newData.getAge());

        PatientEntity newPatient = mongoOperations.findAndModify(
                query,update,
                new FindAndModifyOptions().returnNew(true), PatientEntity.class
        );
//        System.out.println( newPatient.getComments();

        return Optional.of(newPatient);
    }

    public Optional<PatientEntity> addComment(PatientEntity newComment){

        Query query =new Query();
        query.addCriteria(Criteria.where("id").is(newComment.getId()));

        Update update = new Update();
        update.push("comments",newComment.getComments());

        PatientEntity newPatient = mongoOperations.findAndModify(
                query,update,
                new FindAndModifyOptions().returnNew(true), PatientEntity.class
        );
//        System.out.println(newComment.getComments());

        return Optional.of(newPatient);
    }
}
