package com.example.technicalTask.controller;

import com.example.technicalTask.entity.PatientEntity;
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

    @GetMapping("/id")
    public ResponseEntity<String> getOnePatient(@RequestBody PatientEntity patientid){
        try {
            patientService.getOne(patientid.getId());
            return ResponseEntity.ok("додано");
//        } catch (UserAlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

    }

    @PostMapping("/")
    public PatientEntity addPatient(@RequestBody PatientEntity patient){
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable String id){
        try {
            return ResponseEntity.ok(patientService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("щось не так");
        }
    }

//    @PatchMapping("/update")
//    public Optional<PatientEntity> updatePatient(@RequestBody PatientEntity patient){
//        return patientService.upDate(patient.getId());
//    }

//    @PatchMapping
//    public ResponseEntity registration(@RequestBody PatientEntity patient){
//        try{
//            return ResponseEntity.ok("Працює(навіть)");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity getPatients(){
//        try{
//            return ResponseEntity.ok("Працює(навіть)");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
}
