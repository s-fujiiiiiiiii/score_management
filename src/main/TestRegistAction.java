package main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StudentDao studentDao = new StudentDao();
		Student student = new Student();
		List<Integer> entYearList = new ArrayList<>();
		
		
	}

}