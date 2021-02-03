package entity;

import java.io.Serializable;

public class Result implements Serializable {

    private boolean success;  //前端判断该属性做逻辑
    private String message;  //前端显示该信息

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
