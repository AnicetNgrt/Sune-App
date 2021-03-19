package com.example.suneapp.utils.shelltest;

import com.example.suneapp.utils.shelltest.node.RecordNode;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetpropResultParser {
    public static RecordNode parse(List<String> results) {
        RecordNode rn = RecordNode.getInstance("getprop");

        for (String r : results) {
            String pattStr = "(?<=\\[)[^\\]\\[]*(?=])";
            Pattern patt = Pattern.compile(pattStr);
            Matcher m = patt.matcher(r);
            m.find();
            String left = m.group(0);
            m.find();
            String right = m.group(0);

            RecordNode added = rn.add(left);
            added.setData(right);
        }

        System.out.println(rn.toString());

        return rn;
    }
}
