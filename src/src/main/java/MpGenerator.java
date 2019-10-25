import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpGenerator {

    public static void main(String[] args) {
//        assert (false) : "代码生成属于危险操作，请确定配置后取消断言执行代码生成！";
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
       String sourcePath = "E:\\belle\\option-ace\\easyRpt\\src\\main\\java";
       String basePackage = "com.cn.easyRpt";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("ms.x");
        gc.setOutputDir(sourcePath);
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(false);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        gc.setFileOverride(true);
        //设置service接口的命名方式（默认的是前面加I）
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://10.0.30.39:3306/db_bms_p02?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true")
                .setUsername("user_bms_cs")
                .setPassword("scm_bms_cs");
        dsc.setTypeConvert(new MySqlTypeConvert() {

        });
         mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel)
                // 全局大写命名
                .setCapitalMode(true)
                // 此处可以修改为您的表前缀
                .setTablePrefix(new String[] {})
                .entityTableFieldAnnotationEnable(true)
                .setRestControllerStyle(true)
                .setEntityLombokModel(true)
                .setInclude(new String[]{"easy_rpt_export"});
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(basePackage)
                .setController("controller")
                //服务接口
                .setService("service")
                //服务实现
                .setServiceImpl("service.impl")
                //dao层
                .setMapper("mapper")
                //dao层对应的xml文件
                .setXml("mapper.mapper")
                //pojo对象
                .setEntity("entity");
        mpg.setPackageInfo(packageConfig);
       TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("/template/controller.java.vm")
                .setEntity("/template/entity.java.vm")
                .setMapper("/template/mapper.java.vm")
                .setXml("/template/mapper.xml.vm")
                .setService("/template/service.java.vm")
                .setServiceImpl("/template/serviceImpl.java.vm");
        mpg.setTemplate(templateConfig);

        // 执行生成
        mpg.execute();
    }
}
