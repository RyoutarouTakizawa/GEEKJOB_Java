package camp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JDBC12
 */
@WebServlet("/JDBC12")
public class JDBC12 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JDBC12() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		response.getWriter().append("Served at: ").append(request.getContextPath());

		//フォームの値を取得
		String searchname = request.getParameter("searchname");
		String age = request.getParameter("searchage");
		String searchyear = request.getParameter("searchyear");
		String searchmonth = request.getParameter("searchmonth");
		String searchday = request.getParameter("searchday");

		//年齢はint型にするため初期値を設定
		int searchage = -1;

		//初回起動時にnullを取得してしまわないように変換
		if(searchname == null) {

			searchname = "";

		}

		if(searchyear == null) {

			searchyear = "";

		}

		if(searchmonth == null) {

			searchmonth = "";

		}

		if(searchday == null) {

			searchday = "";

		}

		//年齢の取得値がnullでない場合はintに変換する ←フォーム未入力時で送信した場合は空文字となるので空文字の場合も変換しない
		if(age != null && age != "")  {

			searchage = Integer.parseInt(age);

		}


		String searchbirthday = searchyear + "-" + searchmonth + "-" + searchday;

		Connection db_con = null;
		PreparedStatement db_st = null;
		ResultSet db_data = null;
		int count = 0;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db?serverTimezone=JST", "root", "");
			db_st = db_con.prepareStatement("SELECT * FROM profiles WHERE name = ? OR age = ? OR birthday = ?");

			//初回起動時に検索してしまわないようにするために条件分岐させている
			if(!(searchname.equals("")) || (searchage != -1) || !(searchyear.equals("")) || !(searchmonth.equals("")) || !(searchday.equals(""))) {

				db_st.setString(1, searchname);
				db_st.setInt(2, searchage);
				db_st.setString(3, searchbirthday);
				db_data = db_st.executeQuery();

				while(db_data.next()){

					System.out.println("ID：" + db_data.getInt("profilesID") + ", 名前：" + db_data.getString("name") + ", 電話番号：" + db_data.getString("tel") + ", 年齢：" + db_data.getInt("age") + ", 誕生日：" + db_data.getString("birthday") );
					count++;

				}

				if(count == 0) {

					System.out.println("検索結果がありません");

				}

				db_st.close();
				db_con.close();
				db_data.close();

			}

			String result = "WEB-INF/input_search.html";
			RequestDispatcher rd = request.getRequestDispatcher(result);
			rd.forward(request,  response);

			db_st.close();
			db_con.close();

		} catch(SQLException e_sql){

			System.out.println("接続時にエラーが発生しました：" + e_sql.toString());

		} catch(Exception e) {

			System.out.println("接続時にエラーが発生しました：" + e.toString());


		} finally {

			if(db_con != null){

				try {

					db_con.close();

				} catch (Exception e_con) {

					System.out.println(e_con.getMessage());

				}
			}
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
