package com.example.technicalTask.controller;

import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.exception.PatientAlreadyExistException;
import com.example.technicalTask.exception.PatientNotFoundException;
import com.example.technicalTask.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/all")
    public List<PatientEntity> fetchAllPatients(){
        return patientService.getAllPatient();
    };

    @PostMapping("/")
    public ResponseEntity<String> addPatient(@RequestBody PatientEntity patient)
    {
        try{
            patientService.addPatient(patient);
            return ResponseEntity.ok("Пацієнта додано");
        } catch (PatientAlreadyExistException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Виникла помилка");
        }
    }

    @GetMapping("/id")
    public ResponseEntity<String> getOnePatient(@RequestBody PatientEntity patientid){
        try {
            patientService.getOne(patientid.getId());
            return ResponseEntity.ok("додано");
        }catch (PatientNotFoundException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable String id){
        try {
            return ResponseEntity.ok(patientService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("щось не так");
        }
    }

    @PatchMapping("/update")
    public ResponseEntity updatePatient(@RequestBody PatientEntity newpatient){
        try {
            patientService.upDate(newpatient);
            return ResponseEntity.ok("updateted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("щось не так");
        }
    }

}
