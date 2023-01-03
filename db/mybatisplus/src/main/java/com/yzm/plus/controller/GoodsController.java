package com.yzm.plus.controller;

import com.yzm.plus.config.GoodsEnum;
import com.yzm.plus.entity.Goods;
import com.yzm.plus.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Yzm
 * @since 2023/01/03
 */
@Slf4j
@RestController
@RequestMapping("/plus/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/list")
    public List<Goods> list() {
        return goodsService.list();
    }

    @GetMapping("/save")
    public void save() {
        goodsService.save(Goods.builder()
                .name("苹果手机").price(BigDecimal.valueOf(9998.0)).leftNum(100)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build());
    }

    @GetMapping("/save2")
    public void save2() {
        goodsService.save(Goods.builder()
                .name("香蕉手机").price(BigDecimal.valueOf(2998.0)).leftNum(100)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build());
    }

    @GetMapping("/save3")
    public void save3() {
        goodsService.save(Goods.builder()
                .name("雪花洗衣机").price(BigDecimal.valueOf(2100.0)).leftNum(100)
                .status(GoodsEnum.HOT)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build());
    }

    @GetMapping("/save4")
    public void save4() {
        goodsService.save(Goods.builder()
                .name("打火机牌吹风机").price(BigDecimal.valueOf(250.0)).leftNum(100)
                .status(GoodsEnum.UP)
                .build());
    }

    @GetMapping("/removeById")
    public boolean removeById() {
        return goodsService.removeById("10001");
    }

    @GetMapping("/updateById")
    public boolean updateById() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 12; i++) {
            executorService.execute(() -> {
                Goods goods = goodsService.getById("1608734720508051458");
                if (goods.getLeftNum() > 0) {
                    goods.setLeftNum(goods.getLeftNum() - 1);
                    if (goodsService.updateById(goods)) {
                        log.info(Thread.currentThread().getName() + "成功");
                    } else {
                        log.info(Thread.currentThread().getName() + "请稍等");
                    }
                    return;
                }
                log.info(Thread.currentThread().getName() + "库存不足");
            });
            Thread.sleep((long) (Math.random() * 100));
        }
        executorService.shutdown();
        return true;
    }

    @GetMapping("/save5")
    public void save5() {
        goodsService.save(Goods.builder()
                .name("小霸王老虎机").price(BigDecimal.valueOf(338.0)).leftNum(10)
                .status(GoodsEnum.UP)
                .dateUnix(LocalDateTime.now())
                .build());
    }


}
