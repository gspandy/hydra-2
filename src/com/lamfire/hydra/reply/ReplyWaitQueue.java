package com.lamfire.hydra.reply;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lamfire
 * Date: 14-2-17
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */
public class ReplyWaitQueue {
    private Map<Integer, ReplyFuture> waits = new ConcurrentHashMap<Integer, ReplyFuture>();

    public void add(ReplyFuture future){
        waits.put(future.getMessageId(),future);
    }

    public ReplyFuture get(int messageId){
        return waits.get(messageId);
    }

    public ReplyFuture take(int messageId){
        return waits.remove(messageId);
    }
}
