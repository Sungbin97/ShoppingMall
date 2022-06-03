package com.green.team4.mapper.sw;


import com.green.team4.vo.sw.InterestVO;
import com.green.team4.vo.sw.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {

    int getAllCnt(int mno); // 회원별 데이터 전체 가져오기
    int insert(InterestVO interestVO); // 데이터 신규 등록
    List<InterestVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    List<InterestVO> getAllWithSearch(SearchVO searchVO); // 데이터 전체 가져오기(mno 단위로)
    int delete(int mno, int pno); // 데이터 삭제

    // 위시리스트 데이터 확인
    int getOne(InterestVO interestVO);
}
