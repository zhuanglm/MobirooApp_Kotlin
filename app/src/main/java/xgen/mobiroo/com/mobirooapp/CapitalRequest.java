package xgen.mobiroo.com.mobirooapp;

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
public class CapitalRequest extends RequestBase<CapitalResponse> {

    @Override
    public String getUri() {
        return Constants.COUNTRY_CAPITAL;
    }

    @Override
    public String getRequestMethodType() {
        return "GET";
    }

    @Override
    public CapitalResponse getResponse() {
        return new CapitalResponse();
    }
}
