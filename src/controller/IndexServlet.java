package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import utility.DBHandler;
import utility.WB;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(WB.PATH_INDEX)
public class IndexServlet extends HttpServlet {
  private static final long serialVersionUID = 20200604L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int page = 1;
    try {
        page = Integer.parseInt(request.getParameter(WB.KEY_PAGE));
    } catch(NumberFormatException e) {}

    // DB 上にある Task を VALUE_PAGE_UL を上限に取得する
    List<Task> tasks = DBHandler.getTasks(page, WB.VALUE_PAGE_UL);

    // DB 上にある Task 数を取得する
    long tasksCount = DBHandler.getTotalCountOfTasks();

    // flush 処理 (session scope -> response 設定へ移動する)
    if(request.getSession().getAttribute(WB.KEY_FLUSH) != null) {
      request.setAttribute(WB.KEY_FLUSH, request.getSession().getAttribute(WB.KEY_FLUSH));
      request.getSession().removeAttribute(WB.KEY_FLUSH);
    }

    // 必要な情報を設定、index ページへ転送
    request.setAttribute(WB.KEY_TASKS, tasks);
    request.setAttribute(WB.KEY_TASKS_COUNT, tasksCount);
    request.setAttribute(WB.KEY_PAGE_UL, WB.VALUE_PAGE_UL);
    request.setAttribute(WB.KEY_PAGE, page);
    request.getRequestDispatcher(WB.PATH_INDEX_JSP).forward(request, response);
  }
}
