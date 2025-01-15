 /**
     * 审核状态枚举接口
     */
    @RequestMapping(value = "/AuditStatus", method = RequestMethod.POST)
    public PagedResult<SelectResult> AuditStatus() throws InvocationTargetException, IllegalAccessException {

        var rs=Arrays.stream(Enums.AuditStatus.values()).map(n->new SelectResult(n.toString(),n.name(),Integer.toString(n.index()),"")).toList();
        return PagedResult.GetInstance(rs,rs.stream().count());
    }
  
  /**
     *审核状态枚举
     */
    public enum AuditStatus
    {
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

        AuditStatus(int index)
        {
            this.index = index;
        }

        public int index() {
            return index;
        }
        private static final HashMap<Integer,AuditStatus> MY_MAP = new HashMap<Integer,AuditStatus>();
        static {
            for (AuditStatus myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
        }
        public static AuditStatus GetEnum(Integer v)
        {
            if(v==null){
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }

    }
