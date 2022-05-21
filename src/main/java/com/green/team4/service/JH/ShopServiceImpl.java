package com.green.team4.service.JH;

import com.green.team4.mapper.JH.ShopMapper;
import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<ProductVO> getListAll() {
        return shopMapper.getListAll();
    }

    @Override
    public List<ProductVO> getListWithCategory(String p_category) {
        return shopMapper.getListWithCategory(p_category);
    }

    @Override
    public int register(ProductVO pvo) {
        return 0;
    }

    @Override
    public ProductVO getOne(int p_no) {
        return shopMapper.getOne(p_no);
    }

    @Override
    public ProductVO getProductWithOpt(Product_optVO povo) {
        return shopMapper.getProductWithOpt(povo);
    }

    @Override
    public List<ProductVO> getListByCategoryAndPage(Map<String, Object> map) {
        return shopMapper.getListByCategoryAndPage(map);
    }

    @Override
    public int getTotalProductCountbyCategory(String p_category) {
        return shopMapper.getTotalProductCountbyCategory(p_category);
    }


    @Override
    public List<ProductVO> getCategory(String p_category) {
        return shopMapper.getCategory(p_category);
    }

    @Override
    public List<ProductVO> getListByFind(ItemPageCriteria cri) {
        return shopMapper.getListByFind(cri);
    }

    @Override
    public int getTotaldatabyFind(ItemPageCriteria cri) {
        return shopMapper.getTotaldatabyFind(cri);
    }

    @Override
    public List<ProductVO> getListBySearch(String keyword) {
        return shopMapper.getListBySearch(keyword);
    }

    @Override
    public List<Product_optVO> getColors(int pno) {
        return shopMapper.getColors(pno);
    }

    @Override
    public List<Product_optVO> getOptions(int pno) {
        return shopMapper.getOptions(pno);
    }
    public List<Product_optVO> getOptions2(int pno) {
        return shopMapper.getOptions2(pno);
    }
    @Override
    public List<Product_optVO> getOptList(Product_optVO povo) {
        return shopMapper.getOptList(povo);
    }

    @Override
    public List<ProductVO> getOneWithOpt(int pno) {
        return shopMapper.getOneWithOpt(pno);
    }
}
