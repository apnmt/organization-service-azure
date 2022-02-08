package de.apnmt.organization.messaging.consumer;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.OrganizationActivationEventDTO;
import de.apnmt.organization.common.service.OrganizationService;
import de.apnmt.organization.messaging.SubscriptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class OrganizationActivationEventConsumer extends de.apnmt.organization.common.async.controller.OrganizationActivationEventConsumer {

    private final Logger log = LoggerFactory.getLogger(OrganizationActivationEventConsumer.class);

    public OrganizationActivationEventConsumer(OrganizationService organizationService) {
        super(organizationService);
    }

    @Override
    @JmsListener(destination = TopicConstants.ORGANIZATION_ACTIVATION_CHANGED_TOPIC, containerFactory = "topicJmsListenerContainerFactory",
        subscription = SubscriptionConstants.ORGANIZATION_ACTIVATION_CHANGED_SUBSCRIPTION)
    public void receiveEvent(ApnmtEvent<OrganizationActivationEventDTO> message) {
        this.log.info("Received event {} from subscription {}", message, SubscriptionConstants.ORGANIZATION_ACTIVATION_CHANGED_SUBSCRIPTION);
        super.receiveEvent(message);
    }

}
