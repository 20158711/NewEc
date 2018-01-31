package sicau.xxgc.newfastec.generators;

import sicau.xxgc.yanbi.annotations.AppRegisterGenerator;
import sicau.xxgc.yanbi.wxchat.templates.AppRegisterTemplate;

/**
 * Created by yanbi on 2018/1/29.
 */
@AppRegisterGenerator(
        packageName = "sicau.xxgc.newfastec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
