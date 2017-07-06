package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Student;
import com.test.util.DBUtil;

public class StudentDao {
	
	/*
	 * ������ݵ�����ɾ���飬��
	 * 
	 */
	
	
	//����ѧԱ�����ݿ�
	public  int addStudent(Student stu){
		
		//�õ�����
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstm=null;
		int n=0;
		//�����������ݵ�sql���
		String sql="insert into student(s_name,s_age,s_sex,s_fun) " +
				" values(?,?,?,?)";
		
		try {
			
			//����pstm
			pstm=conn.prepareStatement(sql);
		  
			//Ϊ?���ò���
		    pstm.setString(1, stu.getS_name());
		    pstm.setInt(2, stu.getS_age());
		    pstm.setString(3, stu.getS_sex());
		    pstm.setString(4, stu.getS_fun());
		
		    //ִ�в�ѯ
		    n=pstm.executeUpdate();
		    
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, pstm);
		}
		return n;
	}

	
	//���ȫ��ѧԱ
	public List<Student> getList(){
		
		List<Student> list=new ArrayList<Student>();
		//�õ�����
		Connection conn=DBUtil.getConnection();
		//��д��ѯ��sql���
		String sql="select * from student";
		
		//����pstm
		PreparedStatement  pstm=null;
		
		try {
			pstm=conn.prepareStatement(sql);
		
		  //ִ�в�ѯ, �õ������
			ResultSet rs=pstm.executeQuery();
		  
		//ѭ�����������������е����ݷ�װ��student,װ��list
			while(rs.next()){
				int id=rs.getInt("s_id");
				String name=rs.getString("s_name");
				int age=rs.getInt("s_age");
				String sex=rs.getString("s_sex");
				String fun=rs.getString("s_fun");
				
				Student stu=new Student(id, name, age, sex, fun);
				
				list.add(stu);
			}
			
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}finally{
			
			DBUtil.closeConn(conn, pstm);
		}
		
		
		
		return list;
	}

    //����ѧԱid,ɾ��ѧԱ
	public void deleteById(int s_id) {
	 
		//�������
		Connection conn=DBUtil.getConnection();
		//��дɾ����sql���
		String sql="delete from student where s_id=?";
		
		PreparedStatement pstm=null;
		
		try {
			//����pstm
			pstm=conn.prepareStatement(sql);
			//Ϊ����ֵ
			pstm.setInt(1, s_id);
			//ִ��ɾ��
			pstm.executeUpdate();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, pstm);
		}
		
		
	}


	//����ѧԱid,�鵥��ѧԱ
	public Student getStudentById(int s_id) {
		      
		 Student stu=null;
		
		//�õ�����
		Connection conn=DBUtil.getConnection();
		//��д��ѯ��sql���
		String sql="select * from student where s_id=?";
		//����pstm
		PreparedStatement  pstm=null;
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, s_id);
					
			//ִ�в�ѯ, �õ������
			ResultSet rs=pstm.executeQuery();
				  
			//ѭ�����������������е����ݷ�װ��student
			while(rs.next()){
				String name=rs.getString("s_name");
				int age=rs.getInt("s_age");
				String sex=rs.getString("s_sex");
				String fun=rs.getString("s_fun");
						
				stu=new Student(s_id, name, age, sex, fun);
				}
			} catch (SQLException e) {
							
			e.printStackTrace();
				
			}finally{
				DBUtil.closeConn(conn, pstm);
			}
				
			return stu;
	}

    //�޸ĵ���ѧԱ
	public void updateStudent(Student stu) {
		
		Connection conn=DBUtil.getConnection();
		String sql="update student " +
				" set s_name=?,s_age=?,s_sex=?,s_fun=? " +
				" where s_id=? ";
		PreparedStatement pstm=null;
		
		try {
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, stu.getS_name());
			pstm.setInt(2, stu.getS_age());
			pstm.setString(3, stu.getS_sex());
			pstm.setString(4, stu.getS_fun());
			pstm.setInt(5, stu.getS_id());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn, pstm);
		}
		
		
	}
	
	
	
}