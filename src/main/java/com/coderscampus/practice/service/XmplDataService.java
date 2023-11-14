package com.coderscampus.practice.service;

import org.springframework.stereotype.Service;

import com.coderscampus.practice.domain.XmplData;
import com.coderscampus.practice.domain.XmplDatum;

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
