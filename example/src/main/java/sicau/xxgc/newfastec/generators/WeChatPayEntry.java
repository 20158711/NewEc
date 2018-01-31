package sicau.xxgc.newfastec.generators;

import sicau.xxgc.yanbi.annotations.PayEntryGenerator;
import sicau.xxgc.yanbi.wxchat.templates.WXPayXEntryTemplate;


/**
 * Created by yanbi on 2018/1/29.
 */
@PayEntryGenerator(
        packageName = "sicau.xxgc.newfastec",
        payEntryTemplate = WXPayXEntryTemplate.class
)
public interface WeChatPayEntry {
}
