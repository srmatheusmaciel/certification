package br.com.matheusmaciel.certification.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmaciel.certification.modules.students.dto.VerifyHasCertificationDTO;
import br.com.matheusmaciel.certification.modules.students.services.VerifyIfHasCertificationService;


@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationService verifyIfHasCertificationService;
  
  @PostMapping("/verify-if-has-certification")
  public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {

    var result = verifyIfHasCertificationService.execute(verifyHasCertificationDTO);


    if(result){
      return "Has certification";
    }
    
    return "Does not have certification";
  }
}
