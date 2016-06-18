package xgen.mobiroo.com.mobirooapp;

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
public abstract class RequestBase <Y extends ResponseBase>{

    public RequestBase(){}

    public abstract String getUri();
    public abstract String getRequestMethodType();

    public abstract Y getResponse();
}
