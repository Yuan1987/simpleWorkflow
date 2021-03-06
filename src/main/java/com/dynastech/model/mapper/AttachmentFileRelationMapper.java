package com.dynastech.model.mapper;

import com.dynastech.model.entity.AttachmentFileRelation;
import com.dynastech.model.entity.AttachmentFileRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachmentFileRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int countByExample(AttachmentFileRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int deleteByExample(AttachmentFileRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int insert(AttachmentFileRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int insertSelective(AttachmentFileRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    List<AttachmentFileRelation> selectByExample(AttachmentFileRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    AttachmentFileRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int updateByExampleSelective(@Param("record") AttachmentFileRelation record, @Param("example") AttachmentFileRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int updateByExample(@Param("record") AttachmentFileRelation record, @Param("example") AttachmentFileRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int updateByPrimaryKeySelective(AttachmentFileRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Attachment_File_relation
     *
     * @mbggenerated Fri Sep 29 10:00:09 CST 2017
     */
    int updateByPrimaryKey(AttachmentFileRelation record);
}