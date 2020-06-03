package utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBHandler {
  // DAO に該当するクラス。
  //controller (jsp) から 要求を受け DB からデータを取得し、
  //java のオブジェクトに変換、これを戻り値として返す

  private static final String PERSISTENCE_UNIT_NAME = "tasklist";
  private static EntityManagerFactory entityManagerFactory;

  public static EntityManager createEntityManager() {
      return _getEntityManagerFactory().createEntityManager();
  }

  private static EntityManagerFactory _getEntityManagerFactory() {
    if(entityManagerFactory == null) {
      entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }
    return entityManagerFactory;
  }

}