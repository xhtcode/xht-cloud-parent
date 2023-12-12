package com.xht.cloud.system.gen;

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
 * 描述 ：`角色和部门关联`代码生成器所需实体
 *
 * @author : 小糊涂-代码生成器
 **/
@Data
@Module(value = "dept", desc = "")
@GenInfoAnnotation(tableName = "sys_role_dept", url = "/sys/role/dept", tableComment = "角色和部门关联", tableType = "BASE TABLE", tableSchema = "xht-cloud", engine = "InnoDB", createTime = "2023-10-18 19:33:51", updateTime = "")
public class SysRoleDept {

    /**
     * 角色ID
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
    @FiledInfo(value = "role_id", describe = "角色ID", columnDefault = "", length = 0, isNullable = true, dbType = "bigint")
    private Long roleId;

    /**
     * 部门ID
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
    @FiledInfo(value = "dept_id", describe = "部门ID", columnDefault = "", length = 0, isNullable = true, dbType = "bigint")
    private Long deptId;


}
