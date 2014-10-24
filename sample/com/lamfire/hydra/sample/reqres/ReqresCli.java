package com.lamfire.hydra.sample.reqres;

import com.lamfire.hydra.reply.ReplySnake;
import com.lamfire.logger.Logger;

public class ReqresCli {
	private static final Logger LOGGER = Logger.getLogger(ReqresCli.class);


	static void usage(){
		System.out.println("com.lamfire.nkit.sample.ClientSample [host] [port] [connsize]");
	}

	public static void main(String[] args) throws Exception {
		String host = "127.0.0.1";
		int port = 8000;
		int size = 1;
		
		if(args.length > 0 && "?".equals(args[0])){
			usage();
			return;
		}
		
		if(args.length == 3){
			host = args[0];
			port = Integer.valueOf(args[1]);
			size = Integer.valueOf(args[2]);
		}
		
		LOGGER.info("[startup]:"+host + ":" +port  +" - "+ size);
		ReplySnake cli = new ReplySnake(host,port);
		cli.setKeepAlive(true);
        cli.setKeepaliveConnsWithClient(4);
        cli.setAutoConnectRetry(true);
		cli.connect();

		for(int i=0;i<10000000;i++){
			byte[] bytes = cli.send(String.valueOf(i).getBytes());
			System.out.println(new String(bytes));
		}
	}

	
}
