package com.green.team4.controller.JH;

import com.green.team4.service.JH.ReviewService;
import com.green.team4.service.JH.ShopService;

import com.green.team4.vo.JH.*;
import com.green.team4.vo.sb.ProductVO;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Log4j2
@RequestMapping("/rest")
@RestController
public class RestjhController {

   @Autowired
   private  ReviewService reviewService;

    @Autowired
    private ShopService shopService;

    // review 불러오기
//    @GetMapping("/getreviews/{pno}")
//    public ResponseEntity<List<ReviewVO>> getreviews(@PathVariable ("pno") int pno){
//        log.info("getreviews 입장" );
//        log.info(" p_no " + pno);
//        ResponseEntity<List<ReviewVO>> responseEntity = null;
//        try {
//            responseEntity = new ResponseEntity<>(reviewService.getReviewList(pno), HttpStatus.OK);
//        }catch (Exception e){
//            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
//            e.printStackTrace();
//        }
//        return responseEntity;
//    }
    @GetMapping(value = "/getreviews",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewPageVO> getreviews(ItemPageCriteria cri){
        log.info("getreviews 입장" );
        log.info(" p_no " + cri);
        ResponseEntity<ReviewPageVO> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(reviewService.getReviewWithPaging(cri), HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }

    // 상품 상세 정보 불러오기(상세정보 , 배송정보)
    @GetMapping("/getinfo/{pno}")
    public ResponseEntity<ProductVO> getinfo(@PathVariable ("pno") int pno){
        log.info("getinfo 입장" );
        log.info(" p_no " + pno);
        ResponseEntity<ProductVO> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(shopService.getOne(pno) ,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }
    @GetMapping(value = "/getListBySearch/{keyword}")
    public ResponseEntity<List<ProductVO>> getListBySearch(@PathVariable("keyword") String keyword){
        ResponseEntity<List<ProductVO>> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(shopService.getListBySearch(keyword),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping(value = "/getOptions")
    public ResponseEntity<List<Set<String>>> getOptions( Product_optVO povo){

        log.info("getOptions 입장");
        log.info("povo" +povo);
        System.out.println("optList: "+shopService.getOptList(povo));
        List<Product_optVO> options = shopService.getOptList(povo);
        Set<String> uniqueOpt = new HashSet<>();
        Set<String> uniqueOpt2 = new HashSet<>();
        List<Set<String>> list = new ArrayList<>();
        for(Product_optVO option : options){
            uniqueOpt.add(option.getPOption());
            uniqueOpt2.add(option.getPOption2());
        }
        list.add(uniqueOpt);
        list.add(uniqueOpt2);
        System.out.println(uniqueOpt);
        System.out.println(uniqueOpt2);
        System.out.println(list);
        ResponseEntity<List<Set<String>>> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

//    @PostMapping(value = "/commentLike")
//    public int ReviewLike(@RequestBody ReviewLikeVO dto){
//        System.out.println("입장");
//            int checkLike  = reviewService.checkLike(dto);
//            if(checkLike ==0 ){
//                reviewService.insertLike(dto);
//                reviewService.updateLike(dto.getRno());
//            }
//            else if(checkLike == 1){
//                reviewService.deleteLike(dto);
//                reviewService.updateLikeCancel(dto.getRno());
//            }
//
//        return checkLike;
//    }


}
