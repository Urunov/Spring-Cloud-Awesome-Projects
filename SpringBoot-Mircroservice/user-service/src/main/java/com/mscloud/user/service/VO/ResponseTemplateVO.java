package com.mscloud.user.service.VO;

import com.mscloud.user.service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {user-service}
 * @Date: {2022/05/14 && 3:10 PM}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    //
    private User user;
    private Department department;
}
