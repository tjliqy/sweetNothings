package common.Exception;

/**
 * Created by tjliqy on 2017/6/26.
 */
public class JsonException extends RuntimeException {
    int errNumber = -1;
    String errMsg = "";

    public JsonException(int errNumber,String errMsg){
        this.errNumber = errNumber;
        this.errMsg = errMsg;
    }

    public JsonException(String errMsg){
        this(404,errMsg);
    }

    public int getErrNumber() {
        return errNumber;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
