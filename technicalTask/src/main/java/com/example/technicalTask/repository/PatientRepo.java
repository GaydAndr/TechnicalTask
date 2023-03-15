package com.example.technicalTask.repository;

import com.example.technicalTask.entity.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientRepo extends MongoRepository<PatientEntity, String> {
    Optional<PatientEntity> findPatientEntityByEmail(String email);

//    Optional<PatientEntity> findById(String id);
//    Optional<PatientEntity> findPatientEntityById(String id);
}
