package cn.com.fl.service.common.enums;

/**
 * 定义返回状态提醒的枚举
 */
public enum ResultEnums {
    //通用返回结果编码
    RESULT_SUCCESS(0,"操作成功"),
    RESULT_ERROR(-1,"操作异常"),
    RESULT_SYS_BUSY(-2,"系统繁忙，请稍后再试"),
    RESULT_LOGIN_ERROR(-3,"请重新登录"),
    RESULT_LOGIN_EMPTY_ERROR(-4,"账号、密码不能为空"),

    //素材库模块错误编码 -10XX
    RESULT_FTP_UPLOAD_SELECT_ERROR(-1001,"查询数据接口失败"),

    //消息模板库模块错误编码 -11XX
    RESULT_TEMPLATE_CREATE_ERROR(-1101, "创建消息模板失败"),
    RESULT_TEMPLATE_DELETE_ERROR(-1102, "删除消息模板失败"),
    RESULT_TEMPLATE_UPDATE_ERROR(-1103, "修改消息模板失败"),
    RESULT_TEMPLATE_QUERY_ERROR(-1104, "查询消息模板失败"),

    //消息错误编码 -12XX
    RESULT_MSG_SEND_ERROR(-1201, "发送消息失败"),

    RESULT_USER_INFO_ERROR(-1301,"员工编号重复")

    ;

    private Integer resultCode;

    private String resultMassage;


    ResultEnums(Integer resultCode, String resultMassage) {
        this.resultCode = resultCode;
        this.resultMassage = resultMassage;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMassage() {
        return resultMassage;
    }

    public void setResultMassage(String resultMassage) {
        this.resultMassage = resultMassage;
    }


    @Override
    public String toString() {
        return "ResultEnums{" +
                "resultCode=" + resultCode +
                ", resultMassage='" + resultMassage + '\'' +
                '}';
    }
}
