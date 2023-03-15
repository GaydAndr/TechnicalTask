package com.example.technicalTask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
//@Document(collation = "patientList")
@Document
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String age;
    private Gender gender;
    private Address address;
    private List<Comments> comments;


//    public PatientEntity(String firstName,
//                         String lastName,
//                         String email,
//                         String age,
//                         Gender gender,
//                         Address address,
//                         List<Comments> comments) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.age = age;
//        this.gender = gender;
//        this.address = address;
//        this.comments = comments;
//    }

}
