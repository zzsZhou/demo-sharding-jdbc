package com.example.demo.service.impl;

import com.example.demo.mapper.CompanyMapper;
import com.example.demo.model.dao.Company;
import com.example.demo.service.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/11/11
 */
@Service
@Slf4j
public class CompanyServiceImpl implements ICompanyService {


    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company queryByCompanyId(Integer id) {
        Company company = companyMapper.selectByPrimaryKey(id);
        return company;
    }


}
