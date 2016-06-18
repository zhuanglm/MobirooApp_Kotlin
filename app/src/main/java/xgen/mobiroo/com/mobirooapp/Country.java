package xgen.mobiroo.com.mobirooapp;

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
public class Country {
    private String m_short_name;
    private String m_full_name;

    public void setShortName(String name){
        m_short_name = name;
    }

    public void setfullName(String name){
        m_full_name = name;
    }

    public String getShortName(){
        return m_short_name;
    }

    public String getFullName(){
        return m_full_name;
    }
}
