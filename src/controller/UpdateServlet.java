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
 * Servlet implementation class UpdateServlet
 */
@WebServlet(WB.PATH_UPDATE)
public class UpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 20200604L;

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
        // 前ページ より 対象の id を取得、データベースから対象の Task インスタンスを取得
        // (Task インスタンスの null は不可： edit.jspで検知済み）
        int id = (int)(request.getSession().getAttribute(WB.KEY_TASK_ID));
        Task task = DBHandler.getTask(id);

        // 前ページ より編集内容を取得、task に適用（更新日時も更新）
        String content = request.getParameter(WB.KEY_CONTENT);
        task.setContent(content);
        task.setUpdatedAtPresent();

        // task の validation を行う
        List<String> errorMessages = task.validate();
        if(errorMessages.size() > 0) {
          // errorMessage がある場合、その旨を転送先のページで伝える
          request.setAttribute(WB.KEY_TOKEN                 , _token);
          request.setAttribute(WB.KEY_TASK                     , task);
          request.setAttribute(WB.KEY_ERROR_MESSAGES , errorMessages);
          request.getRequestDispatcher(WB.PATH_EDIT_JSP).forward(request, response);
        } else {
          // DBへの更新を行う
          DBHandler.updateTaskInDB(id, task);

          // flush を設定し、index ページへリダイレクト
          request.getSession().setAttribute(WB.KEY_FLUSH, "更新が完了しました。");
          response.sendRedirect(request.getContextPath() + WB.PATH_INDEX);
        }
     }
  }
}
