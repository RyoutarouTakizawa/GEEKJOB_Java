package jums;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class InsertResult
 */
@WebServlet("/InsertResult")
public class InsertResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			//セッションスタート
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
			//直リンク防止
			String accesschk = request.getParameter("ac");

			if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
				throw new Exception("不正なアクセスです");
			}

			//ユーザー情報に対応したJavaBeansオブジェクトに格納していく
			UserDataDTO userdata = new UserDataDTO();
			userdata.setName((String)session.getAttribute("name"));
//			Calendar birthday = Calendar.getInstance();
			String year = (String)session.getAttribute("year");
		    String month = (String)session.getAttribute("month");
			String day = (String)session.getAttribute("day");	
			String insertday = year + "-" + month + "-" + day;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = new Date(); 
			birthday = sdf.parse(insertday);			
			userdata.setBirthday(birthday);
			userdata.setType(Integer.parseInt((String)session.getAttribute("type")));
			userdata.setTell((String)session.getAttribute("tell"));
			userdata.setComment((String)session.getAttribute("comment"));

			//DBへデータの挿入
			UserDataDAO .getInstance().insert(userdata);

			request.getRequestDispatcher("/insertresult.jsp").forward(request, response);
			
		}catch(Exception e){
			//データ挿入に失敗したらエラーページにエラー文を渡して表示
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
