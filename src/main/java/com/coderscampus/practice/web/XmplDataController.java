package com.coderscampus.practice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.practice.domain.XmplData;
import com.coderscampus.practice.domain.XmplDatum;
import com.coderscampus.practice.service.XmplDataService;

@RestController
@RequestMapping("/xmpl")
@CrossOrigin(origins = "*")
public class XmplDataController {

	@Autowired
	private XmplDataService xmplDataService;

	@PostMapping("/data")
	public ResponseEntity<XmplData> getData() {
		XmplData xmplData =  xmplDataService.getData();
		return ResponseEntity.ok(xmplData);
	}

	@PostMapping("/datum")
	public ResponseEntity<XmplDatum> getDatum() {
		XmplDatum xmplDatum =  xmplDataService.getDatum();
		return ResponseEntity.ok(xmplDatum);
	}
}
