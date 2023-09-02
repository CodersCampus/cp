package com.coderscampus.springwise.service;

import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.XmplData;
import com.coderscampus.springwise.domain.XmplDatum;

@Service
public class XmplDataService {

	public XmplData getData(){
		XmplData xmplData = new XmplData();
		xmplData.addXmplData(new XmplDatum("yada", 1, false, "wah"));
		xmplData.addXmplData(new XmplDatum("yahoo", 2, true, "wah wah"));
		xmplData.addXmplData(new XmplDatum("yada", 3, false, "wah wah wah"));
		return xmplData;
	}

	public XmplDatum getDatum(){
		return new XmplDatum("yada", 1, false, "wah");
	}
}
