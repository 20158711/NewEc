package sicau.xxgc.newfastec.generators;

import sicau.xxgc.yanbi.annotations.EntryGenerator;
import sicau.xxgc.yanbi.wxchat.templates.WXEntryTemplate;

/**
 * Created by yanbi on 2018/1/29.
 */
@EntryGenerator(
        packageName = "sicau.xxgc.newfastec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
