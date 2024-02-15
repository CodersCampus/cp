package com.coderscampus.xmpl;

import java.util.ArrayList;
import java.util.List;

public class XmplData {
    List<XmplDatum> xmplDataList = new ArrayList<>();

    public List<XmplDatum> getXmplDataList() {
        return xmplDataList;
    }
    public void addXmplData(XmplDatum xmplDatum) {
        xmplDataList.add(xmplDatum);
    }
}
