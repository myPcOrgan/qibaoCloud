package com.qibao.activity.util;

import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.vo.UserPrizeVO;
import com.qibao.activity.exception.RoomException;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * 房间帮助类
 */
public class RoomHelp {

    //房间号前缀
    private static final String PREFIX_ROOM_NO = "";

    //活动号前缀
    private static final String PREFIX_ACTIVITY_NO = "";

    /**
     * 房间号生成
     * @return
     */
    public static String getRoomNo(){
        String id = System.currentTimeMillis()+"";
        id = id.substring(8);
        return PREFIX_ROOM_NO+getRandom(999,100)+id;
    }

    /**
     * 活动号生成(8位随机数)
     * @return
     */
    public static String getActivityNo(){
        String id = System.currentTimeMillis()+"";
        id = id.substring(8);
        return PREFIX_ACTIVITY_NO+getRandom(999,100)+id;
    }

    /**
     * 生成范围内的随机数
     * @param max 最大值
     * @param min 最小值
     * @return
     */
    public static int getRandom(int max, int min){
        Random random = new Random();
        int i = random.nextInt(max) % (max-min+1) + min;
        return i;
    }

    /**
     * 房间开奖算法
     * @param userIds 总人数id
     * @param total 总金额
     * @param numPeople 中奖人数
     */
    public static List<UserPrizeEO> lotify(List<Long> userIds, double total, int numPeople){
        //先奖品分成中奖人数的份数，然后随机抽取中奖人数
        if (userIds == null || userIds.size() <= 0 || total <= 0 || numPeople <= 0){
            throw new RoomException(RoomException.ROOM_LOTIFY_ERROR);
        }
        if (userIds.size() < numPeople){
            numPeople = userIds.size();
        }
        List<UserPrizeEO> lotifys = new ArrayList<UserPrizeEO>();
        if (numPeople == 1 ){   //中奖人数为一个人的时候，随机选择一个
            int random = getRandom(userIds.size() - 1, 0);
            UserPrizeEO prizeVO = new UserPrizeEO();
            prizeVO.setUserId(userIds.get(random));
            prizeVO.setWinGold(total);
            lotifys.add(prizeVO);
        }else {
            int remainPeople = numPeople;
            double remainGold = total;
            Set<Integer> randomNoRepeat = getRandomNoRepeat((userIds.size() - 1), 0, numPeople);
            Iterator<Integer> iterator = randomNoRepeat.iterator();
            List<Integer> randomIds = new ArrayList<>();
            while (iterator.hasNext()){
                randomIds.add(iterator.next());
            }
            for (int i = 0; i < numPeople; i++){
                double randomGold = getRandomMoney(remainPeople, remainGold);
                remainPeople--;
                remainGold = remainGold - randomGold;
                UserPrizeEO prizeVO = new UserPrizeEO();
                prizeVO.setWinGold(randomGold);
                prizeVO.setUserId(userIds.get(randomIds.get(i)));
                lotifys.add(prizeVO);
            }

        }
        return lotifys;
    }

    /**
     * 随机生成不重复的num数
     * @param max   最大值
     * @param min   最小值
     * @param num   生成的数量
     */
    public static Set<Integer> getRandomNoRepeat(int max,int min, int num){

        Set<Integer> set = new HashSet();
        while (set.size() < num){
            int random = getRandom(max, min);
            set.add(random);
        }
        return set;

    }


    /**
     * 模仿微信红包算法
     * @param remainPeople  剩余人数
     * @param remainGold    剩余金币
     * @return
     */
    public static double getRandomMoney(int remainPeople, double remainGold) {
        if (remainPeople == 1) {
            return remainGold;
        }
        Random r     = new Random();
        double min   = 1; //
        double max   = remainGold / remainPeople * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 1: money;
        money = Math.floor(money);
        return money;
    }

    public static String toJsonString(Object o){
        String result = JSONObject.fromObject(o).toString();
        return result;
    }


    public static void main(String[] args) {
        Set<Integer> randomNoRepeat = getRandomNoRepeat(9, 0, 9);
        Iterator<Integer> iterator = randomNoRepeat.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
