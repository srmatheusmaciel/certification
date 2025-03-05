package br.com.matheusmaciel.certification.modules.students.services;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusmaciel.certification.modules.questions.entities.QuestionEntity;
import br.com.matheusmaciel.certification.modules.questions.repositories.QuestionRepository;
import br.com.matheusmaciel.certification.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.matheusmaciel.certification.modules.students.entities.AnswerCertificationsEntity;
import br.com.matheusmaciel.certification.modules.students.entities.CertificationStudentEntity;
import br.com.matheusmaciel.certification.modules.students.entities.StudentEntity;
import br.com.matheusmaciel.certification.modules.students.repositories.CertificationStudentRepository;
import br.com.matheusmaciel.certification.modules.students.repositories.StudentRepository;


@Service
public class StudentCertificationAnswersService {

  @Autowired
  private StudentRepository studentRepository;


  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private VerifyIfHasCertificationService verifyIfHasCertificationService;


  public CertificationStudentEntity execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO ){
   

    List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswerDTO.getTechnology());

    List<AnswerCertificationsEntity> answerCertifications = new ArrayList<>();

    studentCertificationAnswerDTO.getQuestionAnswers()
    .stream().forEach(questionAnswer -> {
      var question = questionsEntity.stream()
      .filter(q -> q.getId().equals(questionAnswer.getQuestionId()))
      .findFirst().get();

      var findCorrectAlternative = question.getAlternatives().stream()
      .filter(alternative -> alternative.isCorrect())
      .findFirst().get();

      if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId())){
        questionAnswer.setCorrect(true);
      }else{
        questionAnswer.setCorrect(false);
      }
    var answerCertificationsEntity = AnswerCertificationsEntity.builder()
    .answerId(questionAnswer.getAlternativeId())
    .questionId(questionAnswer.getQuestionId())
    .isCorrect(questionAnswer.isCorrect()).build();

    answerCertifications.add(answerCertificationsEntity);


    });

    var student = studentRepository.findByEmail(studentCertificationAnswerDTO.getEmail());
    UUID studentId;
    if(student.isEmpty()){
      var studentCreated = StudentEntity.builder()
      .email(studentCertificationAnswerDTO.getEmail())
      .build();

      studentCreated = studentRepository.save(studentCreated);
      studentId = studentCreated.getId();
    }else{
      studentId = student.get().getId();
    }




    CertificationStudentEntity certificationStudentEntity = 
    CertificationStudentEntity.builder()
    .technology(studentCertificationAnswerDTO.getTechnology())
    .studentId(studentId)
    .answerCertificationsEntity(answerCertifications)
    .build();

    var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

    answerCertifications.stream()
    .forEach(answerCertification -> {
      answerCertification.setCertificationId(certificationStudentEntity.getId());
      answerCertification.setCertificationStudentEntity(certificationStudentEntity);
    });

    certificationStudentEntity.setAnswerCertificationsEntity(answerCertifications);

    certificationStudentRepository.save(certificationStudentEntity);
   
    return certificationStudentCreated;




  }
}
