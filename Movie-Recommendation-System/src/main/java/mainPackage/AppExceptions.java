package mainPackage;

public class AppExceptions extends RuntimeException {
    private final ErrorCode errorCode;

    public AppExceptions(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}