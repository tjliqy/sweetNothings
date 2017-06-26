package common

import com.jfinal.aop.Before
import com.jfinal.core.Controller
import common.interceptor.JsonInterceptor

/**
 * Created by tjliqy on 2017/6/26.
 */
@Before(JsonInterceptor::class)
open class JsonBaseController:Controller(){
    val jsonBody:HashMap<String,Any> = HashMap()

    fun addBody(name:String,value:Any){
        jsonBody.put(name,value)
    }
}