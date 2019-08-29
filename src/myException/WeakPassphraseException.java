package myException;

public class WeakPassphraseException extends Exception{
    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public WeakPassphraseException() {
        super();
    }

    public WeakPassphraseException(String message) {
        super(message);
        msgDes = message;
    }

    public WeakPassphraseException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public void setRetCd(String retCd) {
        this.retCd = retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }
}
