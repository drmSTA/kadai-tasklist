package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import utility.DBHandler;
import utility.WB;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet(WB.PATH_SHOW)
public class ShowServlet extends HttpServlet {
  private static final long serialVersionUID = 20200604L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 該当する id の task を DB から取得
    int id = Integer.parseInt(request.getParameter(WB.KEY_TASK_ID));
    Task task = DBHandler.getTask(id);

    // request に task を設定、ページ転送
    request.setAttribute(WB.KEY_TASK, task);
    request.getRequestDispatcher(WB.PATH_SHOW_JSP).forward(request, response);
  }
}
