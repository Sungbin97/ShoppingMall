package com.green.team4.controller.sb;

import com.green.team4.service.sw.DeliveryService;
import com.green.team4.service.sw.ExchangeService;
import com.green.team4.service.sw.OrderService;
import com.green.team4.vo.sw.DeliveryVO;
import com.green.team4.vo.sw.ExchangeVO;
import com.green.team4.vo.sw.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/sb/order/*")
@RequiredArgsConstructor
public class AdOrderController {

    // 의존성 주입
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final ExchangeService exchangeService;


    // Order -----------------------------------------------------------------------------------------

    @GetMapping("/list")
    public void orderList(){ // 주문목록 모두 가져오기 int mno, Model model

        // 주문 목록 가져오기
//        log.info("OrderController => orderList(GET) 실행 => 받은 mno: "+mno);
//        List<OrderVO> orderList = orderService.readAll(mno);
//        model.addAttribute("orderList",orderList);
//        model.addAttribute("mno",mno);
    }

    @GetMapping("/read")
    public void readOne(int ono, Model model){ // 주문 세부내용 가져오기
        log.info("OrderController => readOne(GET) 실행 => 받은 ono: "+ono);
        OrderVO orderVO = orderService.readOne(ono);
        model.addAttribute("orderVO",orderVO);
    }

    // Delivery -----------------------------------------------------------------------------------------

    @GetMapping("/delivery")
    public void readOneDelivery(int ono, Model model){ // 배송조회 화면 가져오기
        log.info("OrderController => readOneDelivery(GET) 실행 => 받은 ono: "+ono);
        DeliveryVO deliveryVO = deliveryService.readOne(ono);
        model.addAttribute("deliveryVO",deliveryVO);
    }

    // Exchange -----------------------------------------------------------------------------------------

    @GetMapping("/exchange")
    public void exRegister(int mno,int ono, int p_no, int pno, Model model){ // 취소/반품/교환 등록 페이지 가져오기
        log.info("OrderController => exRegister(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => exRegister(GET) 실행 => 받은 ono: "+ono);
        log.info("OrderController => exRegister(GET) 실행 => 받은 p_no: "+p_no);
        log.info("OrderController => exRegister(GET) 실행 => 받은 pno: "+pno);

        model.addAttribute("mno",mno);
        model.addAttribute("ono",ono);

        model.addAttribute("p_no",p_no);
        model.addAttribute("pno",pno);
    }

    @PostMapping("/exchange")
    public String exRegister(ExchangeVO exchangeVO){
        log.info("OrderController => exRegister(POST) 실행 => 받은 exchangeVO: "+exchangeVO);

        // 주문서 내 취소/반품/교환 신청 여부 내역 수정
        OrderVO orderVO = orderService.readOne(exchangeVO.getOno());
        orderVO.setExStatus(true);
        orderService.modify(orderVO);

        // 취소/반품/교환 신청 데이터 신규추가
        exchangeVO.setExStartDate(LocalDateTime.now());
        exchangeService.register(exchangeVO);
        return "redirect:/sw/mypage/order/list?mno="+exchangeVO.getMno();
    }



}