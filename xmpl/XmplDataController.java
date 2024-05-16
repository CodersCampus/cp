package com.coderscampus.xmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xmpl")
@CrossOrigin(origins = "*")
public class XmplDataController {

    private final XmplDataService xmplDataService;

    @Autowired
    public XmplDataController(XmplDataService xmplDataService) {
        this.xmplDataService = xmplDataService;
    }

    @PostMapping("/xmpl/data")
    public ResponseEntity<XmplData> getData() {
        XmplData xmplData = xmplDataService.getData();
        return ResponseEntity.ok(xmplData);
    }

    @PostMapping("/datum")
    public ResponseEntity<XmplDatum> getDatum() {
        XmplDatum xmplDatum = xmplDataService.getDatum();
        return ResponseEntity.ok(xmplDatum);
    }
}
