package rikkei.academy.dto.response;

public class ResponseMessage {
    private String messenger;

    public ResponseMessage() {
    }

    public ResponseMessage(String messenger) {
        this.messenger = messenger;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }
}
