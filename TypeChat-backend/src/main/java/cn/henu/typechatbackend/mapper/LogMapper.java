package cn.henu.typechatbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.henu.typechatbackend.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper extends BaseMapper<Log> {
}
