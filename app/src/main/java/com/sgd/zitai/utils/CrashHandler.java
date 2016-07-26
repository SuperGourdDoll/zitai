package com.sgd.zitai.utils;

import android.content.Context;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler instance;  //单例引用，这里我们做成单例的，因为我们一个应用程序里面只需要一个UncaughtExceptionHandler实例
	private Context myContext;
    
    private CrashHandler(){}
    
    public synchronized static CrashHandler getInstance(){  //同步方法，以免单例多线程环境下出现异常
        if (instance == null){
            instance = new CrashHandler();
        }
        return instance;
    }
    
    public void init(Context ctx){  //初始化，把当前对象设置成UncaughtExceptionHandler处理器
    	this.myContext = ctx;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    
    @Override
    public void uncaughtException(Thread thread, Throwable exception) {  //当有未处理的异常发生时，就会来到这里。。 
    	StringWriter stackTrace = new StringWriter();  
        exception.printStackTrace(new PrintWriter(stackTrace));  
        System.err.println(stackTrace);  
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);  
    }
}