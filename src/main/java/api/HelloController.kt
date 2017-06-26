package api

import com.jfinal.core.Controller

/**
 * Created by tjliqy on 2017/6/23.
 */
class HelloController : Controller() {
    internal fun index() {
        renderText("Hello233")
    }
}
