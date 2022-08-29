package com.example.bill4self.system.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 11:20
 * @email dujianghui537885@163.com
 */
@Data
@Accessors(chain = true)
public class TreeVo {
    private String title;

    private Long id;

    private List<TreeVo> children;

    private boolean checked;
}
