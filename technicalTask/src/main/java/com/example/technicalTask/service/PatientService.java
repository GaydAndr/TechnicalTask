package com.example.technicalTask.service;

import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.exception.PatientAlreadyExistException;
import com.example.technicalTask.exception.PatientNotFoundException;
import com.example.technicalTask.repository.PatientRepo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static com.mongodb.client.model.Updates.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepo patientRepo;
    public List<PatientEntity> getAllPatient(){
        return patientRepo.findAll();
    }

    public PatientEntity addPatient(PatientEntity patient) throws PatientAlreadyExistException {
       if(patientRepo.findPatientEntityByEmail(patient.getEmail()).isPresent()){
            throw new PatientAlreadyExistException("Пацієнт з таким E-mail вже існує");
			}
            patientRepo.insert(patient);
//            patientRepo.save(patient);
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



    public Optional<PatientEntity> upDate(PatientEntity newpatient){
        Optional<PatientEntity> patient = patientRepo.findById(newpatient.getId());
        Query query =new Query();
        query.addCriteria(Criteria.where("_id").is(newpatient.getId()));

        Update update = new Update();
        update.set("firstName",newpatient.getFirstName());


        return patient;
    }
}
