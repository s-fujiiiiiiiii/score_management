package main;

import java.util.List;

import bean.TestListStudent;
import dao.TestListSubjectDao;

public class TestListSubjectExecuteAction {
    public List<TestListStudent> execute(String subjectCd, String classNum, int entYear) throws Exception {
        TestListSubjectDao dao = new TestListSubjectDao();
        return dao.findBySubjectCdClassNumAndEntYear(subjectCd, classNum, entYear);
    }
}