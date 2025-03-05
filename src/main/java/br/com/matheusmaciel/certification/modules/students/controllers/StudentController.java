package br.com.matheusmaciel.certification.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmaciel.certification.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.matheusmaciel.certification.modules.students.dto.VerifyHasCertificationDTO;
import br.com.matheusmaciel.certification.modules.students.entities.CertificationStudentEntity;
import br.com.matheusmaciel.certification.modules.students.services.StudentCertificationAnswersService;
import br.com.matheusmaciel.certification.modules.students.services.VerifyIfHasCertificationService;


@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationService verifyIfHasCertificationService;

  @Autowired
  private StudentCertificationAnswersService studentCertificationAnswersService;
  
  @PostMapping("/verify-if-has-certification")
  public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {

    var result = verifyIfHasCertificationService.execute(verifyHasCertificationDTO);


    if(result){
      return "Usuário já fez a prova";
    }
    
    return "Usuário pode fazer a prova";
  }

  @PostMapping("/certification-answer")
  public CertificationStudentEntity certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO){
    return this.studentCertificationAnswersService.execute(studentCertificationAnswerDTO);

  }

}
