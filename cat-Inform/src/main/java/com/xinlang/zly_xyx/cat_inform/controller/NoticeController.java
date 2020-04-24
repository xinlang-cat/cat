package com.xinlang.zly_xyx.cat_inform.controller;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_inform.bean.Notice;
import com.xinlang.zly_xyx.cat_inform.service.INoticeService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class NoticeController {
    @Autowired
    private INoticeService noticeService;


    @PostMapping("/notice")
    @LogAnnotation(module = "添加公告")
    @ApiOperation(value = "添加公告")
    public Notice save(@RequestBody Notice notice) {
        notice.setCreateTime(new Date());
        AppUser appUser = AppUserUtil.getLoginAppUser();
        notice.setCreateUserId(appUser.getId().intValue());
        notice.setCreateUserNickName(appUser.getNickname());
        noticeService.save(notice);
        return notice;
    }

    @PutMapping("/notice")
    @LogAnnotation(module = "修改公告")
    @ApiOperation(value = "修改公告")
    public Notice update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return notice;
    }

    @GetMapping("/notice/list")
    @LogAnnotation(module = "根据机构编码查询公告")
    @ApiOperation(value = "根据机构编码查询公告")
    public List<Notice> findByPageName(@RequestParam Map<String, Object> params) {
        return noticeService.findListByParams(params, Notice.class);
    }

    @GetMapping("/notice/page")
    @LogAnnotation(module = "根据机构编码查询公告")
    @ApiOperation(value = "根据机构编码查询公告")
    public Page<Notice> findPage(@RequestParam Map<String, Object> params) {
        return noticeService.findPageByParams(params, Notice.class);
    }

    @DeleteMapping("/notice/{id}")
    @LogAnnotation(module = "根据id删除公告")
    @ApiOperation(value = "根据id删除公告")
    public void delete(@PathVariable Integer id) {
        noticeService.delete(id);
    }


}
