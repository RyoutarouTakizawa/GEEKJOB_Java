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
 * Servlet implementation class JDBC10
 */
@WebServlet("/JDBC10")
public class JDBC10 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JDBC10() {
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


		String profilesID = request.getParameter("deleteprofilesID");

		int deleteprofilesID = 0;

		if(profilesID != null) {

			deleteprofilesID = Integer.parseInt(profilesID);

		}

		Connection db_con = null;
		PreparedStatement db_st = null;
		ResultSet db_data = null;


		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db?serverTimezone=JST", "root", "");

			db_st = db_con.prepareStatement("DELETE FROM profiles WHERE profilesID = ?");

			if(profilesID != null) {

				db_st.setInt(1, deleteprofilesID);

				int profiledata = db_st.executeUpdate();

				if(profiledata != 0) {

					System.out.println(profiledata + "件削除完了しました");

				} else {

					System.out.println("削除するレコードがありませんでした");

				}

				db_st = db_con.prepareStatement("SELECT * FROM profiles");
				db_data = db_st.executeQuery();

				while(db_data.next()){

					System.out.println("ID：" + db_data.getInt("profilesID") + ", 名前：" + db_data.getString("name") + ", 電話番号：" + db_data.getString("tel") + ", 年齢：" + db_data.getInt("age") + ", 誕生日：" + db_data.getString("birthday") );

				}

				db_st.close();
				db_con.close();
				db_data.close();

			}

			String result = "WEB-INF/input_ID.html";
			RequestDispatcher rd = request.getRequestDispatcher(result);
			rd.forward(request,  response);

			if(profilesID == null) {

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
