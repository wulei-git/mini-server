package com.init.mini.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.init.mini.common.util.RedisUtil;
import com.init.mini.web.constant.GoodsConstant;
import com.init.mini.web.dto.GoodPO;
import com.init.mini.web.dto.GoodsTargetRedisPO;
import com.init.mini.web.entity.Goods;
import com.init.mini.web.mapper.GoodsMapper;
import com.init.mini.web.service.GoodsService;
import com.init.mini.web.util.HistoryDateUtil;
import com.init.mini.web.util.JSONFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    public final static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);


    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Integer insertBatch() {
        goodsMapper.deleteAll();
        List<String> deptList = new ArrayList<>();
        for (int i=1; i < 5; i++) {
            deptList.add(String.valueOf(i));
        }
        List<String> planList = new ArrayList<>();
        for (int i=1; i < 20; i++) {
            planList.add(new StringBuilder().append("plan").append(i).toString());
        }
        List<String> targetList = new ArrayList<>();
        for (int i=1; i < 5; i++) {
            targetList.add(new StringBuilder().append("target").append(i).toString());
        }

        List<String> yearList = new ArrayList<>();
        for (int i=2020; i <= 2021; i++) {
            yearList.add(String.valueOf(i));
        }

        List<String> monthList = new ArrayList<>();
        for (int i=1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }

        List<Integer> workAgeList = new ArrayList<>();
        for (int i=1; i <= 16; i++) {
            workAgeList.add(i);
        }
        int count=0;
        for (String s1 : deptList) {
            for (String s2 : planList) {
                for (String s3 : targetList) {
                    for (Integer s4: workAgeList) {
                        for (String s5 : yearList) {
                            for (String s6 : monthList) {
                                Goods goods = new Goods();
                                goods.setDeptCode(s1);
                                goods.setPlanName(s2);
                                goods.setTargetType(s3);
                                goods.setWorkAge(s4);
                                goods.setYear(s5);
                                goods.setMonth(s6);
                                goods.setDeptLevel("0");
                                goods.setCreatedBy("wulei");
                                goods.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                                goods.setUpdatedBy("wulei");
                                goods.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                                goods.setTargetValue(new BigDecimal(Math.random()*9527808).setScale(2, BigDecimal.ROUND_HALF_UP));
                                int i = goodsMapper.insertSelective(goods);
                                if (i==1) count++;
                            }
                        }
                    }
                }
            }
        }
        log.info("count{}=", count);
        return count;
    }

    @Override
    public Integer deleteAll() {
        return goodsMapper.deleteAll();
    }

    @Override
    public void execute() {
        // 1.获取时间列表（去年+今年发生月份）
        List<String> hisDateList = HistoryDateUtil.hisDateList;
        // 2.机构列表
        List<String> deptList = GoodsConstant.DEPT;

        // 3.险种列表
        List<String> planList = GoodsConstant.PLAN;
        // 4.指标列表
        List<String> targetList = GoodsConstant.TARGET;

        for (String dept : deptList) {
            Map<String, String> goodsMap = new HashMap<>();
            for (String plan : planList) {
                List<Goods> goodsList = goodsMapper.selectByDeptAndPlan(dept, plan);
                executeSave(goodsList, targetList, hisDateList, dept, plan,goodsMap);
            }

            redisUtil.setList(goodsMap);
        }
    }

    private void executeSave(List<Goods> goodsList,
                             List<String> targetList,
                             List<String> hisDate,
                             String dept,
                             String plan,
                             Map<String, String> goodsMap) {
        List<GoodsTargetRedisPO> goodsTargetRedisPOS = new ArrayList<>();
        for (String target : targetList) {
            List<Goods> curTargetData = goodsList.stream().filter(
                    p->p.getTargetType().equals(target)
            ).collect(Collectors.toList());

            GoodPO[][] goodPOS = getRedisData(hisDate, curTargetData);
            GoodsTargetRedisPO targetRedisPO = new GoodsTargetRedisPO();
            targetRedisPO.setTargetType(target);
            targetRedisPO.setGoodPOS(goodPOS);

            goodsTargetRedisPOS.add(targetRedisPO);
        }

        JSONObject jsonObject = new JSONObject();
        try {
            for (GoodsTargetRedisPO goodsTargetRedisPO : goodsTargetRedisPOS) {
                JSONObject json = JSONFormatUtil.twoArrToJSON(goodsTargetRedisPO.getGoodPOS());
                jsonObject.put(goodsTargetRedisPO.getTargetType(), json);
            }
            goodsMap.put(new StringBuilder().append(dept).append(":").append(plan).append(":").toString(), jsonObject.toJSONString());
        } catch (Exception e) {
            log.error("error");
        }

    }

    private GoodPO[][] getRedisData(List<String> hisDate,
                                    List<Goods> goodsList) {
        GoodPO[][] arr = new GoodPO[hisDate.size()][17];

        for (int i=0; i<hisDate.size(); i++) {
            String date = hisDate.get(i);
            List<Goods> curDateData = goodsList.stream().filter(
                    p->date.equals(new StringBuilder().append(p.getYear()).append("-").append(p.getMonth()).toString())
            ).collect(Collectors.toList());
            packAge(arr, i, curDateData);
        }

        return arr;
    }

    private void packAge(GoodPO[][] arr,
                         int month,
                         List<Goods> goodsList) {
        for (int i=0; i<17; i++) {
            for (Goods goods : goodsList) {
                if (goods !=null && i==goods.getWorkAge()) {
                    GoodPO goodPO = new GoodPO();
                    goodPO.setMonth(goods.getMonth());
                    goodPO.setPlanName(goods.getPlanName());
                    goodPO.setTargetType(goods.getTargetType());
                    goodPO.setTargetValue(goods.getTargetValue());
                    goodPO.setWorkage(goods.getWorkAge());
                    goodPO.setYear(goods.getYear());

                    arr[month][i] = goodPO;
                }
            }
        }
    }
}
