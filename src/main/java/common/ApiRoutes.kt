package common

import api.ApiController
import com.jfinal.config.Routes

/**
 * Created by tjliqy on 2017/6/25.
 */

class ApiRoutes: BaseRoutes() {
    override fun config() {
        baseUrl = "/api"
        addExtraUrl("/",ApiController::class.java)
    }

}