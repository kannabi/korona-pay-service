package com.droptable.koronapayservice.pay.controller;

import com.droptable.koronapayservice.pay.dao.Response;
import com.droptable.koronapayservice.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = {"/main_pay"}, method = RequestMethod.POST)
    @ResponseBody
    public Response processPay(String restaurantId, BigDecimal sum) {
        return new Response(HttpStatus.OK.value(), "Pay successful");
    }

    @RequestMapping(value = {"/tip_pay"}, method = RequestMethod.POST)
    @ResponseBody
    public Response processTip(String userId, String waiterId, BigDecimal sum) {
        payService.processTip(userId, waiterId, sum);
        return new Response(HttpStatus.OK.value(), "Tip has been processed");
    }
}
