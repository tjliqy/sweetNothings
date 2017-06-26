package common

import com.jfinal.config.Routes
import com.jfinal.core.Controller
import com.jfinal.kit.StrKit

/**
 * Created by tjliqy on 2017/6/25.
 */
abstract class BaseRoutes :Routes(){
    var baseUrl=""
    set(value) {
        if (StrKit.isBlank(value)) {
            throw IllegalArgumentException("baseViewPath can not be blank")
        } else {
            var url = value.trim();
            if (!url.startsWith("/")) {
                url = "/" + url
            }

            if (url.endsWith("/")) {
                url = url.substring(0, url.length - 1)
            }

            baseUrl = url
        }
    }

    override fun add(controllerKey: String?, controllerClass: Class<out Controller>?): Routes {
        return super.add(controllerKey, controllerClass)
    }
    fun addExtraUrl(controllerKey:String,controllerClass:Class<out Controller>?):Routes{
        return super.add(baseUrl + processViewPath(controllerKey),controllerClass)
    }

    private fun processViewPath(viewPath: String): String {
        var viewPath = viewPath
        viewPath = viewPath.trim()
        if (!viewPath.startsWith("/")) {
            viewPath = "/" + viewPath
        }
        if (!viewPath.endsWith("/")) {
            viewPath += "/"
        }

        return viewPath
    }
}
