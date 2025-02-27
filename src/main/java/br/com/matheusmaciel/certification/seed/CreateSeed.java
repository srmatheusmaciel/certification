package br.com.matheusmaciel.certification.seed;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateSeed {
  private final JdbcTemplate jdbcTemplate;

  public CreateSeed(DataSource datasource) { // Use 'datasource' minúsculo
    this.jdbcTemplate = new JdbcTemplate(datasource);
  }

  public static void main(String[] args) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/db_certification");
    dataSource.setUsername("postgres");
    dataSource.setPassword("postgres");

    CreateSeed createSeed = new CreateSeed(dataSource);
    createSeed.run(args);
  }

  public void run(String[] args) {
    executeSqlFile("src/main/resources/create.sql");
  }

  private void executeSqlFile(String filePath) {
    try {
      String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));
      jdbcTemplate.execute(sqlScript);
      System.out.println("Script seed executed successfully.");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error: " + e.getMessage());
    }
  }
}
