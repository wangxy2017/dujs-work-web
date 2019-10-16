package com.wxy.util;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.StringReader;

/**
 * @Author wxy
 * @Date 19-10-16 下午5:22
 * @Description TODO
 **/
public class HtmlToTextUtils {
    /**
     * html 转换 text
     *
     * @param html
     * @return
     * @throws IOException
     */
    public static String convert(String html) throws IOException {
        final StringBuilder b = new StringBuilder();
        ParserDelegator delegator = new ParserDelegator();
        // the third parameter is TRUE to ignore charset directive
        delegator.parse(new StringReader(html), new HTMLEditorKit.ParserCallback() {
            public void handleText(char[] data, int pos) {
                b.append(data).append("\n");
            }
        }, true);
        return b.toString();
    }
}
