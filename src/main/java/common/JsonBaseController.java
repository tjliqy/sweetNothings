package common;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import common.interceptor.JsonInterceptor;

import java.util.HashMap;

/**
 * Created by tjliqy on 2017/6/26.
 */
@Before(JsonInterceptor.class)
public abstract class JsonBaseController extends Controller {

    HashMap<String,Object> body = new HashMap<String, Object>();

    public void addBody(String key,Object value){
        body.put(key,value);
    }

    public HashMap<String,Object> getBody(){
        return body;
    }
}
