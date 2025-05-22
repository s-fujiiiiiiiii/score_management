<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>点数編集</title>
</head>
<body>
    <h2>点数編集</h2>
    <form action="TestUpdateExecuteAction" method="post">
        <input type="hidden" name="classNum" value="${test.classNum}" />
        <input type="hidden" name="subjectCd" value="${test.subjectCd}" />
        <input type="hidden" name="studentNo" value="${test.studentNo}" />
        <input type="hidden" name="examRound" value="${test.no}" />

        氏名：${test.studentName}<br/>
        点数：<input type="number" name="point" value="${test.point}" required /><br/>

        <input type="submit" value="更新" />
    </form>

    <form action="TestDeleteAction" method="post" onsubmit="return confirm('本当に削除しますか？');">
        <input type="hidden" name="classNum" value="${test.classNum}" />
        <input type="hidden" name="subjectCd" value="${test.subjectCd}" />
        <input type="hidden" name="studentNo" value="${test.studentNo}" />
        <input type="hidden" name="examRound" value="${test.no}" />
        <input type="submit" value="削除" />
    </form>

    <br/>
    <a href="test_list.jsp">戻る</a>
</body>
</html>
