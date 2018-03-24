package hackatonkm.response;

public class FailResponse implements GeneralResponse {
    private int statusCode;
    private String errorMessage;

    public FailResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
