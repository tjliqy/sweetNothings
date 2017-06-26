package common

import api.ApiController
import api.HelloController
import com.jfinal.config.*
import com.jfinal.kit.PropKit
import com.jfinal.plugin.activerecord.ActiveRecordPlugin
import com.jfinal.plugin.activerecord.SqlReporter
import com.jfinal.plugin.druid.DruidPlugin
import com.jfinal.template.Engine
import model._MappingKit

/**
 * Created by tjliqy on 2017/6/23.
 */
class WebConfig : JFinalConfig() {


    override fun configConstant(constants: Constants?) {
        PropKit.use("common.properties")
    }

    override fun configRoute(routes: Routes?) {
//        routes.add(ApiRoutes())
        routes?.add("/api",ApiController::class.java)
        routes?.add("/", HelloController::class.java)
    }

    override fun configEngine(engine: Engine?) {

    }

    override fun configPlugin(plugins: Plugins?) {
        val druidPlugin = DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim())
        plugins?.add(druidPlugin)

        val activeRecordPlugin = ActiveRecordPlugin(druidPlugin)
        activeRecordPlugin.setShowSql(true)
        SqlReporter.setLog(true)

        _MappingKit.mapping(activeRecordPlugin)
        plugins?.add(activeRecordPlugin)
    }

    override fun configInterceptor(interceptors: Interceptors?) {

    }

    override fun configHandler(handlers: Handlers?) {

    }
}
