package com.tristan.web.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tristan.web.po.hibernate.Student;
import com.tristan.web.po.hibernate.User;
import com.tristan.web.vo.StudentVO;

@Repository
public class StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * --每门课程的最高分，最低分，平均分, 学选修的人数  
select t2.cno, t2.cname, max(t3.grade), avg(t3.grade), min(t3.grade), count(t2.cno)  
from   course t2, sc t3  
where  t2.cno=t3.cno   
group by t2.cno, t2.cname  
order by  max(t3.grade) desc  
	 * @return
	 */
	
	public List listEachCourseGrade(){
		String hql = "select  t2.cno, t2.cname, max(t3.grade) , avg(t3.grade), min(t3.grade), count(t2.cno) " +
		" from Course as t2, SC as t3 " +
		" where t2.cno=t3.scpk.cno" + 
		" group by t2.cno, t2.cname " +
		" order by count(t2.cno) desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List list =  query.list(); 
		
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[])list.get(i);
			for (int j = 0; j < o.length; j++) {
				System.out.print(o[j]+ "   ");
				
			}
			System.out.println();
			
		}
		return list;
	}
	
	
	
	
	public List search(StudentVO studentVO) {
		StringBuilder sb = new StringBuilder();
		sb.append("select t1.sno, t1.sname,  t2.cno, t2.cname, t3.grade " +
		" from Student as t1, Course as t2, SC as t3  " +
		" where t1.sno=t3.scpk.sno and t2.cno=t3.scpk.cno ");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		if (!StringUtils.isEmpty(studentVO.getSno())) {
			sb.append(" and t1.sno = '"+studentVO.getSno()+"'");
		}
		if (!StringUtils.isEmpty(studentVO.getSname())) {
			sb.append(" and t1.sname = '"+studentVO.getSname()+"'");
		}
		if (!StringUtils.isEmpty(studentVO.getCno())) {
			sb.append(" and t2.cno = '"+studentVO.getCno()+"'");
		}
		if (!StringUtils.isEmpty(studentVO.getCname())) {
			sb.append(" and t2.cname = '"+studentVO.getCname()+"'");
		}

		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		
		return query.list();
	}
	
	public Student find(String sno) {
		String hql = "from Student as s where s.sno = '"+sno+"'"; 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Student s = (Student) query.list().get(0); 
		return s;
	}
	
	public List listAll(){
		String hql = "select t1.sno, t1.sname,  t2.cno, t2.cname, t3.grade " +
		" from Student as t1, Course as t2, SC as t3  " +
		" where t1.sno=t3.scpk.sno and t2.cno=t3.scpk.cno";// and t1.=:sno";
		
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		//query.setString("sno", "003");
		
		List list =  query.list(); 
		return list;
	}
	
	public static void main(String[] args) {
		//testA();
		//testB();
		testC();
		//testD();
		
	}

	private static void testD() {
		Session session = new AnnotationConfiguration().configure("config/hibernateTest.cfg.xml").buildSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "select t1.sno, t1.sname,  t2.cno, t2.cname, t3.grade " +
				" from Student as t1, Course as t2, SC as t3  " +
				" where t1.sno=t3.scpk.sno and t2.cno=t3.scpk.cno ";
		
		Query query = session.createQuery(hql);
		
		List list = query.list();
	
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[])list.get(i); 
			StudentVO vo = new StudentVO();
			vo.setSno((String)o[0]);
			vo.setSname((String)o[1]);
			vo.setCno((String)o[2]);
			vo.setCname((String)o[3]);
			vo.setGrade((Integer)o[4]);
			studentList.add(vo);
		}
		
		for (StudentVO studentVO : studentList) {
			System.out.println(studentVO.getSno() + "  "+ studentVO.getSname()+"   "+ studentVO.getCno()+"   "+studentVO.getCname() +"   "+ studentVO.getGrade());
		}
		
		tx.commit();
	}

	private static void testC() {
		StudentDAO dao = new StudentDAO();
		dao.sessionFactory =  new AnnotationConfiguration().configure("config/hibernateTest.cfg.xml").buildSessionFactory();
		dao.sessionFactory.getCurrentSession().beginTransaction();
		
//		Student s = dao.find("001");
//		System.out.println(s.getSname());
		
		dao.listEachCourseGrade();
	}

	private static void testA() {
		Session session = new AnnotationConfiguration().configure("config/hibernateTest.cfg.xml").buildSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Student stu = new Student();
		stu.setSno("997");
		stu.setSname("X man");
		stu.setSage(30);
		stu.setSdept("HP");
		stu.setSsex("M");
		
		session.save(stu);
		tx.commit();
	}	
	
	private static void testB() {
		Session session = new AnnotationConfiguration().configure("config/hibernateTest.cfg.xml").buildSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Student as s where s.sno = '998'"; 
		Query query = session.createQuery(hql);
		Student s = (Student) query.list().get(0); 
//		Student s = (Student)session.load(Student.class, "999");
		System.out.println(s.getSname());
		tx.commit();
	}	

}
