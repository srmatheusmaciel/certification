package br.com.matheusmaciel.certification.modules.students.services;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusmaciel.certification.modules.questions.entities.QuestionEntity;
import br.com.matheusmaciel.certification.modules.questions.repositories.QuestionRepository;
import br.com.matheusmaciel.certification.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.matheusmaciel.certification.modules.students.dto.VerifyHasCertificationDTO;
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


  public CertificationStudentEntity execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO ) throws Exception{

    var hasCertification =this.verifyIfHasCertificationService.execute(
      new VerifyHasCertificationDTO(
        studentCertificationAnswerDTO.getEmail(),
        studentCertificationAnswerDTO.getTechnology()
      
        ));

        System.out.println("Has certification? " + hasCertification); // Verifica se o valor é true ou false

        if(hasCertification){
          throw new Exception("Student already has certification");
        }
   

    List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswerDTO.getTechnology());

    List<AnswerCertificationsEntity> answerCertifications = new ArrayList<>();

    AtomicInteger correctAnswers = new AtomicInteger(0);

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
        correctAnswers.incrementAndGet();
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
    .studentEntity(student.get())
    .grade(correctAnswers.get())
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
