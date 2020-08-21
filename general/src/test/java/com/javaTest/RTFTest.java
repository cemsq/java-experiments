package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 *
 */
public class RTFTest {

    @Test
    public void shouldConvertFromPlainToPlain() {
        convert("a test", "a test");
    }

    @Test
    public void shouldConvertFromRTFToPlain() {
        convert("{\\rtf1\\ansi\\ansicpg1252\\uc1\\deff0{\\fonttbl\n" +
                        "{\\f0\\fnil\\fcharset0\\fprq2 Arial;}\n" +
                        "{\\f1\\fswiss\\fcharset0\\fprq2 Tahoma;}\n" +
                        "{\\f2\\froman\\fcharset2\\fprq2 Symbol;}}\n" +
                        "{\\colortbl;\\red0\\green0\\blue0;\\red255\\green255\\blue255;}\n" +
                        "{\\stylesheet{\\s0\\itap0\\nowidctlpar\\f0\\fs24 [Normal];}{\\*\\cs10\\additive Default Paragraph Font;}}\n" +
                        "{\\*\\generator TX_RTF32 15.0.530.503;}\n" +
                        "\\deftab1134\\paperw11905\\paperh16838\\margl0\\margt0\\margr0\\margb0\\widowctrl\\formshade\\sectd\n" +
                        "\\headery720\\footery720\\pgwsxn11905\\pghsxn16838\\marglsxn0\\margtsxn0\\margrsxn0\\margbsxn0\\pard\\itap0\\nowidctlpar\\plain\\f1\\fs20 Text f\\loch\\f1\\hich\\f1\\'fcr Anforderungserfassung 252525}",
                "Text für Anforderungserfassung 252525");
    }

    private void convert(String from, String expected) {

        RTFEditorKit rtfParser = new RTFEditorKit();
        Document document = rtfParser.createDefaultDocument();

        try {
            rtfParser.read(new ByteArrayInputStream(from.getBytes()), document, 0);
            String text = document.getText(0, document.getLength());
            Assert.assertEquals(text, expected);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
