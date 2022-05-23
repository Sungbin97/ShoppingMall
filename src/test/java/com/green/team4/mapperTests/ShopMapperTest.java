package com.green.team4.mapperTests;

import com.green.team4.mapper.JH.ShopMapper;
import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.PagingVO;

import com.green.team4.vo.sb.ProductVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ShopMapperTest {

    @Autowired
    private ShopMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(ShopMapperTest.class);
    @Test
    public void testGetList(){

        System.out.println(mapper.getListAll());
    }

    @Test
    public void testGetList2(){


    }


    @Test
    public void testInsert(){
        String[] maincategories = {"의류","식품","운동용품"};
        String[] clothes = {"티셔츠","셔츠","기능성티","반팔티","런닝복"};
        String[] food = {"비타민C","비타민D","종합 비타민","오메가3","아르기닌"};
        String[] equipment = {"벤치프레스","로잉머신","철봉","런닝머신","덤벨"};
        String[] cjpgs = {"clothes0.jpg","clothes1.jpg","clothes2.jpg","clothes3.jpg"};
        String[] fjpgs = {"food0.jpg","food1.jpg","food2.jpg","food3.jpg"};
        String[] ejpgs = {"equipment0.jpg","equipment1.jpg","equipment2.jpg","equipment3.jpg"};
        String[] sizes = {"XXL","XL","L","M","S"};
        String[] colors = {"red","green","blue","white","black"};
        for (int i = 0; i < 200; i++) {
            ProductVO pvo = new ProductVO();
            int randidx = (int)(Math.random()*maincategories.length);
            pvo.setPMainCategory(maincategories[randidx]);

           switch (pvo.getPMainCategory()){

               case "의류" : pvo.setPSubCategory(clothes[(int)(Math.random()*clothes.length)]);
                            pvo.setPImage( "/images/"+cjpgs[(int)(Math.random()*cjpgs.length)]);
//                            pvo.setp(sizes[(int)(Math.random()*sizes.length)]);
//                            pvo.setP_color(colors[(int)(Math.random()*colors.length)]);
                   break;
               case "식품" : pvo.setPSubCategory(food[(int)(Math.random()*food.length)]);
                            pvo.setPImage("/images/"+fjpgs[(int)(Math.random()*fjpgs.length)] );
                   break;
               case "운동용품" : pvo.setPSubCategory(equipment[(int)(Math.random()*equipment.length)]);
                             pvo.setPImage("/images/"+ejpgs[(int)(Math.random()*ejpgs.length)] );
//                             pvo.setP_color(colors[(int)(Math.random()*colors.length)]);
                   break;


           }
            pvo.setPPrice((int)(Math.random()*11100)+1000);
            pvo.setPAmount((int)(Math.random()*100)+1);

            pvo.setPName("상품"+i);

            pvo.setPInformation("형 믿고 사");
            pvo.setPDelivery("무료배송");

           mapper.insert(pvo);
        }
    }
    @Test
    public void testGetone(){
        System.out.println(mapper.getOne(20));


    }
    @Test void testPage(){
        ItemPageCriteria cri = new ItemPageCriteria();

        PagingVO pagingVO = new PagingVO();
        pagingVO.setCri(cri);
        Map<String ,Object> map = new HashMap<>();
        map.put("p_cateCodeRef","201");
        map.put("pagingVO",pagingVO);
        System.out.println(mapper.getListByCategoryAndPage(map));
    }

    @Test
    public void testtotalcnt(){
        System.out.println(mapper.getTotalProductCountbyCategory("프로틴"));
    }
    @Test
    public void testGetCategory(){
        List<ProductVO> list =mapper.getCategory("프로틴");
        System.out.println(list);
        System.out.println("갯수 : "+list.size());
    }

    @Test
    public void  testFindList(){

        ItemPageCriteria cri = new ItemPageCriteria();

        cri.setMain_category("상의");
        //cri.setSub_category("잠옷");
        mapper.getTotaldatabyFind(cri);
        System.out.println(mapper.getListByFind(cri));






    }
    @Test
    public void  testFindcount(){

        ItemPageCriteria cri = new ItemPageCriteria();
        // fCri.setMain_category("식품");
        cri.setMain_category("상의");
        //fCri.setSub_category2("프로틴바");
        System.out.println(mapper.getTotaldatabyFind(cri));

    }
}
