package com.wang.jmonkey.modules.gauge.utils;

import com.wang.jmonkey.common.model.enums.SexEnum;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: HeJiawang
 * @Date: 2019/9/29
 */
public class GaugeResultUtil {

    /**
     * 取两个数相减的绝对值
     * @param a 数值1
     * @param b 数值2
     * @return 两个数相减的绝对值
     */
    public static int subAbs(int a, int b) {
        return Math.abs(a - b);
    }

    /**
     * 两个数相减的绝对值 是否 大于 某个值
     * @param a 数值1
     * @param b 数值2
     * @param target  是否 大于 某个值
     * @return true 大于
     */
    public static boolean subAbsGt(int a, int b, int target) {
        return  subAbs(a, b) > target;
    }

    public static boolean subAbsLt(int a, int b, int target) {
        return  subAbs(a, b) < target;
    }

    /**
     * 1, 2, 3, 4
     * =>
     * [{scoreB=1, scoreA=2}, {scoreB=1, scoreA=3}, {scoreB=1, scoreA=4}, {scoreB=2, scoreA=3}, {scoreB=2, scoreA=4}, {scoreB=3, scoreA=4}]
     * @param param
     * @return
     */
    public static List<Map<String, Integer>> doubleTurns(Integer... param) {
        List<Map<String, Integer>> result = CollectionUtil.newArrayList();

        if (param.length <= 1) {
            return result;
        }

        List<Integer> paramList = Arrays.asList(param);

        for (int i = 0; i < paramList.size() - 1; i++) {
            int p1 = paramList.get(i);

            List<Integer> p2List = paramList.subList(i + 1, paramList.size());
            p2List.forEach(p2 -> {
                Map<String, Integer> map = CollectionUtil.newHashMap();
                map.put("scoreA", p2);
                map.put("scoreB", p1);

                result.add(map);
            });
        }

        return result;
    }

    public static String renderAge(int age) {
        if (age <= 19) {
            return "-19";
        }

        if (age >= 20 & age <= 29) {
            return "20-29";
        }

        if (age >= 30 & age <= 39) {
            return "30-39";
        }

        if (age >= 40 & age <= 49) {
            return "40-49";
        }

        if (age >= 50 & age <= 59) {
            return "50-59";
        }

        if (age >= 60) {
            return "60-";
        }

        return StringUtils.EMPTY;
    }

    /**
     * 1、纬度类型 P E N L
     * 2、性别
     * 3、年龄段 -19 20-29 30-39 40-49 50-59 60-
     * 4、分数
     */
    public static Map<String, Map<SexEnum, Map<String, Map<Integer, Integer>>>> aksMap = CollectionUtil.newHashMap();

