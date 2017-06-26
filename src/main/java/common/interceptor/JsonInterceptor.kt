package common.interceptor

import com.jfinal.aop.Interceptor
import com.jfinal.aop.Invocation
import common.Exception.JsonException
import common.JsonBaseController

/**
 * Created by tjliqy on 2017/6/26.
 */
class JsonInterceptor : Interceptor {
    override fun intercept(inv: Invocation?) {
        val controller = inv?.controller
        controller?.setAttr("err",-1)
        controller?.setAttr("errMsg","")
        controller?.setAttr("body",null)
        try {
            inv?.invoke()
            if (controller is JsonBaseController){
                controller.addBody("body",controller.jsonBody)
            }
        }catch (e:Exception){
            if(e is JsonException){
                controller?.setAttr("err",e.errorNumber)
                controller?.setAttr("errMsg",e.errorMsg)
            }else{
                controller?.setAttr("err","500")
                controller?.setAttr("errMsg","Unknown error")
            }
        }finally {
            controller?.renderJson()
        }
    }
}