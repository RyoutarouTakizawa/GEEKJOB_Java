package camp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC04 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection db_con = null;
		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db?serverTimezone=JST", "root", "");

			db_st = db_con.prepareStatement("SELECT * FROM profiles WHERE profilesID = ?");
			db_st.setInt(1, 1);
			db_data = db_st.executeQuery();

			while(db_data.next()){

				System.out.println("ID：" + db_data.getInt("profilesID") + ", 名前：" + db_data.getString("name") + ", 電話番号：" + db_data.getString("tel") + ", 年齢：" + db_data.getInt("age") + ", 誕生日：" + db_data.getString("birthday") );

			}

			db_data.close();
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
