package org.example.demo2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.io.IOException;

@WebListener
public class MigrationListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      DatabaseManager.migrate();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
