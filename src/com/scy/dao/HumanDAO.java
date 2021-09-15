package com.scy.dao;

import com.scy.bean.Human;
import com.scy.bean.HumanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HumanDAO {
    //所有-----单表：增---数据  删---根据id删除，条件删除  改：根据主键条件修改（动态sql）
    //查--按主键查询
    long countByExample(HumanExample example);//用example类查总条数﹐动态的sql去查淘总条数
    //当example类为null的时候，sql语句等一如下
    //select count(*) from human
    //当example类不为null的时候
    //select count(*) from human where gender=2
    int deleteByExample(HumanExample example);

    int deleteByPrimaryKey(Integer id);//按主键id删除

    int insert(Human record);//不用他   //当human对象所有属性都在可以用他，就是一个普通的增加

    int insertSelective(Human record);//尽量用他！！！先用他，他错了再用上一个

    List<Human> selectByExample(HumanExample example);

    Human selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Human record, @Param("example") HumanExample example);//动态的批量修改

    int updateByExample(@Param("record") Human record, @Param("example") HumanExample example);//动态的批量修改   不用

    int updateByPrimaryKeySelective(Human record);//按主键id修改一条数据一个对象

    int updateByPrimaryKey(Human record);
}