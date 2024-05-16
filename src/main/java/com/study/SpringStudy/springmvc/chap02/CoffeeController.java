package com.study.SpringStudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/coffee/*")
public class CoffeeController {
    /**
     * @request-url = /coffee/order
     * @forwarding-jsp : /WEB-INF/views/mvc/coffee-form.jsp
     * @return
     */

    //    @RequestMapping("/order")
    //GET 요청만 받겠다
    @GetMapping("/order")
    public String order(){

        return "mvc/coffee-form";
    }
  // Post 요청만 받겠다
    //method = RequestMethod.POST 걸면 겟이면 안받아줌

//  @RequestMapping(value = "/result",method = RequestMethod.POST)
    //   ㅣㅣ   같은것
    @PostMapping("/result")
    public String result(String menu, int price, Model model){
        //1. 주문 데이터 (menu, price)읽어오기

        // 2. jsp에 보내서 랜더링
        model.addAttribute("mmm",menu);
        model.addAttribute("ppp",price);

        return "mvc/coffee-result";
    }
}
