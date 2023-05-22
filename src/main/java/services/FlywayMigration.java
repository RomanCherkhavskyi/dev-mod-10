package services;

import org.flywaydb.core.Flyway;

public class FlywayMigration {

    public void flywayMigration(){

        Flyway flyway = Flyway
                .configure()
                .baselineOnMigrate(true)
                .dataSource("jdbc:h2:C:/goit/dev/dev-mod-10/mydb","","")
                .load();

        flyway.migrate();

    }
}