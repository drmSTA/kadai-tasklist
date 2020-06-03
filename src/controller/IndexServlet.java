package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
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
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

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

    // get tasks from SQL
    List<Task> tasks = DBHandler.getTasks(page, WB.VALUE_PAGE_UL);

    // get the count of total tasks
    long tasksCount = DBHandler.getTotalCountOfTasks();

    request.setAttribute(WB.KEY_TASKS, tasks);
    request.setAttribute(WB.KEY_TASKS_COUNT, tasksCount);
    request.setAttribute(WB.KEY_PAGE_UL, WB.VALUE_PAGE_UL);
    request.setAttribute(WB.KEY_PAGE, page);

    if(request.getSession().getAttribute(WB.KEY_FLUSH) != null) {
      request.setAttribute(WB.KEY_FLUSH, request.getSession().getAttribute(WB.KEY_FLUSH));
      request.getSession().removeAttribute(WB.KEY_FLUSH);
    }

    request.getRequestDispatcher(WB.PATH_INDEX_JSP).forward(request, response);
  }
}
