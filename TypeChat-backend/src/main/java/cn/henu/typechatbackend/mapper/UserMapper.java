package cn.henu.typechatbackend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.henu.typechatbackend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
