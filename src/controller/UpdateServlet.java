package controller;

import java.io.IOException;
import java.sql.Timestamp;
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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //CSRF対策
      String _token = (String)request.getParameter(WB.KEY_TOKEN);
      if(_token != null && _token.equals(request.getSession().getId())) {
        int id = (int)(request.getSession().getAttribute(WB.KEY_TASK_ID));
        Task task = DBHandler.getTask(id);

        String content = request.getParameter(WB.KEY_CONTENT);
        task.setContent(content);
        task.setUpdatedAtPresent();

          // perform validation and return to Create Form if error in contents
        List<String> errorMessages = task.validate();
          if(errorMessages.size() > 0) {
              // set data  and alert (if it has) in Create Form
              request.setAttribute(WB.KEY_TOKEN                 , _token);
              request.setAttribute(WB.KEY_TASK                     , task);
              request.setAttribute(WB.KEY_ERROR_MESSAGES , errorMessages);

              // back to Edit form
              RequestDispatcher requestDispatcher = request.getRequestDispatcher(WB.PATH_EDIT_JSP);
              requestDispatcher.forward(request, response);
          } else {
              // perform a commitment
              DBHandler.updateTaskInDB(id, task);

              // post a notice
              request.getSession().setAttribute(WB.KEY_FLUSH, "更新が完了しました。");

              // go to Index Page
              response.sendRedirect(request.getContextPath() + WB.PATH_INDEX);
          }

      }
    }
}
