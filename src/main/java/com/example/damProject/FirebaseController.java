package com.example.damProject;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FirebaseController {

  @PostMapping("/add")
  public String data(@RequestParam String key, @RequestParam String value) {

    try {
      Firestore db = FirestoreClient.getFirestore();

      Map<String, Object> data = new HashMap<>();
      data.put("value", value);

      db.collection("damCollection").document(key).set(data);

      return "Data keep succesfully";

    } catch (Exception e) {
      e.printStackTrace();

      return "Error adding data " + e.getMessage();
    }
  }

  @GetMapping("/getData")
  public String getData(@RequestParam String key) {
    try {
      Firestore db = FirestoreClient.getFirestore();

      var collection = db.collection("damCollection").document(key).get().get();

      if (collection.exists()) {
        return "data" + collection.getData();
      } else {
        return "Not data found with key: " + key;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "Error: " + e.getMessage();
    }
  }

  @GetMapping("/papas")
  public String papas() {
    return "Papas";
  }
}
