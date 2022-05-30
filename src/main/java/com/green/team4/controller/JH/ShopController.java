package com.green.team4.controller.JH;

import com.green.team4.service.JH.CategoryService;
import com.green.team4.service.JH.ReviewService;
import com.green.team4.service.JH.ShopService;

import com.green.team4.service.sw.MemberInfoService;
import com.green.team4.vo.JH.CategoryVO;
import com.green.team4.vo.JH.ItemPageCriteria;
import com.green.team4.vo.JH.PagingVO;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@Log4j2
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MemberInfoService memberInfoService;
    @GetMapping("/start")
    public void start(){

    }


    @GetMapping("/list")
    public void listGet( @ModelAttribute("cri") ItemPageCriteria cri, Model model){
        log.info("listget");

        PagingVO pagingVO = new PagingVO();
        System.out.println(cri);
        String code = "";
        if(cri.getTier()==1){
            model.addAttribute("pCateName",categoryService.getCateName(  cri.getPCateCode()));
            code = cri.getPCateCode().substring(0,1);
        }
        else if(cri.getTier()==2){
            model.addAttribute("pCateName",categoryService.getCateName(  cri.getPCateCode()));
            code=cri.getPCateCode().substring(0,3);
            model.addAttribute("cateTier3ListWithCode",categoryService.getCateTier3WithCode(code));
        }
        else if (cri.getTier()==3) {
            model.addAttribute("pCateName",categoryService.getCateName(  cri.getPCateCode()));
            model.addAttribute("cateTier3ListWithCode",categoryService.getCateTier3WithCode(cri.getPCateCode().substring(0,3)));
            code=cri.getPCateCode();
        }
        cri.setPCateCode(code);
        System.out.println("코드 변환후 cri : "+cri);
        pagingVO.setCri(cri);
        pagingVO.setTotalProductData(shopService.getTotaldatabyFind(cri));
        model.addAttribute("list",shopService.getListByFind(cri));
        model.addAttribute("pagingVO",pagingVO);
    }
    @GetMapping("/listAll")
    public String listGet(Model model){
        model.addAttribute("list",shopService.getListAll());


        return "shop/list";
    }


    // 상품 상세페이지
    @GetMapping("/read")
    public void read(int pno,int mno, Model model){
        log.info("read");
        log.info("pno : "+pno);
       log.info("mno : " + mno);
        ProductVO pvo = shopService.getOne(pno);

        model.addAttribute("member",memberInfoService.readOne(mno));
        model.addAttribute("relatedList",shopService.getListByRand( pvo.getPCateCode().substring(0,3)));
        System.out.println(shopService.getListByRand( pvo.getPName()));
        model.addAttribute("pvo",pvo);

    }


    @GetMapping("/orderCompleted")
    public void orderCompleted(int p_no , Model model){


    }
}
