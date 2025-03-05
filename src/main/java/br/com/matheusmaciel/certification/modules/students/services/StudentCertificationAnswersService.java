package br.com.matheusmaciel.certification.modules.students.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusmaciel.certification.modules.questions.entities.QuestionEntity;
import br.com.matheusmaciel.certification.modules.questions.repositories.QuestionRepository;
import br.com.matheusmaciel.certification.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.matheusmaciel.certification.modules.students.repositories.CertificationStudentRepository;


@Service
public class StudentCertificationAnswersService {


  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private VerifyIfHasCertificationService verifyIfHasCertificationService;


  public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO ){
   

    List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswerDTO.getTechnology());

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

    });
   
    return studentCertificationAnswerDTO;




  }
}
