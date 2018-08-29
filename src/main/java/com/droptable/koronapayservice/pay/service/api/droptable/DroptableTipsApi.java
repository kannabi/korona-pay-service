package com.droptable.koronapayservice.pay.service.api.droptable;

import com.droptable.koronapayservice.pay.dao.Response;
import com.droptable.koronapayservice.pay.service.api.TipsApi;
import io.reactivex.Single;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class DroptableTipsApi implements TipsApi {

    private TipsServiceApi api;

    @PostConstruct
    private void init() {
        api = new Retrofit.Builder()
                .baseUrl("http://178.62.2.36:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TipsServiceApi.class);
    }

    @Override
    public Single<Response> registerTip(String userId, String waiterId, BigDecimal sum, Long timestamp) {
        return api.registerTip(waiterId, sum, timestamp);
    }

    private interface TipsServiceApi {
        @POST("/tips/tip_pay")
        public Single<Response> registerTip(
                @Query("waiterId") String waiterId,
                @Query("sum") BigDecimal sum,
                @Query("timestamp") Long timestamp
        );
    }
}
