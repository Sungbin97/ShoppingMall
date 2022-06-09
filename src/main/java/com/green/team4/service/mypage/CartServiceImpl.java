package com.green.team4.service.mypage;

import com.green.team4.mapper.mypage.CartMapper;
import com.green.team4.vo.mypage.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartMapper cartMapper;

    @Override
    public String register(CartVO cartVO) {
        log.info("CartService => register 실행 => 받은 cartVO: "+cartVO);
        List<CartVO> overlapCartList = new ArrayList<>();
        try{
            // 옵션이 없을때 중복데이터 검토
            if(cartVO.getCColor() ==null && cartVO.getCOption() == null && cartVO.getCOption2() ==null){
                List<CartVO> cartList = cartMapper.getAll(cartVO.getMno()); // 해당 회원 장바구니 다 가져오기
                //System.out.println(cartList);
                overlapCartList = cartList.stream().filter(cart->( // 하나씩 꺼내서 중복 List 산출
                        cart.getPno() == cartVO.getPno()
                )).collect(Collectors.toList());
            }
            else{
                // 옵션이 존재할때 중복 데이터 검토
                List<CartVO> cartList = cartMapper.getAll(cartVO.getMno()); // 해당 회원 장바구니 다 가져오기
                System.out.println(cartList);
                overlapCartList = cartList.stream().filter(cart->( // 하나씩 꺼내서 중복 List 산출
                        cart.getPno() == cartVO.getPno()
                                && cart.getCOption().equals(cartVO.getCOption())
                                && cart.getCOption2().equals(cartVO.getCOption2())
                                && cart.getCColor().equals(cartVO.getCColor())
                )).collect(Collectors.toList());
            }
            // 장바구니 신규 추가
            if(overlapCartList.size()==0){ // 중복되는 상품이 없으면
                int result = cartMapper.insert(cartVO);
                log.info("CartService => register 실행 후 등록된 데이터 개수: "+result);
                return "등록 완료되었습니다.";
            }
            else return "이미 장바구니에 추가되어있습니다.";

        }
        catch (Exception e){
            return "이미 장바구니에 추가되어있습니다.";
        }

    }

    @Override
    public List<CartVO> readAll(int mno) { // 장바구니 모두 가져오기(mno단위)
        log.info("CartService => readAll 실행 => 받은 mno: "+mno);
        List<CartVO> result = cartMapper.getAll(mno);
        log.info("CartService => readAll 실행 후 받은 Cart List: "+result);
        return result;
    }

//    @Override
//    public CartVO readOne(int mno, int pno) { // 장바구니 상품 하나 가져오기
//        log.info("CartService => readOne 실행 => 받은 mno: "+mno);
//        log.info("CartService => readOne 실행 => 받은 pno: "+pno);
//        CartVO cartVO = cartMapper.getOneByPnoMno(pno, mno);
//        log.info("CartService => readOne 실행 후 받은 cartVO: "+cartVO);
//        return cartVO;
//    }

    @Override
    public CartVO modify(CartVO cartVO) { // 장바구니 상품 하나 수정
        log.info("CartService => modify 실행 => 받은 cartVO: "+cartVO);
        CartVO cartOne = cartMapper.getOneByCno(cartVO.getCno()); // 기존 장바구니 상품 가져오기

        // 주문 수량 및 상품 주문 금액 업데이트
        cartOne.setCCount(cartVO.getCCount()); // 주문 수량 업데이트
        int pricePerOne = cartOne.getCDisPrice(); // 개당 상품가(할인 후)
        int totalPrice = pricePerOne * cartVO.getCCount(); // 상품 주문금액 수정 (개당 상품가 X 수정된 주문 수량)
        cartOne.setCTotalPrice(totalPrice); // 상품 주문 금액 업데이트
        int result = cartMapper.update(cartOne); // DB 저장
        log.info("CartService => modify 실행 후 수정된 데이터 개수: "+result);

        CartVO updateCart = cartMapper.getOneByCno(cartVO.getCno()); // 수정된 장바구니 상품 가져오기

        return updateCart;
    }

    @Override
    public int remove(int cno) { // 장바구니 삭제
        log.info("CartService => remove 실행 => 받은 cno: "+cno);
        int result = cartMapper.delete(cno);
        log.info("CartService => remove 실행 후 삭제된 개수: "+result);
        return result;
    }
}