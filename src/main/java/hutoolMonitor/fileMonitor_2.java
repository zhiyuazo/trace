package hutoolMonitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.lang.Console;

public class fileMonitor_2 {
	public static void main(String[] args) {
		File file = FileUtil.file("D:\\");
		WatchMonitor.createAll(file, new SimpleWatcher(){
		    @Override
		    public void onModify(WatchEvent<?> event, Path currentPath) {
		        Console.log("EVENT onModify");
		    }
			@Override
			public void onCreate(WatchEvent<?> event, Path currentPath) {
		        Console.log("EVENT onCreate");
			}
			@Override
			public void onDelete(WatchEvent<?> event, Path currentPath) {
		        Console.log("EVENT onDelete");
			}
			@Override
			public void onOverflow(WatchEvent<?> event, Path currentPath) {
		        Console.log("EVENT onOverflow");
			}
		}).start();
	}

}
