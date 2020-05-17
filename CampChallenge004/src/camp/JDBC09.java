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
 * Servlet implementation class JDBC09
 */
@WebServlet("/JDBC09")
public class JDBC09 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JDBC09() {
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


		String profilesID = request.getParameter("insertprofilesID");
		String age = request.getParameter("insertage");
		String insertname = request.getParameter("insertname");
		String inserttel = request.getParameter("inserttel");
		String insertyear = request.getParameter("insertyear");
		String insertmonth = request.getParameter("insertmonth");
		String insertday = request.getParameter("insertday");

		String insertbirthday = "";
		int insertprofilesID = 0;
		int insertage = 0;

		if(insertname != null) {

			insertprofilesID = Integer.parseInt(profilesID);
			insertage = Integer.parseInt(age);
			insertbirthday = insertyear + "-" + insertmonth + "-" + insertday;

		}

		Connection db_con = null;
		PreparedStatement db_st = null;
		ResultSet db_data = null;


		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db?serverTimezone=JST", "root", "");

			db_st = db_con.prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?)");

			if(insertname != null) {

				db_st.setInt(1, insertprofilesID);
				db_st.setString(2, insertname);
				db_st.setString(3, inserttel);
				db_st.setInt(4, insertage);
				db_st.setString(5, insertbirthday);

				int profiledata = db_st.executeUpdate();
				System.out.println(profiledata + "件登録完了しました");


				db_st = db_con.prepareStatement("SELECT * FROM profiles");
				db_data = db_st.executeQuery();

				while(db_data.next()){

					System.out.println("ID：" + db_data.getInt("profilesID") + ", 名前：" + db_data.getString("name") + ", 電話番号：" + db_data.getString("tel") + ", 年齢：" + db_data.getInt("age") + ", 誕生日：" + db_data.getString("birthday") );

				}

				db_st.close();			
				db_con.close();
				db_data.close();

			}

			String result = "WEB-INF/input_insert.html";
			RequestDispatcher rd = request.getRequestDispatcher(result);
			rd.forward(request,  response);

			if(insertname == null) {

				db_st.close();			
				db_con.close();

			}

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
