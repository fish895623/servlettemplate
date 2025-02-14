package org.example.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
  private static final Properties properties = new Properties();

  static {
    try (InputStream input = Prop.class.getClassLoader().getResourceAsStream("config.properties")) {
      if (input != null) {
        properties.load(input);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String get(String key) {
    return properties.getProperty(key);
  }
}
