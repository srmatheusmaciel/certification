package br.com.matheusmaciel.certification.modules.students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCertificationsEntity {
  private UUID id;
  private UUID certificationId;
  private UUID studentId;
  private UUID questionId;
  private UUID answerId;
  private Boolean isCorrect;
}
