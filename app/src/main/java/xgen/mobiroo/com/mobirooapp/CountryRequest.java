package xgen.mobiroo.com.mobirooapp;

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
public class CountryRequest extends RequestBase<CountryResponse> {

    @Override
    public String getUri() {
        return Constants.INSTANCE.getCOUNTRY_NAME();
    }

    @Override
    public String getRequestMethodType() {
        return "GET";
    }

    @Override
    public CountryResponse getResponse() {
        return new CountryResponse();
    }
}
