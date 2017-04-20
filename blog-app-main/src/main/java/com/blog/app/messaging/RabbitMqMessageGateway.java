package com.blog.app.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import com.blog.app.common.elasticsearch.event.EsPostSynchEvent;

@MessagingGateway
public interface RabbitMqMessageGateway {
	@Gateway(requestChannel = "worksChannel")
	void generate(EsPostSynchEvent esPostSynchEvent);

}
