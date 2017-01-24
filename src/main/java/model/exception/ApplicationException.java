package model.exception;

/**
 * Created by Dyvak on 23.01.2017.
 */
public class ApplicationException extends RuntimeException {

        private String messageKey;

        private String additionalMessage;

        private String logMessage;

        public ApplicationException() {
        }

        protected ApplicationException(String messageKey, Throwable cause) {
            super(cause);
            this.messageKey = messageKey;
        }

        protected ApplicationException(String messageKey) {
            this.messageKey = messageKey;
        }

        protected ApplicationException addLogMessage(String logMessage) {
            this.logMessage = logMessage;
            return this;
        }

        protected ApplicationException addMessageKey(String messageKey) {
            this.messageKey = messageKey;
            return this;
        }

        protected ApplicationException addAdditionalMessage(String additionalMessage) {
            this.additionalMessage = additionalMessage;
            return this;
        }

        public String getAdditionalMessage() {
            return additionalMessage;
        }

        public String getLogMessage() {
            return logMessage;
        }

        public String getMessageKey() {
            return messageKey;
        }
    }

