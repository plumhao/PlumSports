package com.lzh.sports.entity;

import java.util.HashMap;

public class Enums {

    /**
     * 角色枚举
     */
    public enum RoleType {
        /**
         * 管理员
         */
        管理员(0),

        /**
         * 用户
         */
        用户(1),
        /**
         * 教练
         */
        教练(2);


        private final int index;

        RoleType(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }


        private static final HashMap<Integer, RoleType> MY_MAP = new HashMap<Integer, RoleType>();

        static {
            for (RoleType myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
        }

        public String toString() {

            return name();
        }

        public static RoleType GetEnum(Integer v) {
            if (v == null) {
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }
    }


    /**
     * 使用状态枚举
     */
    public enum EquipmentStatus {
        /**
         * 可用
         */
        可用(0),

        /**
         * 维修中
         */
        维修中(1),

        /**
         * 报废
         */
        报废(2),

        /**
         * 租借中
         */
        租借中(3);


        private final int index;

        EquipmentStatus(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }

        private static final HashMap<Integer, EquipmentStatus> MY_MAP = new HashMap<Integer, EquipmentStatus>();

        static {
            for (EquipmentStatus myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
        }

        public static EquipmentStatus GetEnum(Integer v) {
            if (v == null) {
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }

    }


    /**
     * 报名状态枚举
     */
    public enum CouseAppointStatus {
        /**
         * 待进行
         */
        待进行(0),

        /**
         * 已签到
         */
        已签到(1),

        /**
         * 用户取消
         */
        用户取消(2),

        /**
         * 违约
         */
        违约(3),

        /**
         * 完成
         */
        完成(4);


        private final int index;

        CouseAppointStatus(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }

        private static final HashMap<Integer, CouseAppointStatus> MY_MAP = new HashMap<Integer, CouseAppointStatus>();

        static {
            for (CouseAppointStatus myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
        }

        public static CouseAppointStatus GetEnum(Integer v) {
            if (v == null) {
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }

    }


    /**
     * 审核状态枚举
     */
    public enum AuditStatus {
        /**
         * 待审核
         */
        待审核(0),

        /**
         * 审核通过
         */
        审核通过(1),

        /**
         * 审核失败
         */
        审核失败(2);


        private final int index;

        AuditStatus(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }

        private static final HashMap<Integer, AuditStatus> MY_MAP = new HashMap<Integer, AuditStatus>();

        static {
            for (AuditStatus myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
        }

        public static AuditStatus GetEnum(Integer v) {
            if (v == null) {
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }

    }

    /**
     *处理状态枚举
     */
    public enum ProcessingStatus
    {
        /**
         * 待处理
         */
        待处理(0),

        /**
         * 处理中
         */
        处理中(1),

        /**
         * 处理完成
         */
        处理完成(2);


        private final int index;

        ProcessingStatus(int index)
        {
            this.index = index;
        }

        public int index() {
            return index;
        }
        private static final HashMap<Integer,ProcessingStatus> MY_MAP = new HashMap<Integer,ProcessingStatus>();
        static {
            for (ProcessingStatus myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
        }
        public static ProcessingStatus GetEnum(Integer v)
        {
            if(v==null){
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }

    }

}
