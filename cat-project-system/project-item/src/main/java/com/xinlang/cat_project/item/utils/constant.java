package com.xinlang.cat_project.item.utils;

public class constant {

    public static class ItemUserType {
        /**
         * 主持人
         */
        public static final Integer COMPERE = 0;
        /**
         * 组长
         */
        public static final Integer GROUP_LEADER = 1;
        /**
         * 组员
         */
        public static final Integer GROUP_members = 2;
    }

    public static class ItemStatus {
        /**
         * 废弃
         */
        public static final Integer DISCARD = -1;
        /**
         * 新建
         */
        public static final Integer  NEW= 0;
        /**
         * 基本信息审核
         */
        public static final Integer BASICS_CHECK = 1;
        /**
         * 基本信息审核
         */
        public static final Integer PROCEED = 2;
    }

}
