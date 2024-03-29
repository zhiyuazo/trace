package com.zzy.trace.hutoolMonitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;

public class fileMonitor_1 {
	
	public static void main(String[] args) {
		File file = FileUtil.file("D:\\file_dir_1\\");
		//这里只监听文件或目录的修改事件
		WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.EVENTS_ALL);
		watchMonitor.setWatcher(new Watcher(){
		    @Override
		    public void onCreate(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("创建：{}-> {}", currentPath, obj);
		    }

		    @Override
		    public void onModify(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("修改：{}-> {}", currentPath, obj);
		    }

		    @Override
		    public void onDelete(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("删除：{}-> {}", currentPath, obj);
		    }

		    @Override
		    public void onOverflow(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("Overflow：{}-> {}", currentPath, obj);
		    }
		});

		//设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
		watchMonitor.setMaxDepth(100);
		//启动监听
		watchMonitor.start();
	}

}
