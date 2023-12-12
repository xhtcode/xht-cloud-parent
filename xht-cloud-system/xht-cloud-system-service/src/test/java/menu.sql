-- ------------------------------------------用户管理-----------------------------------------------------

-- 菜单 sql


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286866702360588', '1719286866702360587', 'B', '用户表查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:user:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286866702360589', '1719286866702360587', 'B', '用户表添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:user:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286866702360590', '1719286866702360587', 'B', '用户表修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:user:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286866702360591', '1719286866702360587', 'B', '用户表删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:user:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------权限管理-----------------------------------------------------

-- 菜单 sql

-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867465723909', '1719286867465723908', 'B', '系统角色表查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:role:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867465723910', '1719286867465723908', 'B', '系统角色表添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:role:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867465723911', '1719286867465723908', 'B', '系统角色表修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:role:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867465723912', '1719286867465723908', 'B', '系统角色表删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:role:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------部门岗位管理-----------------------------------------------------

-- 菜单 sql


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867516055574', '1719286867516055573', 'B', '部门查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:dept:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867516055575', '1719286867516055573', 'B', '部门添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:dept:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867516055576', '1719286867516055573', 'B', '部门修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:dept:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867516055577', '1719286867516055573', 'B', '部门删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:dept:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------权限管理-----------------------------------------------------

-- 菜单 sql


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867570581506', '1719286867570581505', 'B', '菜单权限表查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:menu:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867570581507', '1719286867570581505', 'B', '菜单权限表添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:menu:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867570581508', '1719286867570581505', 'B', '菜单权限表修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:menu:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867570581509', '1719286867570581505', 'B', '菜单权限表删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:menu:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------字典管理-----------------------------------------------------

-- 菜单 sql


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867629301762', '1719286867629301761', 'B', '字典查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867629301763', '1719286867629301761', 'B', '字典添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867629301764', '1719286867629301761', 'B', '字典修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867629301765', '1719286867629301761', 'B', '字典删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------字典管理-----------------------------------------------------

-- 菜单 sql


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867679633440', '1719286867679633439', 'B', '字典数据表查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict-item:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867679633441', '1719286867679633439', 'B', '字典数据表添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict-item:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867679633442', '1719286867679633439', 'B', '字典数据表修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict-item:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867679633443', '1719286867679633439', 'B', '字典数据表删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:dict-item:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------地区信息-----------------------------------------------------

