package com.xht.cloud.service.generate.gen;

import com.xht.cloud.code.generate.annotation.GenInfoAnnotation;
import com.xht.cloud.code.generate.annotation.Module;
import com.xht.cloud.code.generate.annotation.fields.FiledInfo;
import com.xht.cloud.code.generate.annotation.fields.Id;
import com.xht.cloud.code.generate.annotation.file.GenApi;
import com.xht.cloud.code.generate.annotation.file.GenApiImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileConvert;
import com.xht.cloud.code.generate.annotation.file.GenFileAddRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileController;
import com.xht.cloud.code.generate.annotation.file.GenFileDO;
import com.xht.cloud.code.generate.annotation.file.GenFileDao;
import com.xht.cloud.code.generate.annotation.file.GenFileDaoImpl;
import com.xht.cloud.code.generate.annotation.file.GenFileResponse;
import com.xht.cloud.code.generate.annotation.file.GenFileIService;
import com.xht.cloud.code.generate.annotation.file.GenFileMapper;
import com.xht.cloud.code.generate.annotation.file.GenFileMapperXml;
import com.xht.cloud.code.generate.annotation.file.GenFileRequest;
import com.xht.cloud.code.generate.annotation.file.GenFileServiceImpl;
import com.xht.cloud.code.generate.annotation.file.GenPackageInfo;
import com.xht.cloud.code.generate.annotation.file.GenFileWrapper;
import com.xht.cloud.code.generate.annotation.file.GenSql;
import com.xht.cloud.code.generate.annotation.file.GenVue3AddOrUpdate;
import com.xht.cloud.code.generate.annotation.file.GenVue3Api;
import com.xht.cloud.code.generate.annotation.file.GenVue3Index;
import com.xht.cloud.code.generate.annotation.file.GenVue3Types;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述 ：`代码生成器-基类`代码生成器所需实体
 *
 * @author : 小糊涂-代码生成器
 **/
@Data
@Module(value = "entity", desc = "")
@GenInfoAnnotation(tableName = "gen_code_base_class", url = "/gen/code/base/class", tableComment = "代码生成器-基类", tableType = "BASE TABLE", tableSchema = "xht-cloud-generate", engine = "InnoDB", createTime = "2023-04-23 17:37:08", updateTime = "")
public class GenCodeBaseClass {

    /**
     * id
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
    @FiledInfo(value = "id", describe = "id", columnDefault = "", length = 36, isNullable = true, dbType = "varchar")
    private String id;

    /**
     * 类名
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
    @FiledInfo(value = "class_name", describe = "类名", columnDefault = "", length = 255, isNullable = false, dbType = "varchar")
    private String className;

    /**
     * 包名
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
    @FiledInfo(value = "package_name", describe = "包名", columnDefault = "", length = 255, isNullable = false, dbType = "varchar")
    private String packageName;

    /**
     * 忽略字段(写数据库字段)
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
    @FiledInfo(value = "ignore_field", describe = "忽略字段(写数据库字段)", columnDefault = "", length = 255, isNullable = false, dbType = "varchar")
    private String ignoreField;

    /**
     * 排序
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
    @FiledInfo(value = "class_sort", describe = "排序", columnDefault = "", length = 255, isNullable = false, dbType = "varchar")
    private String classSort;

    /**
     * 是否删除(0未删除1已经删除)
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
    @FiledInfo(value = "del_flag", describe = "是否删除(0未删除1已经删除)", columnDefault = "", length = 1, isNullable = false, dbType = "char")
    private String delFlag;

    /**
     * 创建者
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
    @FiledInfo(value = "create_by", describe = "创建者", columnDefault = "", length = 64, isNullable = false, dbType = "varchar")
    private String createBy;

    /**
     * 更新者
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
    @FiledInfo(value = "update_by", describe = "更新者", columnDefault = "", length = 64, isNullable = false, dbType = "varchar")
    private String updateBy;

    /**
     * 创建时间
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
    @FiledInfo(value = "create_time", describe = "创建时间", columnDefault = "", length = 0, isNullable = false, dbType = "datetime")
    private LocalDateTime createTime;

    /**
     * 更新时间
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
    @FiledInfo(value = "update_time", describe = "更新时间", columnDefault = "", length = 0, isNullable = false, dbType = "datetime")
    private LocalDateTime updateTime;


}
