package cn.henu.typechatbackend.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.henu.typechatbackend.entity.Group;
import cn.henu.typechatbackend.entity.User;
import cn.henu.typechatbackend.controller.dto.ResponseDto;
import cn.henu.typechatbackend.mapper.GroupMapper;
import cn.henu.typechatbackend.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typechat/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private GroupMapper groupMapper;

    ///获取用户基本信息
    @PostMapping("/info")
    public ResponseDto<?> info(@RequestBody User user)
    {
        try {
            User user1 = userMapper.selectById(user.getId());
            user1.clearPrivateInfo();
            return new ResponseDto<>(user1);
        }catch (Exception e){
            return new ResponseDto<>(false, e.getMessage());
        }
    }


    //更新用户信息
    @PostMapping("/update")
    public ResponseDto<?> login(@RequestBody User user){
        user.setCreateTime(null);
        userMapper.updateById(user);
        User user1 = userMapper.selectById(user.getId());
        user1.clearPrivateInfo();
        return new ResponseDto<>(user1);
    }


    ///根据关键字查找用户和群聊


    @PostMapping("/search")
    public ResponseDto<?> search(@RequestBody Map<String,String> param){
        String keyword = param.get("keyword");
        ResponseDto<JSONObject> responseDto = new ResponseDto<>(null);
        try {
            if(keyword==null)   throw new Exception("关键词为空！");

            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("is_deleted", false);
            userQueryWrapper.like("nickname",keyword).or().like("email",keyword).or().eq("id",keyword);
            userQueryWrapper.select("id","nickname","email","avatar");
            List<User> userList = userMapper.selectList(userQueryWrapper);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userList", userList);

            QueryWrapper<Group> groupQueryWrapper = new QueryWrapper<>();
            groupQueryWrapper.eq("is_deleted", false);
            groupQueryWrapper.eq("id",keyword).or().like("name",keyword);
            List<Group> groupList = groupMapper.selectList(groupQueryWrapper);
            jsonObject.put("groupList",groupList);
            responseDto.setData(jsonObject);
        }catch (Exception e){
            return new ResponseDto<>(false, e.getMessage());
        }
        return responseDto;
    }

}
