package common;

import api.ApiController;
import api.HelloController;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import model._MappingKit;

/**
 * Created by tjliqy on 2017/6/26.
 */
public class WebConfig extends JFinalConfig {
    public void configConstant(Constants constants) {
        PropKit.use("common.properties");

    }

    public void configRoute(Routes routes) {
        routes.setBaseViewPath("/_view/page");
        routes.add("/api", ApiController.class);
        routes.add("/", HelloController.class);
    }

    public void configEngine(Engine engine) {
        engine.addSharedObject("assets","/_view/assets");
        engine.addSharedFunction("/_view/common/_layout.html");
    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        plugins.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.setShowSql(true);
        SqlReporter.setLog(true);

        _MappingKit.mapping(activeRecordPlugin);
        plugins.add(activeRecordPlugin);
    }

    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }
}
