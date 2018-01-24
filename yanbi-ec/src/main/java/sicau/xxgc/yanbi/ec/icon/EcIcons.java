package sicau.xxgc.yanbi.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by yanbi on 2018/1/23.
 */

public enum  EcIcons implements Icon {

    icon_scan('\ue65d'),
    icon_ali_pay('\ue62f');

    private char character;

    EcIcons(char character){
        this.character=character;
    }

    @Override
    public String key() {
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return character;
    }

}
