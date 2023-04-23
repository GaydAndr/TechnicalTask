package com.example.technicalTask.controller;

import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.exception.PatientAlreadyExistException;
import com.example.technicalTask.exception.PatientNotFoundException;
import com.example.technicalTask.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/getall")
    public ResponseEntity fetchAllPatients(){
        try{
//            return new ResponseEntity<List<PatientEntity>>(patientService.getAllPatient(), HttpStatus.OK);
            return ResponseEntity.ok(patientService.getAllPatient());
        }catch (Exception exception){
//            return new ResponseEntity<List<PatientEntity>>( HttpStatus.BAD_REQUEST );
            return ResponseEntity.badRequest().body("Виникла помилка" + exception);
        }

    };

    @PostMapping("/add-patient")
    public ResponseEntity addPatient(@RequestBody PatientEntity patient){
        try{
//            patientService.addPatient(patient);
            return ResponseEntity.ok(patientService.addPatient(patient));
        } catch (PatientAlreadyExistException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Виникла помилка" + exception);
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
            return ResponseEntity.badRequest().body("щось не так"+ e);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity updatePatient(@RequestBody Object newpatient){
        try {
            patientService.upDate(newpatient);
            return ResponseEntity.ok(patientService.upDate(newpatient));
        } catch (PatientNotFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("щось не так" + e);
        }
    }

    @PatchMapping("/add-comment")
    public ResponseEntity addCommentForPatient(@RequestBody String newpatient){
        try {
            patientService.addComment(newpatient);
            return ResponseEntity.ok(patientService.addComment(newpatient));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("щось не так " + e);
        }
    }

}
