package camp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC02 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection db_con = null;
		PreparedStatement db_st = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db?serverTimezone=JST", "root", "");

			db_st = db_con.prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?)");
			db_st.setInt(1, 6);
			db_st.setString(2, "滝澤遼太郎");
			db_st.setString(3, "090-2468-1357");
			db_st.setInt(4, 25);
			db_st.setString(5, "1994-06-24");

			int profiledata = db_st.executeUpdate();
			System.out.print(profiledata + "行が追加されました");

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

}
