<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../header.jsp"%>

<html>
<head>
    <title>成績一覧</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        .no-score {
            color: #888; /* 未実施の試験点数のスタイリング */
        }
    </style>
</head>
<body>
    <h2>成績一覧</h2>

    <!-- 🔹 科目別検索フォーム -->
    <h3>科目別検索</h3>
    <form action="/score_management/main/TestListAction" method="get">
        入学年度:
        <select name="entYear">
            <c:forEach var="year" items="${entYearList}">
                <option value="${year}" ${year == param.entYear ? 'selected' : ''}>${year}</option>
            </c:forEach>
        </select>

        クラス:
        <select name="classNum">
            <c:forEach var="classNum" items="${classList}">
                <option value="${classNum}" ${classNum == param.classNum ? 'selected' : ''}>${classNum}</option>
            </c:forEach>
        </select>

        科目:
        <select name="subjectCd">
            <c:forEach var="subject" items="${subjectList}">
                <option value="${subject.cd}" ${subject.cd == param.subjectCd ? 'selected' : ''}>${subject.name}</option>
            </c:forEach>
        </select>

        <button type="submit">検索</button>
    </form>

    <!-- 🔹 生徒別検索フォーム -->
    <h3>生徒別検索</h3>
    <form action="/score_management/main/TestListAction" method="get">
        学生番号:
        <input type="text" name="studentNo" value="${param.studentNo}">
        <button type="submit">検索</button>
    </form>

    <!-- 🔹 検索結果の表示 -->
    <c:choose>
        <c:when test="${not empty subjectName and not empty testScores}">
            <h3>科目: ${subjectName}</h3>
            <table>
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <c:forEach var="i" begin="1" end="${maxNo}">
                            <th>${i}回目</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="record" items="${testScores}">
                        <tr>
                            <td>${record.entYear}</td>
                            <td>${record.classNum}</td>
                            <td>${record.studentNo}</td>
                            <td>${record.name}</td>

                            <c:forEach var="i" begin="1" end="${maxNo}">
                                <td class="${record.points[i] == null ? 'no-score' : ''}">
                                    ${record.points[i] != null ? record.points[i] : '-'}
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>

        <c:when test="${not empty studentName and not empty testScores}">
            <h3>氏名: ${studentName} (学籍番号: ${param.studentNo})</h3>
            <table>
                <thead>
                    <tr>
                        <th>科目名</th>
                        <th>科目コード</th>
                        <th>試験回数</th>
                        <th>得点</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="record" items="${testScores}">
                        <tr>
                            <td>${record.subjectName}</td>
                            <td>${record.subjectCd}</td>
                            <td>${record.no}</td>
                            <td>${record.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>

        <c:otherwise>
            <c:if test="${not empty param.entYear or not empty param.classNum or not empty param.subjectCd or not empty param.studentNo}">
                <p>該当するデータがありません。</p>
            </c:if>
        </c:otherwise>
    </c:choose>
</body>
</html>