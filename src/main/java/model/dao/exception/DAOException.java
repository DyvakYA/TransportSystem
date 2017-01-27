package model.dao.exception;

/**
 * Created by Dyvak on 26.01.2017.
 */
public class DAOException extends Throwable {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
