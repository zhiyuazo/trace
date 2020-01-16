package thread;

import java.util.concurrent.ThreadFactory;

import cn.hutool.core.lang.UUID;
public class ThreadFactorySelf implements  ThreadFactory{
		@Override
		public Thread newThread(Runnable arg0) {
			Thread tmp = new Thread(arg0);
			tmp.setName("zzy-" + UUID.randomUUID());
			return tmp;
		}
}