-- 菜单 sql


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867729965088', '1719286867729965087', 'B', '地区信息查询', NULL, NULL, NULL, '', NULL, NULL, 'sys:area-info:query', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867729965089', '1719286867729965087', 'B', '地区信息添加', NULL, NULL, NULL, '', NULL, NULL, 'sys:area-info:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867729965090', '1719286867729965087', 'B', '地区信息修改', NULL, NULL, NULL, '', NULL, NULL, 'sys:area-info:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1719286867729965091', '1719286867729965087', 'B', '地区信息删除', NULL, NULL, NULL, '', NULL, NULL, 'sys:area-info:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- ------------------------------------------Oauth认证模块-----------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1727982643637141505', '1627612215981826050', 'C', 'oauth2 客户端信息', '/oauth2/registered/client', 'Oauth2RegisteredClientIndex', '/views/system/oauth2/client/index', 'MoreFilled' , NULL, NULL, 'oauth2:registered-client:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1727982643637141507', '1727982643637141505', 'B', 'oauth2 客户端信息添加', NULL, NULL, NULL, '', NULL, NULL, 'oauth2:registered-client:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1727982643637141508', '1727982643637141505', 'B', 'oauth2 客户端信息修改', NULL, NULL, NULL, '', NULL, NULL, 'oauth2:registered-client:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1727982643637141509', '1727982643637141505', 'B', 'oauth2 客户端信息删除', NULL, NULL, NULL, '', NULL, NULL, 'oauth2:registered-client:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725645050568705', '1627612215981826050', 'C', '代码生成器-代码模板', '/gen/code/template', 'GenCodeTemplateIndex', '/views/system/template/template/index', 'MoreFilled' , NULL, NULL, 'gen:code-template:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725645050568707', '1728725645050568705', 'B', '代码生成器-代码模板添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:code-template:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725645050568708', '1728725645050568705', 'B', '代码生成器-代码模板修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:code-template:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725645050568709', '1728725645050568705', 'B', '代码生成器-代码模板删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:code-template:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646136893441', '1627612215981826050', 'C', '代码生成器-数据源管理', '/gen/database', 'GenDatabaseIndex', '/views/system/database/database/index', 'MoreFilled' , NULL, NULL, 'gen:database:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646136893443', '1728725646136893441', 'B', '代码生成器-数据源管理添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:database:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646136893444', '1728725646136893441', 'B', '代码生成器-数据源管理修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:database:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646136893445', '1728725646136893441', 'B', '代码生成器-数据源管理删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:database:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646493409291', '1627612215981826050', 'C', '代码生成器-数据库信息', '/gen/table', 'GenTableIndex', '/views/system/table/table/index', 'MoreFilled' , NULL, NULL, 'gen:table:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646493409293', '1728725646493409291', 'B', '代码生成器-数据库信息添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:table:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646493409294', '1728725646493409291', 'B', '代码生成器-数据库信息修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:table:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646493409295', '1728725646493409291', 'B', '代码生成器-数据库信息删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:table:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646849925123', '1627612215981826050', 'C', '代码生成业务字段', '/gen/table/column', 'GenTableColumnIndex', '/views/system/column/column/index', 'MoreFilled' , NULL, NULL, 'gen:table-column:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646849925125', '1728725646849925123', 'B', '代码生成业务字段添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:table-column:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646849925126', '1728725646849925123', 'B', '代码生成业务字段修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:table-column:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725646849925127', '1728725646849925123', 'B', '代码生成业务字段删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:table-column:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725882825654283', '1627612215981826050', 'C', '代码生成器-代码模板', '/gen/code/template', 'GenCodeTemplateIndex', '/views/system/template/template/index', 'MoreFilled' , NULL, NULL, 'gen:code-template:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725882825654285', '1728725882825654283', 'B', '代码生成器-代码模板添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:code-template:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725882825654286', '1728725882825654283', 'B', '代码生成器-代码模板修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:code-template:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725882825654287', '1728725882825654283', 'B', '代码生成器-代码模板删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:code-template:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725883899396097', '1627612215981826050', 'C', '代码生成器-数据源管理', '/gen/database', 'GenDatabaseIndex', '/views/system/database/database/index', 'MoreFilled' , NULL, NULL, 'gen:database:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725883899396099', '1728725883899396097', 'B', '代码生成器-数据源管理添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:database:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725883899396100', '1728725883899396097', 'B', '代码生成器-数据源管理修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:database:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725883899396101', '1728725883899396097', 'B', '代码生成器-数据源管理删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:database:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884234940417', '1627612215981826050', 'C', '代码生成器-数据库信息', '/gen/table', 'GenTableIndex', '/views/system/table/table/index', 'MoreFilled' , NULL, NULL, 'gen:table:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884234940419', '1728725884234940417', 'B', '代码生成器-数据库信息添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:table:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884234940420', '1728725884234940417', 'B', '代码生成器-数据库信息修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:table:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884234940421', '1728725884234940417', 'B', '代码生成器-数据库信息删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:table:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
-- -----------------------------------------------------------------------------------------------

-- 菜单 sql
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884486598657', '1627612215981826050', 'C', '代码生成业务字段', '/gen/table/column', 'GenTableColumnIndex', '/views/system/column/column/index', 'MoreFilled' , NULL, NULL, 'gen:table-column:list', '1', '1', '0', '0', '0', '0', 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());


-- 权限 sql 增删改是按钮级别前端后端都有权限控制，查询主要来自后端控制
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884486598659', '1728725884486598657', 'B', '代码生成业务字段添加', NULL, NULL, NULL, '', NULL, NULL, 'gen:table-column:add', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884486598660', '1728725884486598657', 'B', '代码生成业务字段修改', NULL, NULL, NULL, '', NULL, NULL, 'gen:table-column:edit', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
INSERT INTO sys_menu
(`id`, `parent_id`, `menu_type`, `menu_name`, `menu_path`, `menu_view_name`, `menu_view_path`, `menu_icon`, `menu_redirect`, `menu_active`, `menu_authority`, `menu_hidden`, `menu_status`, `menu_link`, `menu_cache`, `menu_full`, `menu_affix`, `menu_sort`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES
('1728725884486598661', '1728725884486598657', 'B', '代码生成业务字段删除', NULL, NULL, NULL, '', NULL, NULL, 'gen:table-column:remove', NULL, '1', NULL, NULL, NULL, NULL, 1, '0', '代码生成(xht)', '代码生成(xht)', sysdate(), sysdate());
