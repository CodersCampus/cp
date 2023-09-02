package com.coderscampus.springwise.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.springwise.domain.XmplData;
import com.coderscampus.springwise.domain.XmplDatum;
import com.coderscampus.springwise.service.XmplDataService;

@RestController
@RequestMapping("/xmpl")
public class XmplDataController {

	@Autowired
	private XmplDataService xmplDataService;

	@GetMapping("/data")
	public ResponseEntity<XmplData> getData() {
		XmplData xmplData =  xmplDataService.getData();
		return ResponseEntity.ok(xmplData);
	}

	@GetMapping("/datum")
	public ResponseEntity<XmplDatum> getDatum() {
		XmplDatum xmplDatum =  xmplDataService.getDatum();
		return ResponseEntity.ok(xmplDatum);
	}
}
