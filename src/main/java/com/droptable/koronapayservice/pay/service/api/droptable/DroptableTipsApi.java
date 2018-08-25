package com.droptable.koronapayservice.pay.service.api.droptable;

import com.droptable.koronapayservice.pay.dao.Response;
import com.droptable.koronapayservice.pay.service.api.TipsApi;
import io.reactivex.Single;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DroptableTipsApi implements TipsApi {

    @Override
    public Single<Response> registerTip(String userId, String waiterId, BigDecimal sum, Long timestamp) {
        return Single.just(new Response(HttpStatus.OK.value(), ""));
    }
}
