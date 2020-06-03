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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
  private static final long serialVersionUID = 20200604L;

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
        // 前ページ より content を取得、task インスタンスの作成
        String content = request.getParameter(WB.KEY_CONTENT);
        Task task = new Task(content);

        // taskインスタンスの validation を行う
        List<String> errorMessages = task.validate();

        if(errorMessages.size() > 0) {
            // task インスタンスに不具合があるため errorMessages とともに new.jsp へ転送
            request.setAttribute(WB.KEY_TOKEN                 , _token);
            request.setAttribute(WB.KEY_TASK                     , task);
            request.setAttribute(WB.KEY_ERROR_MESSAGES , errorMessages);
            request.getRequestDispatcher(WB.PATH_NEW_JSP).forward(request, response);
        } else {
            // DBへ登録
            DBHandler.addNewTaskIntoDB(task);

            // flush を設定し、index ページへリダイレクト
            request.getSession().setAttribute(WB.KEY_FLUSH, "登録が完了しました。");
            response.sendRedirect(request.getContextPath() + WB.PATH_INDEX);
        }
    }
  }
}
