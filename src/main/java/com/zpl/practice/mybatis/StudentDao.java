package com.zpl.practice.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author ZhangPeilin
 * @date 2018/11/21
 */

public class StudentDao {

    public void add(Student student) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.insert("com.zpl.practice.mybatis.Student.add", student);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public Student findById(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            return sqlSession.selectOne("com.zpl.practice.mybatis.Student.findById", id);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public List<Student> findAll() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            return sqlSession.selectList("com.zpl.practice.mybatis.Student.findAll");
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public void delete(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.delete("com.zpl.practice.mybatis.Student.delete", 1);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public void update(Student student) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            sqlSession.update("com.zpl.practice.mybatis.Student.update", student);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();

        //Student student = new Student(2, "zhangpeilin", 100000D);
        //studentDao.add(student);

        //Student student = studentDao.findById(1);
        //System.out.println(student.getName());

        //List<Student> students = studentDao.findAll();
        //System.out.println(students.size());
        Student student = studentDao.findById(2);
        student.setName("nintendo switch");
        student.setSal(20000D);
        studentDao.update(student);

        studentDao.delete(1);
    }
}
