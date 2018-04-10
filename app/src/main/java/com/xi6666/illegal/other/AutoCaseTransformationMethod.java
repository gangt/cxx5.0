package com.xi6666.illegal.other;

import android.text.method.ReplacementTransformationMethod;

/**
 * 作者： qsdsn on 2017/2/9
 * 描述：${DESC}
 */

public class AutoCaseTransformationMethod extends ReplacementTransformationMethod {
    @Override
    protected char[] getOriginal() {
        return new char[]{'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z'};
    }

    @Override
    protected char[] getReplacement() {
        return new char[]{ 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
    }
}
