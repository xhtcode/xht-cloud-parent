package com.xht.cloud.service.generate.gen;

import com.xht.cloud.code.generate.annotation.GenInfoAnnotation;
import com.xht.cloud.code.generate.annotation.Module;
import com.xht.cloud.code.generate.annotation.fields.FiledInfo;
import com.xht.cloud.code.generate.annotation.fields.Id;
import com.xht.cloud.code.generate.annotation.file.*;
import lombok.Data;

/**
 * 描述 ：``代码生成器所需实体
 *
 * @author : 小糊涂-代码生成器
 **/
@Data
@Module(value = "template", desc = "模板")
@GenInfoAnnotation(tableName = "gen_code_group_template", url = "/gen/code/group/template", tableComment = "", tableType = "BASE TABLE", tableSchema = "xht-cloud-generate", engine = "InnoDB", createTime = "2023-12-04 11:13:08", updateTime = "")
public class GenCodeGroupTemplate {

    /**
     * 组id
     */
    @Id
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "group_id", describe = "组id", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String groupId;

    /**
     * 模板id
     */
    @Id
    @GenApi
    @GenVue3Types
    @GenVue3Api
    @GenVue3Index
    @GenVue3AddOrUpdate
    @GenApiImpl
    @GenFileConvert
    @GenFileAddRequest
    @GenFileController
    @GenFileDO
    @GenFileDao
    @GenFileDaoImpl
    @GenFileResponse
    @GenFileIService
    @GenFileMapper
    @GenFileMapperXml
    @GenFileRequest
    @GenFileServiceImpl
    @GenPackageInfo
    @GenSql
    @GenFileWrapper
    @FiledInfo(value = "template_id", describe = "模板id", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String templateId;


}
