package com.droptable.koronapayservice.pay.service;

import com.droptable.koronapayservice.pay.service.api.TipsApi;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.math.BigDecimal;

@Component
public class PayService {

    private final TipsApi tipsApi;
    private final Logger logger;
    private final CompositeDisposable compositeDisposable;

    @Autowired
    public PayService(TipsApi tipsApi) {
        this.tipsApi = tipsApi;
        compositeDisposable = new CompositeDisposable();
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public void processPay(String restaurantId, BigDecimal sum) {

    }

    public void processTip(String userId, String waiterId, BigDecimal sum) {
        compositeDisposable.add(
            tipsApi.registerTip(userId, waiterId, sum, System.currentTimeMillis())
                .subscribeOn(Schedulers.io())
                .retry(3)
                .subscribe(
                    response -> logger.info(response.toString()),
                    error -> logger.trace("ERROR PROCESS TIP", error)
                )
        );
    }

    @PreDestroy
    private void onDestroy() {
        compositeDisposable.dispose();
    }
}
