package com.rashmi;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@Getter
public class MakeUpMessageGeneratorImpl implements MakeUpMessageGenerator{

   /* Same Field values used in ConstantsForMessages class to be able to use a single method to get all error messages from getMessageInfo.
   Field values used by getMessage(String code, Object... args) to get messages from messages.properties

    public static final String ERROR_MESSAGE = "makeUp.error.message";

    public static final String BOOKING_ERROR_MESSAGE = "makeUp.error.message.booking";

    public static final String REGISTER_ERROR_MESSAGE = "makeup.error.message.register";

    public static final String ERROR_MESSAGE_SESSION_TIMEDOUT = "makeup.error.message.session.timed.out";

    public static final String NO_BOOKED_APPOINTMENTS_MESSAGE ="no.booked.appointments.message";
    */

    private  final MakeUp makeUp;

    private final MessageSource messageSource;

    @Autowired
    public MakeUpMessageGeneratorImpl(MakeUp makeUp, MessageSource messageSource) {
        this.makeUp = makeUp;
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {

        log.debug("Makeup object value {} " + makeUp);

    }


    @Override
    public String getMessageInfo(String message) {
        return getMessage(message);
    }

    // == private methods ==
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
