package jums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertConfirm
 */
@WebServlet("/InsertConfirm")
public class InsertConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertConfirm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//セッションスタート
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
			String accesschk = request.getParameter("ac");

			if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
				throw new Exception("不正なアクセスです");
			}

			UserDataBeans udb = new UserDataBeans();
			//フォームからの入力を取得
			String name = request.getParameter("name");
			if(name == null) {
				name = "";	
			}			
			udb.setName(name);			
			String year = request.getParameter("year");
			if(year == null) {
				year = "";	
			}	
			udb.setYear(year);
			String month = request.getParameter("month");
			if(month == null) {
				month = "";	
			}	
			udb.setMonth(month);
			String day = request.getParameter("day");
			if(day == null) {
				day = "";	
			}	
			udb.setDay(day);
			String type = request.getParameter("type");
			udb.setType(type);
			String tell = request.getParameter("tell");
			if(tell == null) {
				tell = "";	
			}	
			udb.setTell(tell);
			String comment = request.getParameter("comment");
			if(comment == null) {
				comment = "";	
			}	
			udb.setComment(comment);

			//セッションに格納
			session.setAttribute("name", udb.getName());
			session.setAttribute("year", udb.getYear());
			session.setAttribute("month",udb.getMonth());
			session.setAttribute("day", udb.getDay());
			session.setAttribute("type", udb.getType());
			session.setAttribute("tell", udb.getTell());
			session.setAttribute("comment", udb.getComment());
			System.out.println("Session updated!!");

			request.getRequestDispatcher("/insertconfirm.jsp").forward(request, response);
		}catch(Exception e){
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
