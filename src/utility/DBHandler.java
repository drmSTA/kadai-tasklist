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

  public static List<Task> getTasks(int page, int maximumElementPerPage){
    EntityManager entityManager = EntityManager4TaskList.getEntityManager();

    List<Task> result = entityManager.createNamedQuery(Task.GET_ALL_TASKS, Task.class)
                               .setFirstResult(maximumElementPerPage * (page - 1))
                               .setMaxResults(maximumElementPerPage)
                               .getResultList();

    entityManager.close();
    return result;
  }

  public static long getTotalCountOfTasks(){
    EntityManager entityManager = EntityManager4TaskList.getEntityManager();

    long result = (long)entityManager.createNamedQuery(Task.GET_TASKS_COUNT, Long.class)
                                  .getSingleResult();

    entityManager.close();
    return result;
  }

  public static void addTaskIntoDB(Task task){
    EntityManager entityManager = EntityManager4TaskList.getEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(task);
    entityManager.getTransaction().commit();

    entityManager.close();
  }

  public static Task getTask(int id){
    EntityManager entityManager = EntityManager4TaskList.getEntityManager();

    Task result = (Task)entityManager.find(Task.class, id);

    entityManager.close();
    return result;
  }

}