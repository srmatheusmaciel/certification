package br.com.matheusmaciel.certification.modules.students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheusmaciel.certification.modules.students.dto.VerifyHasCertificationDTO;
import br.com.matheusmaciel.certification.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationService {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;
  
  public boolean execute(VerifyHasCertificationDTO dto){
    System.out.println("Buscando certificação no banco para email: " + dto.getEmail() 
                       + " e tecnologia: " + dto.getTechnology());

    var result = this.certificationStudentRepository
    .findByStudentEmailAndTechnology(dto.getEmail(),
                                     dto.getTechnology());
                                     
    System.out.println("Resultado encontrado: " + result.size()); // 🔥 Ver quantos registros foram encontrados
    
    return !result.isEmpty();
}
}
