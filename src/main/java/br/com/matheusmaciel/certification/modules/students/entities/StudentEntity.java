package br.com.matheusmaciel.certification.modules.students.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentEntity {
  private UUID id;
  private String name;
  private String email;
  private List<CertificationStudentEntity> certificationStudentEntity;
}
