package com.pool.events;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
	@Id
	private String id;
	private Date timeStamp;
	private String aggregrateIdentifier;
	private String aggregrateType;
	private int version;
	private String eventType;
	private BaseEvent eventData;
}
