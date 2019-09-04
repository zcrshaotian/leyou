package ly.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResult {

    private int Code;
    private String Message;
    private Long timeStamp;

    public ExceptionResult(int code, String Message) {
        this.Code = code;
        this.Message = Message;
        timeStamp = System.currentTimeMillis();
    }
}
