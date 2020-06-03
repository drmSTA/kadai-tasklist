package controller;

import java.io.IOException;

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
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int id = Integer.parseInt(request.getParameter(WB.KEY_TASK_ID));
      Task task = DBHandler.getTask(id);

      // registry task and session id into session scope
      request.setAttribute(WB.KEY_TASK, task);
      request.setAttribute(WB.KEY_TOKEN, request.getSession().getId());

      // registry task id into session scope
      if(task != null) {
        request.getSession().setAttribute(WB.KEY_TASK_ID, task.getId());
      }

      request.getRequestDispatcher(WB.PATH_EDIT_JSP).forward(request, response);
    }

}
