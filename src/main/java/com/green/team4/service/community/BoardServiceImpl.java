package com.green.team4.service.community;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.community.BoardMapper;
import com.green.team4.vo.mypage.SearchVO;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper mapper;


    @Override
    public void insert(BoardVO vo) {
        mapper.insert(vo);
    }

    @Override
    public BoardVO getOne(Long bno) {

        return mapper.readOne(bno);
    }

    @Override
    public List<BoardVO> getList() {

        return mapper.readList();
    }

    @Override
    public void modify(BoardVO vo) {
        mapper.modify(vo);
    }

    @Override
    public void delete(Long sid) {
        mapper.delete(sid);
    }

    @Override
    public List<BoardVO> getPageList(Criteria criteria) {
        criteria.setPage((criteria.getPage() - 1)* criteria.getPageNum());
        return mapper.getPageList(criteria);
    }

    @Override
    public int getTotal(Criteria criteria) {
        return mapper.getTotalCount(criteria);
    }

    @Override
    public BoardVO userInfo(Long mno) {
        return mapper.userInfo(mno);
    }

    @Override
    public List<BoardVO> readListForMain() {
        return mapper.readListForMain();
    }

    @Override
    public List<BoardVO> readAllByMno(int mno) { // SW 추가
        return mapper.getAllByMno(mno);
    }

    @Override
    public List<BoardVO> readAllByMnoSearch(int mno, int pageNum, SearchVO searchVO) { // SW 추가
        System.out.println("BoardService => readAllByMnoSearch 실행 => 받은 mno: "+mno);
        System.out.println("BoardService => readAllByMnoSearch 실행 => 받은 pageNum: "+pageNum);
        System.out.println("BoardService => readAllByMnoSearch 실행 => 받은 searchVO: "+searchVO);
        searchVO.setMno(mno); // 검색 vo에 mno set
        PageHelper.startPage(pageNum,8); // 가져올 데이터 페이지 번호, 페이지 당 데이터 개수
        List<BoardVO> list = mapper.getAllByMnoSearch(searchVO);
        System.out.println("BoardService => readAllByMnoSearch 실행 후 가져온 list: "+list);

        return list;
    }
}
