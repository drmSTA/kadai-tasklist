package utility;

public class WB {
  /*
  WB = words bank 定数をまとめておくところ。
  各クラスの定数を打ち間違えないよう、 Eclipse のオートコンプリート機能を使うために作成
  PATH_XX_JSP シリーズなんかは、ページの階層が一覧となるため、万一の再編時に便利（？）
  これが最善の方法なのかは不明、とりあえずの暫定処置。
  */

  public static final String KEY_TOKEN = "_token";
  public static final String KEY_TASKS = "tasks";
  public static final String KEY_TASKS_COUNT = "tasksCount";
  public static final String KEY_PAGE = "page";
  public static final String KEY_ERROR_MESSAGES = "errorMessages";
  public static final String KEY_FLUSH = "flush";
  public static final String KEY_PAGE_UL = "pageUL";
  public static final int     VALUE_PAGE_UL = 15;

  public static final String KEY_TASK = "task";
  public static final String KEY_TASK_ID = "id";
  public static final String KEY_CONTENT = "content";
  public static final String KEY_CREATED_DATE = "createdAt";
  public static final String KEY_UPDATED_DATE = "updatedAt";


  public static final String PATH_INDEX = "/index";

  // PATH_XX_JSP series
  public static final String PATH_INDEX_JSP   = "/WEB-INF/views/tasks/index.jsp";
  public static final String PATH_NEW_JSP     = "/WEB-INF/views/tasks/new.jsp";
  public static final String PATH_SHOW_JSP  = "/WEB-INF/views/tasks/show.jsp";
  public static final String PATH_EDIT_JSP    = "/WEB-INF/views/tasks/edit.jsp";




}
