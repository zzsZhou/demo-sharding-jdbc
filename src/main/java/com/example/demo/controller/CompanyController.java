package com.example.demo.controller;

import com.example.demo.model.dao.Company;
import com.example.demo.service.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/11/11
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/companys")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;


    @GetMapping("/{company_id}")
    public Company queryCompanyById(@PathVariable("company_id") Integer companyId) {

        log.info("queryCompanyById id:{}", companyId);

        Company rs = companyService.queryByCompanyId(companyId);
        return rs;
    }

}
