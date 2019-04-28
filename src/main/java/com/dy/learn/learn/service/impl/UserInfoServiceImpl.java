package com.dy.learn.learn.service.impl;

import com.dy.learn.learn.bean.Query.UserInfoQuery;
import com.dy.learn.learn.bean.Result;
import com.dy.learn.learn.bean.ResultPage;
import com.dy.learn.learn.dao.entity.UserInfo;
import com.dy.learn.learn.dao.mapper.UserInfoMapper;
import com.dy.learn.learn.dataSource.DataSourceSelection;
import com.dy.learn.learn.enums.DataSourceType;
import com.dy.learn.learn.enums.ResultCode;
import com.dy.learn.learn.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @DataSourceSelection(type = DataSourceType.slave)
    public UserInfo getUserInfoById(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    @DataSourceSelection(type = DataSourceType.slave)
    public int getUserInfo(UserInfo userInfo) {
        int res=this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return res;
    }

    @Override
    @DataSourceSelection(type = DataSourceType.master)
    public void insertUserInfo(UserInfo userInfo) {
        int i=userInfoMapper.insert(userInfo);
    }

    @Override
    public ResultPage<UserInfo> findUserInfoPage(UserInfoQuery query) {
        ResultPage<UserInfo> result = new ResultPage<UserInfo>();
        if (query.getFlag()){
            PageHelper.startPage(query.getPage(), query.getRows());
        }
        List<UserInfo> lists = userInfoMapper.selectUserInfoPage(query);
        result.setData(lists);
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

}
