package com.green.team4.service.JH;

import com.green.team4.vo.JH.CategoryVO;

import java.util.List;

public interface CategoryService {
    public List<CategoryVO> cateList();
    public CategoryVO getCateName(String code);

    public List<CategoryVO> getCateCode1();


    public List<CategoryVO> getCateCode2();
    public List<CategoryVO> getCateTier2();
    public List<CategoryVO> getCateTier3WithCode(String code);
    public List<CategoryVO> getCateTier3();
}
