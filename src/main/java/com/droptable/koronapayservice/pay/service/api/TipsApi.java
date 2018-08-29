package com.droptable.koronapayservice.pay.service.api;

import com.droptable.koronapayservice.pay.dao.Response;
import io.reactivex.Single;

import java.math.BigDecimal;

public interface TipsApi {

    public Single<Response> registerTip(
            String userId, String waiterId, BigDecimal sum, Long timestamp
    );
}
