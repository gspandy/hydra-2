package com.lamfire.hydra;

import java.net.SocketAddress;
import java.util.Stack;

import com.lamfire.hydra.net.Context;
import com.lamfire.hydra.net.Session;

public class MessageContext {
	private Context context;
	private Session session;
	private Stack<Integer> nodes;
	private Message message;
	
	/**
	 * 创建回复消息
	 * @return
	 */
	public Message makeMessage(){
		Message reply = new Message();
		reply.addAllLinks(nodes);
		return reply;
	}
	
	/**
	 * 创建回复消息
	 * @param id
	 * @param bytes
	 * @return
	 */
	public Message makeMessage(int id,byte[] bytes){
		Message reply = new Message();
		reply.setId(id);
		reply.setBody(bytes);
		reply.addAllLinks(nodes);
		return reply;
	}
	
	public void send(int id,byte[] data){
		session.send(makeMessage(id,data));
	}

	public void send(int id,String data){
		send(id,data.getBytes());
	}

	public int getSessionId(){
		return session.getSessionId();
	}
	
	public SocketAddress  getRemoteAddress(){
		return session.getRemoteAddress();
	}
	
	public SocketAddress getLocalAddress(){
		return session.getLocalAddress();
	}
	
	public Context getContext() {
		return context;
	}
	void setContext(Context context) {
		this.context = context;
	}
	
	public Session getSession() {
		return session;
	}
	
	void setSession(Session session) {
		this.session = session;
	}
	
	public Message getMessage() {
		return message;
	}
	
	void setMessage(Message message) {
		this.message = message;
	}
	
	Stack<Integer> getNodes() {
		return nodes;
	}
	void setNodes(Stack<Integer> nodes) {
		this.nodes = nodes;
	}
}
