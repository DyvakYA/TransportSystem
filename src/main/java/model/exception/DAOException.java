package model.exception;

/**
 * Created by Dyvak on 23.01.2017.
 */
public class DAOException  extends ApplicationException {

        public DAOException(){
            super(DAOErrorMessageKey.DAO_ERROR);
        }

        public DAOException(Exception cause) {
            super(DAOErrorMessageKey.DAO_ERROR, cause);
        }

        @Override
        public DAOException addLogMessage(String logMessage) {
            super.addLogMessage(logMessage);
            return this;
        }

        @Override
        public DAOException addMessageKey(String messageKey) {
            super.addMessageKey(messageKey);
            return this;
        }

        @Override
        public DAOException addAdditionalMessage(String additionalMessage) {
            super.addAdditionalMessage(additionalMessage);
            return this;
        }
    }

