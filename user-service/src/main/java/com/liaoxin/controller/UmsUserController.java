package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.model.PageDTO;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.*;
import com.liaoxin.model.dto.SignInDTO;
import com.liaoxin.model.dto.SignUpDTO;
import com.liaoxin.service.UserService;
import com.liaoxin.service.bpo.UserBPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UmsUserController extends BaseController {

    @Autowired
    UserBPO userBPO;
    @Autowired
    UserService userService;
//    @Autowired
//    MessageProduct messageProduct;

    /**
     * 获取用户信息
     * @param token 标识
     */
    @GetMapping("info")
    public ResultBean info(@RequestParam("token") String token){
        UmsUser umsUser = userInfo(token);
        if(null == umsUser){
            throw new AppException("用户信息失效，请重新登录");
        }
        List<String> roles = new ArrayList();
        roles.add("admin");
        umsUser.setRoles(roles);
        umsUser.setPassword(null);
        return ResultBean.success(umsUser);
    }

    /**
     * 用户注册
     * @param signUpDTO 注册DTO
     */
    @PostMapping("/signUp")
    public ResultBean<UmsUser> signUp(@RequestBody SignUpDTO signUpDTO){
        UmsUser result = userBPO.signUp(signUpDTO);
        return ResultBean.success(result);
    }

    /**
     * 用户登录
     * @param signInDTO 登录DTO
     */
    @PostMapping("/signIn")
    public ResultBean signIn(@RequestBody SignInDTO signInDTO){
        String token = userBPO.signIn(signInDTO);
        Map<String, String> result = new HashMap();
        result.put("tokenHeader","Bearer");
        result.put("token",token);
        return ResultBean.success(result);
    }

    /**
     * 分页查询
     * @param current
     * @param size
     * @param user
     * @return
     */
    @GetMapping("/{current}/{size}")
    public ResultBean list(@PathVariable("current") Long current,
                           @PathVariable("size") Long size,
                           @RequestBody UmsUser user){
        com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO<UmsUser> pageDTO = new com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO<UmsUser>(current,size);
        Page<UmsUser> userPage = userService.list(pageDTO,user);
        return ResultBean.success(userPage);
    }

    @GetMapping("/list")
    public ResultBean<Page<UmsUser>> page(@RequestBody PageDTO pageDTO){
        QueryWrapper<UmsUser> wrapper = new QueryWrapper();
        wrapper.select(UmsUser.class, info -> !info.getColumn().equals("password"));
        if(pageDTO.getSearchWord() != null){
            wrapper.like("nick_name",pageDTO.getSearchWord());
        }
        return ResultBean.success(userService.page(new Page(pageDTO.getPageNum(), pageDTO.getPageSize()),wrapper));
    }

    /**
     * 通过ID查询用户
     * @param id
     */
    @GetMapping("/{id}")
    public ResultBean<UmsUser> getUser(@PathVariable("id") Long id){
        return ResultBean.success(userService.getById(id));
    }

    /**
     * 更新用户信息
     * @param updateDTO
     */
    @PutMapping("")
    public ResultBean update(@RequestBody UmsUpdateDTO updateDTO){
        UmsUser umsUser = new UmsUser();

        umsUser.setLastTime(new Date());
        BeanUtils.copyProperties(updateDTO, umsUser);
        if(userService.updateById(umsUser)){
            return ResultBean.success();
        }else{
            return ResultBean.failure();
        }
    }

    /**
     * 用户登出
     * @param request
     */
    @PostMapping("/logout")
    public ResultBean logout(HttpServletRequest request){
        String token = request.getHeader("Bearer");
        if(!userService.logout(token)){
            return ResultBean.failure(403,"用户已经注销");
        }
        return ResultBean.success(true);
    }

    /**
     * 修改密码
     * @param dto
     */
    @PutMapping("/password/{id}")
    public ResultBean updatePassword(@RequestBody UpdatePasswordDTO dto){
        int result = userService.updatePassword(dto);
        if(result > 0){
            return ResultBean.success();
        }
        return ResultBean.failure();
    }
}
