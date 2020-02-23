package com.omdbapi.common;


import org.apache.log4j.Logger;

public class TestLogger implements ITestLogger{
    private static Logger logger=Logger.getLogger(TestLogger.class);

    public  void info(String message)
    {
        logger.info(message);
    }
    public void info(Object... messages)
    {
        StringBuilder stringBuilder=new StringBuilder();
        for (Object message:messages){
            stringBuilder.append(message);
            stringBuilder.append(" ");
        }
        logger.info(stringBuilder);
    }
}
