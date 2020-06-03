package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(
          name = "getAllTasks",
          query = "SELECT m FROM Task AS m ORDER BY m.id DESC"
          ),
  @NamedQuery(
      name = "getTasksCount",
      query = "SELECT COUNT(m) FROM Task AS m"
      )
})

@Table(name = "tasks")
public class Task {
  public static final String GET_ALL_TASKS = "getAllTasks";
  public static final String GET_TASKS_COUNT = "getTasksCount";
  public static final int CONTENT_LENGTH_UL = 255;
  @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    Content data が取りうる length については、必要に応じて見直すこと
    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Timestamp updatedAt;

    // 新規エントリ作成時に利用されるコンストラクタ　
    //（時間系の変数のみ呼び出し時で初期化、tasklist の内容はカラ）
    public Task() {
      this("");
    }

    public Task(String content) {
      Timestamp time = new Timestamp(System.currentTimeMillis());

      this.content = content;
      this.createdAt = time;
      this.updatedAt = time;
    }


    //===========================================
    // getter / setter
    //===========================================
    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public Timestamp getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
      this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
      return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
      this.updatedAt = updatedAt;
    }

    // 既存のエントリーの修正や更新時に呼び出されるメソッド
    public void setUpdatedAtPresent(){
      this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}