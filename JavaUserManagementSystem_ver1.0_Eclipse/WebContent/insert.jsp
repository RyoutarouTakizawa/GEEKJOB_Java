<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="jums.JumsHelper"%>

<!-- セッションの値をStringにして取得する。nullだった場合は空文字に置き換える -->
<%
    HttpSession hs = request.getSession();

    String name = (String) hs.getAttribute("name");
    if(name == null){
    	name = "";
    }
    String year = (String) hs.getAttribute("year");
    if(year == null){
    	year = "";
    }
    String month = (String) hs.getAttribute("month");
    if(month == null){
    	month = "";
    }
    String day = (String) hs.getAttribute("day");
    if(day == null){
    	day = "";
    }
    String type = (String) hs.getAttribute("type");
    if(type == null){
    	type = "";
    }
    String tell = (String) hs.getAttribute("tell");
    if(tell == null){
    	tell = "";
    }
    String comment = (String) hs.getAttribute("comment");
    if(comment == null){
    	comment = "";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JUMS登録画面</title>
</head>
<body>
	<form action="insertconfirm" method="POST">
		<!-- 名前はvalueをセッションの値に書き換える -->
		名前: <input type="text" name="name" value=<%=name%>> <br>
		<br>
		<!-- 生年月日は一番上のオプションタグのvalueをセッションの値があるときはセッションの値、無いときは「----(--)」とする -->
		生年月日: <select name="year">
			<%
           if(year.equals("")){ %>
			<option value="">----</option>
			<% }else{ %>
			<option value="<%=year%>"><%=year%></option>
			<% } %>
			<%
            for(int i=1950; i<=2010; i++){ %>
			<option value="<%=i%>"><%=i%></option>
			<% } %>
		</select>年 <select name="month">
			<%
           if(month.equals("")){ %>
			<option value="">--</option>
			<% }else{ %>
			<option value="<%=month%>"><%=month%></option>
			<% } %>
			<%
            for(int i = 1; i<=12; i++){ %>
			<option value="<%=i%>"><%=i%></option>
			<% } %>
		</select>月 <select name="day">
			<%
           if(day.equals("")){ %>
			<option value="">--</option>
			<% }else{ %>
			<option value="<%=day%>"><%=day%></option>
			<% } %>
			<%
            for(int i = 1; i<=31; i++){ %>
			<option value="<%=i%>"><%=i%></option>
			<% } %>
		</select>日 <br>
		<br>
		<!-- 種別はセッションの値に応じたラジオボタンにchecked属性を持たせる -->
		種別: <br>
		<input type="radio" name="type" value="1"<%if(type.equals("1") || type.equals("")){out.print("checked");}%>>エンジニア<br>
		<input type="radio" name="type" value="2"<%if(type.equals("2")){out.print("checked");}%>>営業<br>
		<input type="radio" name="type" value="3"<%if(type.equals("3")){out.print("checked");}%>>その他<br><br>
		<!-- 電話番号はvalueをセッションの値に書き換える -->
		電話番号: <input type="text" name="tell" value="<%=tell%>"> <br>
		<br>
		<!-- 自己紹介文はテキストエリアの入力値をセッションの値に書き換える -->
		自己紹介文 <br>
		<textarea name="comment" rows=10 cols=50 style="resize: none"wrap="hard"><%=comment%></textarea>
		<br>
		<br> <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
		     <input type="submit" name="btnSubmit" value="確認画面へ">
	</form>
	<br>
	<!--トップへのリンクを表示 -->
	<%=JumsHelper.getInstance().home()%>
</body>
</html>
