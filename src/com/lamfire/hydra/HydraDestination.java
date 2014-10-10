package com.lamfire.hydra;

import java.nio.ByteBuffer;

import com.lamfire.hydra.Message;
import com.lamfire.logger.Logger;
import com.lamfire.hydra.Context;
import com.lamfire.hydra.Session;

/**
 * 消息调度目的地
 * @author lamfire
 *
 */
public abstract class HydraDestination extends Hydra implements Destination {
	static final Logger LOGGER = Logger.getLogger(HydraDestination.class);
	private MessageBus bus;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HydraDestination(String host, int port) {
		super(host,port);
	}

    public HydraDestination(String host, int port,MessageBus bus) {
        super(host,port);
        this.bus = bus;
    }

    public HydraDestination(String name, String host, int port) {
        super(host,port);
        this.name = name;
    }

    public HydraDestination(String name, String host, int port,MessageBus bus) {
        super(host,port);
        this.name = name;
        this.bus = bus;
    }

	public void setMessageBus(MessageBus bus){
		this.bus = bus;
	}

	@Override
	public void onMessageReceived(Context context, Session session, Message message) {
		if(bus == null){
			LOGGER.warn("The 'Bus' not found,received message was be igrone.");
			return;
		}
		bus.onReplyMessage(session, message);
        if(LOGGER.isDebugEnabled()){
		    LOGGER.debug("[Dispatcher] onMessageReceived:"+message);
        }
	}



	
}
