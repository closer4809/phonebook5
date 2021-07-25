package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;






@Repository
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체리스트 가져오기
	
	//전체리스트
	public List<PersonVo>   getPersonList(){
		
		//dv요청 
		//리스트 가져온다(맵퍼스의 폰북의 셀렉트리스트 참조)			
		List<PersonVo> personlist = sqlSession.selectList("phonebook.selectList");
		System.out.println("dao");
		
		System.out.println(personlist);
		
		return personlist;
		
	}
	
	//전화번호저
	public int personInsert(PersonVo personVo) {
		
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return 1;
	}
	
	
}
