package plus.kuailefeizhaijidi.blog.exception;

/**
 * @author dl
 * @since 2020年03月28日
 */
public class AuthorizeException extends BlogException{
    public AuthorizeException() {
    }

    public AuthorizeException(String message) {
        super(message);
    }

    public AuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizeException(Throwable cause) {
        super(cause);
    }

    public AuthorizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
