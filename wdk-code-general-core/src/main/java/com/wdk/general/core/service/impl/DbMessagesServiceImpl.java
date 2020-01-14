package com.wdk.general.core.service.impl;

import com.wdk.general.core.common.enums.DbTypeEnum;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.ResponseVo;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.service.DbMessagesService;
import com.wdk.general.core.storage.dao.DbMessagesMapper;
import com.wdk.general.core.storage.entity.DbMessages;
import com.wdk.general.core.storage.entity.DbMessagesExample;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by wdk on 2020/1/2
 */
@Service
public class DbMessagesServiceImpl implements DbMessagesService {


    @Autowired
    private DbMessagesMapper dbMessagesMapper;

    /**
     * 保存数据库
     *
     * @param db
     * @return
     */
    @Override
    public ResponseVo<Integer> save(DbMessage db) {

        if (null == db.getUserId()) {
            db.setUserId(UserContext.current().getUserId());
//            return new ResponseVo<>(ResponseStatusEnum.PARRAM_ERROR.getCode(),"用户ID不能为空。");
        }

        //校验数据是否存在或重名
        DbMessagesExample example = new DbMessagesExample();
        example.createCriteria()
                .andUserIdEqualTo(db.getUserId())
                .andDbNameEqualTo(db.getDbname())
                .andDbTypeEqualTo(DbTypeEnum.MYSQL.getCode());
        List<DbMessages> data = dbMessagesMapper.selectByExample(example);
        if (data.size() > 0) {
            return new ResponseVo<>(ResponseStatusEnum.PARRAM_ERROR.getCode(), "已经存在数据库：" + DbTypeEnum.MYSQL.getCode() + "。dbName:" + db.getDbname());
        }
        //保存数据
        DbMessages dbMessages = new DbMessages();
        BeanUtils.copyProperties(db, dbMessages);
        int result = dbMessagesMapper.insertSelective(dbMessages);

        return new ResponseVo<>(ResponseStatusEnum.SUCCESS, result);
    }
}
