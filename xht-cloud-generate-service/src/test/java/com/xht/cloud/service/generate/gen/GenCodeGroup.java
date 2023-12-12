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
@GenInfoAnnotation(tableName = "gen_code_group", url = "/gen/code/group", tableComment = "", tableType = "BASE TABLE", tableSchema = "xht-cloud-generate", engine = "InnoDB", createTime = "2023-12-04 11:11:47", updateTime = "")
public class GenCodeGroup {

    /**
     * 
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
    @FiledInfo(value = "id", describe = "", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String id;

    /**
     * 组名称
     */
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
    @FiledInfo(value = "group_name", describe = "组名称", columnDefault = "", length = 255, isNullable = false, dbType = "varchar")
    private String groupName;

    /**
     * 组描述
     */
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
    @FiledInfo(value = "group_desc", describe = "组描述", columnDefault = "", length = 255, isNullable = false, dbType = "varchar")
    private String groupDesc;


}
