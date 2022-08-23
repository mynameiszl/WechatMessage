package demo.wx.mp.pojo;

import lombok.Data;

/**
 * @author lin
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return new Result(200, "success");
    }

    public static <T> Result<T> success(String message) {
        return new Result(200, message);
    }

    /**
     * 失败返回
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        return new Result(100, "fail");
    }

    public static <T> Result<T> fail(String message) {
        return new Result(100, message);
    }

    /**
     * 自定义返回
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildResult(Integer code, String message, T data) {
        return new Result(code, message, data);
    }

    /**
     * 自定义返回不带参数
     * @param code
     * @param message
     * @return
     */
    public static Result buildResult(Integer code, String message) {
        return new Result(code, message);
    }

}

