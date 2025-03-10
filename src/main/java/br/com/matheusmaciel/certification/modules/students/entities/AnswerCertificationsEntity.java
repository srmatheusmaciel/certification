package br.com.matheusmaciel.certification.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "answer_certifications_students")
public class AnswerCertificationsEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "certification_id", insertable = false, updatable = false)
  @JsonBackReference
  private CertificationStudentEntity certificationStudentEntity;
 
  @Column(name = "certification_id")
  private UUID certificationId;
  
  @Column(name = "student_id", insertable = false, updatable = false)
  private UUID studentID;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private StudentEntity studentEntity;
  
  @Column(name = "question_id")
  private UUID questionId;
  
  @Column(name = "answer_id")
  private UUID answerId;
  
  @Column(name = "is_correct")
  private Boolean isCorrect;
 
  @CreationTimestamp
  private LocalDateTime createdAt;
}
