package com.example.icapa.madridguide;


import android.test.AndroidTestCase;

import java.util.Locale;

public class LanguageTests extends AndroidTestCase {
    public void testLanguageIsSpanish(){
        String lan = Locale.getDefault().getLanguage();
        assertEquals(lan,"es");

    }
}
