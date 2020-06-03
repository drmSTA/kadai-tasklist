package utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManager4TaskList {
  private static final String PERSISTENCE_UNIT_NAME = "tasklist";
  private static EntityManagerFactory entityManagerFactory;

  public static EntityManager getEntityManager(){
    if(entityManagerFactory == null){
      entityManagerFactory =  Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    return entityManagerFactory.createEntityManager();
  }
}
