package com.green.team4.serviceTests.sw;

import com.green.team4.mapper.sw.MemberInfoMapper;
import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.sw.MemberInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemServiceTests {

    @Autowired
    private MemberInfoService service;

    @Autowired
    private MemberInfoMapper mapper;

    @Test
    public void testInsert(){ // Test데이터 입력

        String[] gender = {"남성","여성"};
        String[] auth = {"일반","에디터","관리자"};
        String[] grade = {"일반","우수","최우수"};

        IntStream.rangeClosed(1,50).forEach(i->{
            MemberInfoVO memberInfoVO = new MemberInfoVO();
            memberInfoVO.setId("Id"+i);
            memberInfoVO.setPassword("pw"+i);
            memberInfoVO.setName("name"+i);
            memberInfoVO.setNickName("nickName"+i);
            memberInfoVO.setEmail("email"+i+"@test.com");
            memberInfoVO.setPhoneNum("1234-"+i);
            memberInfoVO.setGender(gender[(int)(Math.random()*2)]);
            memberInfoVO.setSSNum("99999-"+i);
            memberInfoVO.setAddress("경기도 성남시 분당구 운중동"+i);
            memberInfoVO.setAuth(auth[(int)(Math.random()*3)]);
            memberInfoVO.setGrade(grade[(int)(Math.random()*3)]);

            mapper.insert(memberInfoVO);
        });
    }

    @Test
    public void testReadOne(){ // 데이터 하나 가져오기
        int mno = 24;
        MemberInfoVO memberInfoVO = service.readOne(mno);
        System.out.println("가져온 MemberInfo: "+memberInfoVO);
    }

    @Test
    public void testUpdate(){
        MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setMno(24);
        memberInfoVO.setPassword("pw"+"수정");
        memberInfoVO.setNickName("nickName"+"수정");
        memberInfoVO.setEmail("email"+"수정"+"@test.com");
        memberInfoVO.setPhoneNum("1234-"+"수정");
        memberInfoVO.setAddress("경기도 성남시 분당구 운중동"+"수정");

        int result = service.modify(memberInfoVO);
        System.out.println("수정된 개수: "+result);
    }

    @Test
    public void testDelete(){
        int mno = 24;
        int result = service.remove(mno);
        System.out.println("삭제된 개수: "+result);
    }
}