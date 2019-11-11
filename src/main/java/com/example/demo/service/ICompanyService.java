package com.example.demo.service;

import com.example.demo.model.dao.Company;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/11/11
 */
public interface ICompanyService {

    Company queryByCompanyId(Integer id);

}
