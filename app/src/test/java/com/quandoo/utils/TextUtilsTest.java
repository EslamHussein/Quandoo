package com.quandoo.utils;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.R;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eslam Hussein on 12/1/17.
 */
public class TextUtilsTest extends BaseTest {
    @Test
    public void getString() throws Exception {
        assertEquals(TextUtils.getString(R.string.app_name),"Quandoo");
    }

    @Test
    public void isEmptyStringSuccess() throws Exception {
        assertTrue(TextUtils.isEmptyString(""));
    }

    @Test
    public void isEmptyStringError() throws Exception {
        assertFalse(TextUtils.isEmptyString("string"));
    }

}