package br.com.matheusmaciel.certification.modules.students.services;

import org.springframework.stereotype.Service;

import br.com.matheusmaciel.certification.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationService {
  
  public boolean execute(VerifyHasCertificationDTO dto){
    if(dto.getEmail().equals("Matheus@gmail.com") && dto.getTechnology().equals("Java")){
      return true;
    }
    return false;
  }
}
