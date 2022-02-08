package de.apnmt.organization.messaging.sender;

import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.ClosingTimeEventDTO;
import de.apnmt.common.sender.ApnmtEventSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClosingTimeEventSender implements ApnmtEventSender<ClosingTimeEventDTO> {

    private static final Logger log = LoggerFactory.getLogger(ClosingTimeEventSender.class);

    private JmsTemplate jmsTemplate;

    public ClosingTimeEventSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String topic, ApnmtEvent<ClosingTimeEventDTO> event) {
        log.info("Send event {} to topic {}", event, topic);
        jmsTemplate.convertAndSend(topic, event);
    }

}
