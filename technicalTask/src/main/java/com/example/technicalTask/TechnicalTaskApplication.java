package com.example.technicalTask;

import com.example.technicalTask.entity.Address;
import com.example.technicalTask.entity.Comments;
import com.example.technicalTask.entity.Gender;
import com.example.technicalTask.entity.PatientEntity;
import com.example.technicalTask.repository.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TechnicalTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTaskApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(PatientRepo repo, MongoTemplate mongoTemplate){
//		return args -> {
//			Address address = new Address(
//					"Ostania",
//					"Orphanage ",
//					"128 Park Avenue"
//			);
//			String email="anyforgesdvr@gmail.com";
//
//			Comments comments = new Comments(
//					LocalDateTime.now(),
//					"comment"
//			);
//
//			Comments comments1 = new Comments(
//					LocalDateTime.now(),
//					"comment 1"
//			);
//
//			PatientEntity patient = new PatientEntity(
//					"Anya",
//					"Forger",
//					email,
//					"6",
//					Gender.FEMALE,
//					address,
//					List.of(comments , comments1)
//
//			);
//
//
//			repo.findPatientEntityByEmail(email).ifPresentOrElse(patient1 -> {
//				System.out.println(patient+"already exists");
//			}, ()->{
//				System.out.println("Inserting patient"+ patient);
//				repo.insert(patient);
//			});
//
//		};
//	}



//	private void usingMongoTeamplateAndQuery(PatientRepo repo, MongoTemplate mongoTemplate, String email, String patient){
//		Query query=new Query();
//		query.addCriteria(Criteria.where("email").is(email));
//
//		List<PatientEntity>patientEntities=mongoTemplate.find(query, PatientEntity.class);
//
//		if(patientEntities.size()>1){
//			throw new IllegalStateException("found many patients with email"+ email);
//		}
//
//		if(patientEntities.isEmpty()){
//			System.out.println("Inserting patient"+ patient);
//			repo.insert(patient);
//		}else {
//			System.out.println(patient+"already exists");
//		}
//	}

}
