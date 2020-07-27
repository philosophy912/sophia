package com.chinatsp.automation.api.builder;

import com.philosophy.base.util.FilesUtils;
import com.philosophy.base.util.StringsUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.chinatsp.automation.api.IConstant.FTLH;
import static com.chinatsp.automation.api.IConstant.TEMPLATES;
import static com.chinatsp.dbc.api.IConstant.UTF8;


/**
 * @author lizhe
 * @date 2020/5/28 13:20
 **/
@Slf4j
public abstract class BaseBuilder {
    @Setter
    private String templateFolder;
    @Setter
    protected String projectName;
    @Setter
    protected String testCaseName;
    @Setter
    protected String version;
    @Setter
    protected String automotive;
    @Setter
    protected String socPort;

    protected Map<String, Object> createMap(String fileName) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("fileName", fileName);
        map.put("createDate", getDateTime());
        return map;
    }

    private String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 根据传递的type查找template文件的文件夹位置
     *
     * @param type 类型
     * @return 找打的文件夹
     */
    private String getTemplateFolder(TestCaseTypeEnum type) {
        String freemarkerFolder;
        if (StringsUtils.isEmpty(templateFolder)) {
            // 以当前文件夹下面的templates优先查找
            String folder = FilesUtils.getCurrentPath() + File.separator + TEMPLATES;
            if (Files.exists(Paths.get(folder))) {
                templateFolder = folder;
            } else {
                String e = "请检查当前路径下是否存在对应的template文件夹";
                throw new RuntimeException(e);
            }
        }
        if (type != TestCaseTypeEnum.COMMON) {
            // 这个时候templateFolder就存在了
            freemarkerFolder = templateFolder + File.separator + type.getValue();
            if (!Files.exists(Paths.get(freemarkerFolder))) {
                String e = "请检查" + templateFolder + "路径下是否存在对应的" + type.getValue() + "文件夹";
                throw new RuntimeException(e);
            }
            return freemarkerFolder;
        }
        return templateFolder;
    }

    @SneakyThrows
    protected Template getTemplate(String fileName, TestCaseTypeEnum type) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        File file = new File(getTemplateFolder(type));
        configuration.setDirectoryForTemplateLoading(file);
        configuration.setDefaultEncoding(UTF8);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);
        // 自动增加后缀.ftlh
        if (!fileName.endsWith(FTLH)) {
            fileName = fileName + FTLH;
        }
        return configuration.getTemplate(fileName);
    }

    @SneakyThrows
    protected Writer getWriter(Path path) {
        return new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8);
    }
}
