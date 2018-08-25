package com.droptable.koronapayservice.pay.controller;

import com.droptable.koronapayservice.pay.dao.Response;
import com.droptable.koronapayservice.pay.service.PayService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = {"/main_pay"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("Process main pay")
    public Response processPay(
            @ApiParam("Client's id")
                    String userId,
            @ApiParam("Just a dummy string that represent a restaurant account bill")
                    String restaurantId,
            @ApiParam("Sum of the order")
                    BigDecimal sum
    ) {
        return new Response(HttpStatus.OK.value(), "Pay successful");
    }

    @RequestMapping(value = {"/tip_pay"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("Process tips for waiter")
    public Response processTip(
            @ApiParam("Client's id")
                    String userId,
            @ApiParam("Waiter's id")
                    String waiterId,
            @ApiParam("Sum of a tip to the waiter")
                    BigDecimal sum
    ) {
        payService.processTip(userId, waiterId, sum);
        return new Response(HttpStatus.OK.value(), "Tip has been processed");
    }
}
