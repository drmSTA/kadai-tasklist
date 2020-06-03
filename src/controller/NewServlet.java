package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import utility.WB;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // CSRF対策
      request.setAttribute(WB.KEY_TOKEN, request.getSession().getId());

      //アクセス時に内容未入力の Task インスタンスを作成
      request.setAttribute(WB.KEY_TASK, new Task());

      request.getRequestDispatcher(WB.PATH_NEW_JSP).forward(request, response);

    }

}
