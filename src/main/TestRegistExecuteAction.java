package main;

import bean.Test;
import dao.TestDao;

public class TestRegistExecuteAction {
    public boolean execute(String entYearStr, String classNum, String subjectCd, String examRound, int point) throws Exception {
        TestDao dao = new TestDao();
        Test test = new Test();

        int entYear = Integer.parseInt(entYearStr);
        test.setEntYear(entYear);
        test.setClassNum(classNum);
        test.setSubjectCd(subjectCd);
        test.setExamRound(examRound);
        test.setPoint(point);

        return dao.save(test, dao.getConnection());
    }
}