    static {
        List<String> wdList = CollectionUtil.newArrayList("P", "E", "N", "L");
        List<SexEnum> sexList = CollectionUtil.newArrayList(SexEnum.Man, SexEnum.Woman);
        List<String> ageList = CollectionUtil.newArrayList("-19", "20-29", "30-39", "40-49", "50-59", "60-");

        {
            wdList.forEach(wd -> {
                Map<SexEnum, Map<String, Map<Integer, Integer>>> wdMap = CollectionUtil.newHashMap();
                aksMap.put(wd, wdMap);

                sexList.forEach(sex -> {
                    Map<String, Map<Integer, Integer>> sexMap = CollectionUtil.newHashMap();
                    wdMap.put(sex, sexMap);

                    ageList.forEach(age -> {
                        Map<Integer, Integer> ageMap = CollectionUtil.newHashMap();
                        sexMap.put(age, ageMap);
                    });
                });
            });
        }

        List<Integer> scoreList;

        {
            /* p man */
            scoreList = CollectionUtil.newArrayList(
                    40, 40, 45, 45, 45, 50, 50 ,55, 55, 60, 60 , 65, 65, 70, 70, 70 , 75, 75, 80, 80, 85, 85, 90
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Man).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 40, 40, 45, 50, 50, 55, 60, 60, 65, 70, 75, 75, 80, 85, 85, 90, 95, 100, 100, 105, 110, 110
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Man).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 40, 45, 45, 50, 50, 55, 60, 60, 65, 65, 70, 75, 75, 80, 80, 85, 90, 90, 95, 95, 100, 105
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Man).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 40, 45, 50, 55, 55, 60, 65, 70, 75, 75, 80, 85, 90, 90, 95, 100, 105, 110, 110, 115, 120
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Man).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 40, 40, 45, 50, 50, 55, 55, 60, 65, 65, 70, 70, 75, 80, 80, 85, 85, 90, 95, 95, 100, 105
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Man).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 40, 45, 50, 55, 60, 65, 65, 70, 75, 80, 85, 90, 95, 95, 100, 105, 110, 115, 120
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Man).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* p woman */
            scoreList = CollectionUtil.newArrayList(
                    35, 40, 45, 50, 50, 55, 60, 65, 65, 70, 75, 80, 80, 85, 90, 90, 95, 100, 105, 105, 110, 115, 120
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Woman).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    40, 40, 45, 50, 50, 55, 60, 60, 65, 70, 70, 75, 80, 80, 85, 90, 90, 95, 100, 105, 105, 110, 115
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Woman).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    40, 45, 45, 50, 50, 55, 60, 60,65, 65, 70, 75, 75, 80, 80, 85, 90, 90, 95, 95, 100, 105, 105
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Woman).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    40, 45, 50, 50, 55, 60, 65, 70, 75, 75, 80, 85, 90, 95, 100, 100, 105, 110, 115, 120
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Woman).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    40, 45, 50, 50, 55, 60, 60, 65, 70, 70, 75, 80, 80, 85, 90, 95, 95, 100, 105, 105, 110, 115, 120
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Woman).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    40, 45, 50, 55, 55, 60, 65, 70, 75, 75, 80, 85, 90, 95, 100, 100, 105, 110, 115, 120
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("P").get(SexEnum.Woman).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* n man */
            scoreList = CollectionUtil.newArrayList(
                    25, 30, 30, 35, 35, 40, 40, 40, 45, 45, 50, 50, 55, 55, 55, 60, 60, 65, 65, 70, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Man).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    25, 25, 30, 30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 75, 75, 80, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Man).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    25, 30, 30, 35, 35, 40, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Man).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 55, 60, 60, 65, 65, 65, 70, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Man).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 40, 40, 40, 45, 45, 45, 50, 50, 55, 55, 55, 60, 60, 60, 65, 65, 70, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Man).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 40, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75, 75, 80, 80, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Man).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* n woman */
            scoreList = CollectionUtil.newArrayList(
                    25, 30, 30, 35, 35, 40, 40, 40, 45, 45, 50, 50, 50, 55, 55, 60, 60, 65, 65, 65, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Woman).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    25, 25, 30, 30, 35, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Woman).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 30, 35, 35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 55, 60, 60, 65, 65, 65, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Woman).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 35, 40, 40, 40, 45, 45, 45, 50, 50, 50, 55, 55, 55, 60, 60, 65, 65, 65, 70, 70, 70
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Woman).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 55, 60, 60, 60, 65, 65, 70, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Woman).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 30, 35, 35, 40, 40, 40, 45, 45, 50, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 70, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("N").get(SexEnum.Woman).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* e man */
            scoreList = CollectionUtil.newArrayList(
                    25, 25, 30, 30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Man).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 30, 35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 70, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Man).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Man).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Man).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 60, 60, 65, 65, 70, 70, 75, 75, 80, 80, 85
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Man).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Man).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* e woman */
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Woman).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 40, 40, 45, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 65, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Woman).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 40, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Woman).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    35, 35, 40, 40, 45, 45, 50, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 75, 75, 80, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Woman).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 60, 65, 65, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Woman).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 55, 60, 60, 65, 65, 70, 70, 75, 75, 80
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("E").get(SexEnum.Woman).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* l man */
            scoreList = CollectionUtil.newArrayList(
                    30, 35, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 60, 60, 65, 65, 70, 70, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Man).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    20, 25, 25, 30, 30, 35, 35, 40, 40, 45, 50, 50, 55, 55, 60, 60, 65, 70, 70, 75, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Man).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    20, 25, 25, 30, 30, 35, 35, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Man).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    15, 20, 20, 25, 25, 30, 35, 35, 40, 40, 45, 45, 50, 55, 55, 60, 60, 65, 65, 70, 70
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Man).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    15, 20, 20, 25, 30, 30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 65, 65, 70, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Man).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    0, 5, 5, 10, 15, 15, 20, 25, 30, 30, 35, 40, 45, 45, 50, 55, 60, 60, 65, 70
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Man).get("60-").put(i + 1, scoreList.get(i));
            }
        }

        {
            /* l woman */
            scoreList = CollectionUtil.newArrayList(
                    20, 25, 25, 30, 30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65, 70, 70, 75
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Woman).get("-19").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    15, 20, 25, 25, 30, 30, 35, 35, 40, 40, 45, 50, 50, 55, 55, 60, 60, 65, 65
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Woman).get("20-29").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    15, 20, 20, 25, 25, 30, 30, 35, 35, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Woman).get("30-39").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    5, 10, 15, 15, 20, 25, 25, 30, 30, 35, 40, 40, 45, 50, 50, 55, 55, 60, 65, 65
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Woman).get("40-49").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    20, 20, 25, 25, 30, 30, 35, 35, 40, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Woman).get("50-59").put(i + 1, scoreList.get(i));
            }
            scoreList = CollectionUtil.newArrayList(
                    15, 20, 20, 25, 25, 30, 30, 35, 35, 40, 45, 45, 50, 50, 55, 55, 60, 60, 65, 65
            );
            for (int i = 0; i< scoreList.size(); i++) {
                aksMap.get("L").get(SexEnum.Woman).get("60-").put(i + 1, scoreList.get(i));
            }
        }
    }

}
