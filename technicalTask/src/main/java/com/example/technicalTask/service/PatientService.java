package com.example.technicalTask.service;

import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepo patientRepo;
    public List<PatientEntity> getAllPatient(){
        return patientRepo.findAll();
    }

    public PatientEntity addPatient(PatientEntity patient){
       if(patientRepo.findPatientEntityByEmail(patient.getEmail()).isPresent()){
            return null ;
			}
            patientRepo.insert(patient);
//            patientRepo.save(patient);
        return patient;
    }

    public Optional<PatientEntity> getOne(String id){
//        PatientEntity patient = patientRepo.findById(_id).get();
        //        if (patient==null){
//            return null;
//        }
//        return test;
        return patientRepo.findById(id);
    }

    public String delete(String id){
        patientRepo.deleteById(String.valueOf(id));
        return id;
    }



//    public Optional<PatientEntity> upDate(PatientEntity newpatient){
//        Optional<PatientEntity> patient = patientRepo.findById(newpatient.getId());
//
//        return patientRepo.
//    }
}
