package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DBHandler;
import utility.WB;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet(WB.PATH_DESTROY)
public class DestroyServlet extends HttpServlet {
  private static final long serialVersionUID = 20200604L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //CSRF対策
      String _token = (String)request.getParameter(WB.KEY_TOKEN);
      if(_token != null && _token.equals(request.getSession().getId())) {
          // 前ページ より 対象の id を取得、データベースから消去
          int id = (int)(request.getSession().getAttribute(WB.KEY_TASK_ID));
          DBHandler.removeTaskFromDB(id);

          // flush を設定、処理済みの id を session から除去し、index ページへリダイレクト
          request.getSession().setAttribute(WB.KEY_FLUSH, "削除が完了しました。");
          request.getSession().removeAttribute(WB.KEY_TASK_ID);
          response.sendRedirect(request.getContextPath() + WB.PATH_INDEX);
      }
    }
}
