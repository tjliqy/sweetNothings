package common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import common.Exception.JsonException;
import common.JsonBaseController;

/**
 * Created by tjliqy on 2017/6/26.
 */
public class JsonInterceptor implements Interceptor {
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        controller.setAttr("err",-1);
        controller.setAttr("errMsg","");
        controller.setAttr("body",null);
        try {
            inv.invoke();
            if (controller instanceof JsonBaseController){
                controller.setAttr("body",((JsonBaseController) controller).getBody());
            }
        }catch (Exception e){
            e.printStackTrace();
            controller.setAttr("err",e instanceof JsonException ?((JsonException)e).getErrNumber():500);
            controller.setAttr("errMsg",e instanceof JsonException?((JsonException)e).getErrMsg():"Unknown error");
        }finally {
            controller.renderJson();
        }
    }
}
