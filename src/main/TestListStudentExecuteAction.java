package main;

import java.util.List;

import bean.TestListStudent;
import dao.TestListStudentDao;

public class TestListStudentExecuteAction {
    public List<TestListStudent> execute(String studentNo) throws Exception {
        TestListStudentDao dao = new TestListStudentDao();
        return dao.findByStudentNo(studentNo);
    }
}