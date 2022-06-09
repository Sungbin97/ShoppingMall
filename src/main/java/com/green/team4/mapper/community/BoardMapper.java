package com.green.team4.mapper.community;

import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public void insert(BoardVO vo);
    public void modify(BoardVO vo);
    public BoardVO readOne(Long bno);
    public List<BoardVO> readList();
    public void delete(Long uNo);
    public List<BoardVO> getPageList(Criteria criteria);
    public int getTotalCount(Criteria criteria);
    // 전체 게시글 수를 구하기 위한 메서드
    public BoardVO userInfo(Long mno);
    //JH추가
    public List<BoardVO> readListForMain();
}