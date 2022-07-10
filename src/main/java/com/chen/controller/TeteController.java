package com.chen.controller;

import com.chen.sysLogger.EBehavior;
import com.chen.sysLogger.OperationLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-07-09
 */
@RestController
@RequestMapping("/test")
public class TeteController {

    @OperationLogger(EBehavior.PRAISE)
    @RequestMapping("/get")
    public void getssss(){
        System.out.println(1111);
    }

    @OperationLogger(EBehavior.VISIT_PAGE)
    @RequestMapping("/get2")
    public void getList(){
        System.out.println(1111);
    }

}
