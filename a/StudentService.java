package com.godinsec.core.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.godinsec.core.dao.IStudentDAO;
import com.godinsec.core.iservice.IStudentService;
import com.godinsec.core.pack.StudentPack;
import com.godinsec.core.pojo.Student;

public class StudentService implements IStudentService {
	private Logger log = Logger.getLogger(this.getClass());
	private IStudentDAO studentDAO;

	public IStudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public List<Student> findStudents(String studName, String studNo) {
		log.debug("findStudents method started!");
		return studentDAO.findByHqlWhere(StudentPack.packStudentQuery(studName,
				studNo));
	}

	@Override
	public Student getStudent(Long id) {
		return studentDAO.getByPK(id);
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = new Student();
		student.setId(id);
		studentDAO.remove(student);
	}

	@Override
	public Student saveStudent(Student student) {
		return studentDAO.save(student);
	}

}
