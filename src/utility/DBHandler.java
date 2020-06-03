package utility;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Task;


public class DBHandler {
  /*
  DAO に該当するクラス。
  controller (jsp) から 要求を受け DB からデータを取得し、
  java のオブジェクトに変換、これを戻り値として返す
  task に特化しているため、 各メソッドも static で十分
   */

  private static final String PERSISTENCE_UNIT_NAME = "tasklist";
  private static final EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

  public static List<Task> getTasks(int page, int maximumElementPerPage){
    List<Task> result = ENTITY_MANAGER.createNamedQuery(Task.GET_ALL_TASKS, Task.class)
                               .setFirstResult(maximumElementPerPage * (page - 1))
                               .setMaxResults(maximumElementPerPage)
                               .getResultList();
    return result;
  }

  public static long getTotalCountOfTasks(){
    long result = (long)ENTITY_MANAGER.createNamedQuery(Task.GET_TASKS_COUNT, Long.class)
                                  .getSingleResult();

    return result;
  }

}