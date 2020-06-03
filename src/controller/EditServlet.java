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
 * Servlet implementation class EditServlet
 */
@WebServlet(WB.PATH_EDIT)
public class EditServlet extends HttpServlet {
  private static final long serialVersionUID = 20200604L;

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
      // 前ページ より 対象の id を取得、データベースから対象の Task インスタンスを取得 (null 許容）
      int id = Integer.parseInt(request.getParameter(WB.KEY_TASK_ID));
      Task task = DBHandler.getTask(id);

      // Task インスタンスが null でない場合（＝DBにデータがある）、セッションスコープに id を設定
      // のちの update 時に使用
      if(task != null) request.getSession().setAttribute(WB.KEY_TASK_ID, task.getId());

      // Task インスタンス 及び id を設定、ページ転送
      request.setAttribute(WB.KEY_TASK, task);
      request.setAttribute(WB.KEY_TOKEN, request.getSession().getId());
      request.getRequestDispatcher(WB.PATH_EDIT_JSP).forward(request, response);
    }

}
