package app.mvc.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Listener {

    @JmsListener(destination = "${com.endava.inbound.queue}")
    public void receive(String message) {
        log.info("received message='{}'", message);
    }

}

