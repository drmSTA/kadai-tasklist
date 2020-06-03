package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //CSRF対策
    String _token = (String)request.getParameter(WB.KEY_TOKEN);
    if(_token != null && _token.equals(request.getSession().getId())) {

        // contents for message
        String content = request.getParameter(WB.KEY_CONTENT);
        Task task = new Task(content);

        // perform validation and return to Create Form if error in contents
        List<String> errorMessages = task.validate();

        if(errorMessages.size() > 0) {
            // set data  and alert (if it has) in Create Form
            request.setAttribute(WB.KEY_TOKEN                 , _token);
            request.setAttribute(WB.KEY_TASK                     , task);
            request.setAttribute(WB.KEY_ERROR_MESSAGES , errorMessages);

            // back to Create Form
            RequestDispatcher rd = request.getRequestDispatcher(WB.PATH_NEW_JSP);
            rd.forward(request, response);
        } else {
            // perform a commitment
            DBHandler.addNewTaskIntoDB(task);
            // set a notice
            request.getSession().setAttribute(WB.KEY_FLUSH, "登録が完了しました。");

            // go to Index Page of TASKS
            response.sendRedirect(request.getContextPath() + WB.PATH_INDEX);
        }
    }
  }
}
