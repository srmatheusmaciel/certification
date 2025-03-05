# Course Certification Management API

## 📘 Descrição do Projeto

Uma API robusta para gerenciamento de certificações de cursos, permitindo o cadastro de questões, tecnologias e estudantes, construída com Spring Boot e arquitetura moderna.

## 🚀 Tecnologias Utilizadas

- **Backend**: 
  - Java 21
  - Spring Boot 3.4.3
  - Spring Web
  - Spring Data JPA
    
- **Banco de Dados**:
  - PostgreSQL
    
- **Ferramentas de Desenvolvimento**:
  - Maven
  - Lombok
  - Spring DevTools

## ✨ Funcionalidades Principais

- Cadastro de Questões
- Gerenciamento de Tecnologias
- Registro de Estudantes
- Geração de Certificações

## 📂 Estrutura do Projeto

```
certification/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/matheusmaciel/
│   │   │       └── certification/
│   │   │           ├── modules/
│   │   │           │   ├── questions/
│   │   │           │   ├── students/
│   │   │           │   
│   │   │           │       
│   │   │           │       
│   │   │           │     
│   │   │           │       
│   │   │           └── CertificationApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│
├── pom.xml
└── README.md
```

## 🔧 Configuração e Instalação

### Pré-requisitos
- JDK 21
- Maven
- PostgreSQL

### Passos de Instalação
1. Clone o repositório
```bash
git clone https://github.com/srmatheusmaciel/certification.git
```

2. Configure o banco de dados no `application.properties`
```properties
spring.application.name=certification
server.port=8085

# Database PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/db_certification
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

3. Instale as dependências e compile
```bash
mvn clean install
```

4. Execute a aplicação
```bash
mvn spring-boot:run
```

## 🛠️ Principais Componentes

- **Controllers**: Gerenciam endpoints e rotas
- **Entities**: Modelos de dados JPA
- **Repositories**: Interfaces de acesso a dados
- **Services**: Lógica de negócio

## 📦 Dependências Principais

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- Spring DevTools

## 🧪 Testes

Execute os testes unitários com:
```bash
mvn test
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie sua feature branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request


## 👨‍💻 Autor

Matheus Maciel - You can find me on [LinkedIn](https://www.linkedin.com/srmatheusmaciel) or [GitHub](https://github.com/srmatheusmaciel).

## 🚨 Próximos Passos

- [ ] Implementar autenticação JWT
- [ ] Adicionar validações de entrada
- [ ] Criar documentação completa da API com Swagger
- [ ] Implementar testes de integração
