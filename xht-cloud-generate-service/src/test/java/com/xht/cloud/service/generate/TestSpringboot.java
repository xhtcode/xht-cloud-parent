package com.xht.cloud.service.generate;

import cn.hutool.core.io.FileUtil;
import com.xht.cloud.generate.GenerateApplication;
import com.xht.cloud.generate.constant.dto.ConfigTreeDTO;
import com.xht.cloud.generate.core.controller.request.GenCodeRequest;
import com.xht.cloud.generate.core.service.IGenerateCoreService;
import com.xht.cloud.generate.module.table.dao.mapper.GenTableMapper;
import com.xht.cloud.generate.utils.GenerateTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述 ：
 *
 * @author : 小糊涂
 **/
@Slf4j
@SpringBootTest(classes = GenerateApplication.class)
public class TestSpringboot {

    @Autowired
    private IGenerateCoreService generateCoreService;
    @Autowired
    private GenTableMapper genTableMapper;

    @Test
    public void test() throws Exception{
        List<String> tableIds = new ArrayList<>();
        tableIds.add("1732955312824524801");
        GenCodeRequest codeRequest = new GenCodeRequest();
        codeRequest.setProjectName("xht-cloud-demo");
        codeRequest.setPackageName("com.xht.cloud.demo");
        byte[] bytes = generateCoreService.downloadCode(codeRequest);
        FileUtil.writeBytes(bytes,new File("F:\\home\\1.zip"));
    }
    public static  String json = """
            [{"id":"-1","label":"顶级文件夹","expanded":true,"edit":false,"fileType":"0","children":[{"id":"056fbb44-f87e-43aa-bd95-63e3bb9ad23b","label":"{projectName}-api","expanded":true,"edit":false,"fileType":"0","children":[{"id":"4d62e83b-fc98-49bc-aca0-19be5b3fef0d","label":"src","expanded":true,"edit":false,"fileType":"0","children":[{"id":"0105982f-0606-4fb7-87e9-363353e01f43","label":"main","expanded":true,"edit":false,"fileType":"0","children":[{"id":"e707e0c1-c7e4-46e9-857d-613059efea1a","label":"java","expanded":true,"edit":false,"fileType":"0","children":[{"id":"9fa379b3-9421-4254-902c-ba5e10bd63d5","label":"{packageName}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"183a2e7b-a00a-4da4-92d4-9a294115ca2e","label":"{moduleLabel}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"bc34bb03-722c-41b4-8c9c-e1d713426d9e","label":"dto","expanded":true,"edit":false,"fileType":"0","children":[]}]}]}]},{"id":"6a0590e5-cd7e-44a1-af29-2c1f3b2bb134","label":"resources","expanded":true,"edit":false,"fileType":"0","children":[]}]}]}]},{"id":"056fbb44-f87e-43aa-bd95-63e3bb9ad23c","label":"{projectName}-service","expanded":true,"edit":false,"fileType":"0","children":[{"id":"4d62e83b-fc98-49bc-aca0-19be5b3fef0d","label":"src","expanded":true,"edit":false,"fileType":"0","children":[{"id":"0105982f-0606-4fb7-87e9-363353e01f43","label":"main","expanded":true,"edit":false,"fileType":"0","children":[{"id":"e707e0c1-c7e4-46e9-857d-613059efea1a","label":"java","expanded":true,"edit":false,"fileType":"0","children":[{"id":"9fa379b3-9421-4254-902c-ba5e10bd63d5","label":"{packageName}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"e2039a0b-e6c2-4390-94fa-5c591acaf375","label":"moudle","expanded":true,"edit":false,"fileType":"0","children":[{"id":"8b397697-de10-4bc6-a372-3ee60131a22f","label":"{moduleLabel}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"989917e3-29cf-4a9e-9bdf-c4163c300694","label":"controller","expanded":true,"edit":false,"fileType":"0","children":[{"id":"acfbb5f9-6380-4c98-b5ce-0891cc12b02d","label":"request","expanded":true,"edit":false,"fileType":"0","children":[{"id":"8e897a87-1150-4490-a744-383a76ebaf92","label":"{className}QueryRequest.java  (查询Request接收对象)","templateId":"173232593926838272","expanded":true,"edit":false,"fileType":"1","children":[]},{"id":"75e928a6-ae20-4b6b-b66b-1760e879b12e","label":"{className}AddRequest.java  (添加Request接收对象)","templateId":"1732325939268382723","expanded":true,"edit":false,"fileType":"1","children":[]},{"id":"94563976-1671-43a8-9935-b960a70fcc58","label":"{className}UpdateRequest.java  (修改Request接收对象)","templateId":"17323259392683827237","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"f225882e-8e9e-4d22-b513-9f777904db54","label":"response","expanded":true,"edit":false,"fileType":"0","children":[{"id":"c18d02fc-e0cd-43d8-9ac7-b75c11dab01c","label":"{className}Response.java  (Reponse响应对象)","templateId":"1732325939268382724","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"61370cb3-7087-453b-9804-506df7df93dc","label":"{className}Controller.java  (Controller)","templateId":"1732325937951371265","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"6e8e965c-5965-4189-aa11-248051f0d707","label":"convert","expanded":true,"edit":false,"fileType":"0","children":[{"id":"0292d678-4026-4947-b1e7-b4bd7d878445","label":"{className}Convert.java  (实体转换)","templateId":"1732325939075444737","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"0b3baee9-cb7b-454e-b5b6-baf17199a014","label":"dao","expanded":true,"edit":false,"fileType":"0","children":[{"id":"17a2bd78-2f1f-4208-b3ff-7813bff57cfe","label":"dataobject","expanded":true,"edit":false,"fileType":"0","children":[{"id":"b1e37aa1-98b6-4866-a2da-82cc480b6d31","label":"{className}DO.java  (数据库持久化对象)","templateId":"1732325939138359300","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"37fca4f3-2648-48cd-881e-f4a5f7f9e206","label":"mapper","expanded":true,"edit":false,"fileType":"0","children":[{"id":"a822ebfb-fb3a-4ad1-814f-a21f557ad00d","label":"{className}Mapper.java  (Mybatis 接口(dao))","templateId":"1732325939201273859","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"5430b2f3-c741-4253-92d0-716deaad21d9","label":"wrapper","expanded":true,"edit":false,"fileType":"0","children":[{"id":"21a806ff-310c-45f5-94ba-f083a4eace89","label":"{className}Wrapper.java  (MyBatisPlus Wrapper构建器)","templateId":"1732325939402600451","expanded":true,"edit":false,"fileType":"1","children":[]}]}]},{"id":"13549b19-fe28-4b5c-bf55-b525ab54cdee","label":"service","expanded":true,"edit":false,"fileType":"0","children":[{"id":"a939367f-c70e-426e-aab6-f61eded9e776","label":"impl","expanded":true,"edit":false,"fileType":"0","children":[{"id":"74778d4a-261d-4402-9ece-f67bb7e4bcb6","label":"{className}ServiceImpl.java  (业务 实现类)","templateId":"1732325939268382725","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"53c06840-7f01-4dc3-bb7a-1d8c196a0daa","label":"{className}Service.java  (业务 接口)","templateId":"1732325939201273857","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"40369bb5-c5d1-479e-bc1c-c1ce7a4d7e05","label":"package-info.java  (包描述)","templateId":"1732325939268382722","expanded":true,"edit":false,"fileType":"1","children":[]}]}]}]}]},{"id":"6a0590e5-cd7e-44a1-af29-2c1f3b2bb134","label":"resources","expanded":true,"edit":false,"fileType":"0","children":[{"id":"c48b39fa-a366-4f82-bb96-f0fe5b032f3c","label":"mapper","expanded":true,"edit":false,"fileType":"0","children":[{"id":"9a47b71e-1964-4be4-a876-5be43ff4e349","label":"{className}Mapper.xml  (mybatis 接口对应的xml对象)","templateId":"1732325939201273858","expanded":true,"edit":false,"fileType":"1","children":[]}]}]}]},{"id":"5ca573f2-dda1-48d1-bbfd-db2ed97efefc","label":"test","expanded":true,"edit":false,"fileType":"0","children":[{"id":"acb8c2fe-33d7-4985-a3a8-676ce64f932d","label":"java","expanded":true,"edit":false,"fileType":"0","children":[{"id":"e4a53e2a-7e70-4cb7-858c-c5c1623d4599","label":"{packageName}","expanded":true,"edit":false,"fileType":"0","children":[]}]},{"id":"d78ab32b-0c18-4b64-b596-7580cfe620f6","label":"resources","expanded":true,"edit":false,"fileType":"0","children":[]}]}]}]},{"id":"3b51e0f4-cb3f-4469-8091-b10efab32947","label":"web","expanded":true,"edit":false,"fileType":"0","children":[{"id":"f44e1627-1536-49de-b207-d71342a2bea1","label":"src","expanded":true,"edit":false,"fileType":"0","children":[{"id":"eccd3a98-50cc-48ca-ae63-8c6e642ab7c6","label":"api","expanded":true,"edit":false,"fileType":"0","children":[{"id":"bec60c73-f127-4e97-be14-a6ac6672f205","label":"{moduleName}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"f5cf4ab7-0e36-430b-8fd0-108c2c3530e4","label":"{moduleLabel}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"571f1be6-ad2c-4f39-92cc-d7eaab9074f1","label":"types.ts  (types)","templateId":"1732325939402600450","expanded":true,"edit":false,"fileType":"1","children":[]},{"id":"f0092175-6cd3-4b5d-9c91-150807e786ae","label":"api.ts  (api)","templateId":"1732325939335491587","expanded":true,"edit":false,"fileType":"1","children":[]}]}]}]},{"id":"44f51966-8225-45fa-915d-3a747c97ac0c","label":"views","expanded":true,"edit":false,"fileType":"0","children":[{"id":"57a987df-02e2-4fce-a278-029d96239807","label":"{moduleName}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"9c092f38-1564-402e-b43e-bdd8ce06d8e9","label":"{moduleLabel}","expanded":true,"edit":false,"fileType":"0","children":[{"id":"85c7878a-a129-42d9-b371-8a1fa5444ffe","label":"components","expanded":true,"edit":false,"fileType":"0","children":[{"id":"ce63d9ed-f3b5-4f8f-a97a-91115bc84c65","label":"add-or-update.vue  (add-or-update)","templateId":"1732325939335491586","expanded":true,"edit":false,"fileType":"1","children":[]}]},{"id":"ea255cc8-68f2-49d2-9f2c-96c92b382272","label":"index.vue  (index)","templateId":"1732325939402600449","expanded":true,"edit":false,"fileType":"1","children":[]}]}]}]}]}]}]}]
            """;
    public static void main(String[] args) {
        List<ConfigTreeDTO> configTreeDTOS = GenerateTool.convertToConfigTreeDTO(json);
        for (ConfigTreeDTO configTreeDTO : configTreeDTOS) {
            System.out.println(configTreeDTO);
            Map<String, ConfigTreeDTO> treeDTOMap = GenerateTool.flatArray(configTreeDTO);
            Set<String> keys = treeDTOMap.keySet();
            for (String key : keys) {
                System.out.println(key);
            }
        }
    }

}

