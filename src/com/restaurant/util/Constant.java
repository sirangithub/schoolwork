package com.restaurant.util;

import java.io.Serializable;

/**
 * @author zhangrong
 * 定义常量
 */

public class Constant implements Serializable {
    //定义性别
    public static final String MALE="男";
    public static final String FEMALE="女";

    //定义职务
    public static final String WAITER="服务员";
    public static final String CASHIER="收银员";
    public static final String CLEANER="保洁员";
    public static final String SECYRUTY_STAFF="保安";
    public static final String COOK="厨师";
    public static final String DOTR="主管";

    //定义员工是否在职
    public static final String ON_FREEZE="在职";
    public static final String OFF_FREEZE="离职";

    //定义菜品状态
    public static final String UNSOLD_OUT="未售完";
    public static final String SOLD_OUT="已售完";

    //定义餐台状态
    public static final String UNBOOKED="未预订";
    public static final String BOOKED="已预订";
    public static final String DTM="就餐中";
    public static final String EMPTY="空闲中";
    //public static final String CHECKOUT="已结账";

    //订单状态
    public static final String PAID="已支付";
    public static final String UNPAID="未支付";

    //选项及提示
    public static final String ADD="添加";
    public static final String DELETE="删除";
    public static final String SAVE="保存";
    public static final String SURE="确定删除?";
    public static final String OPTION="请选择要删除的行！";
    public static final String USUCCESS="更新成功";
    public static final String OK="确定";
    public static final String CANCER="取消";
   public static final String ASUCCESS="添加成功";
}
