package com.qibao.activity.feign;

import com.qibao.common.dto.BaseResponse;
import com.qibao.user.context.service.IUserController;
import com.qibao.user.context.vo.UserAccountVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("qibao-user-service")
public interface IUserFeign extends IUserController {
}
