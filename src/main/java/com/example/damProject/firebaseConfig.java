package com.example.damProject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class firebaseConfig {

  @PostConstruct
  public void intiFirebase() {
    try {
      FileInputStream token = new FileInputStream(
          "src/main/resources/damproject-650c9-firebase-adminsdk-8o2k2-68cff2840e.json");

      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(token))
          .setDatabaseUrl("https://DamProject.firebaseio.com/")
          .build();
      FirebaseApp.initializeApp(options);

      System.out.println("Firebase connect succesfully");

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Falied to connect to firebase", e);
    }
  }
}
