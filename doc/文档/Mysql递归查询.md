# mysql 8版本

## 查询子节点  含自己

```sql
 -- 查询子节点  含自己
WITH RECURSIVE custom_sys_menu (id, parent_id, menu_type, menu_name) AS
                   (SELECT t1.id,
                           t1.parent_id,
                           t1.menu_type,
                           t1.menu_name
                    from sys_menu t1
                    where t1.id = '1627612933404942337'
                    
                    UNION ALL

                    SELECT t2.id,
                           t2.parent_id,
                           t2.menu_type,
                           t2.menu_name
                    from sys_menu t2,
                         custom_sys_menu t3
                    WHERE t2.parent_id = t3.id)
SELECT t.*
FROM custom_sys_menu t
;
```

## 查询子节点  不含自己

```sql
 -- 查询子节点  不含自己
WITH RECURSIVE custom_sys_menu (id, parent_id, menu_type, menu_name) AS
                   (SELECT t1.id,
                           t1.parent_id,
                           t1.menu_type,
                           t1.menu_name
                    from sys_menu t1
                    where t1.id = '1627612933404942337'
                    
                    UNION ALL

                    SELECT t2.id,
                           t2.parent_id,
                           t2.menu_type,
                           t2.menu_name
                    from sys_menu t2,
                         custom_sys_menu t3
                    WHERE t2.parent_id = t3.id)
SELECT t.*
FROM custom_sys_menu t
WHERE t.id != '1627612933404942337'
;
```

## 查询父节点  含自己

```sql

-- 查询父节点  含自己
WITH RECURSIVE custom_sys_menu (id, parent_id, menu_type, menu_name
    ) AS
                   (SELECT t1.id,
                           t1.parent_id,
                           t1.menu_type,
                           t1.menu_name
                    from sys_menu t1
                    where t1.id = '1627612933404942337'
                    
                    UNION ALL

                    SELECT t2.id,
                           t2.parent_id,
                           t2.menu_type,
                           t2.menu_name

                    from sys_menu t2,
                         custom_sys_menu t3
                    WHERE t2.id = t3.parent_id)
SELECT t.*
FROM custom_sys_menu t
;
```

## 查询父节点不含自己

```sql

-- 查询父节点  不含自己
WITH RECURSIVE custom_sys_menu (id, parent_id, menu_type, menu_name
    ) AS
                   (SELECT t1.id,
                           t1.parent_id,
                           t1.menu_type,
                           t1.menu_name
                    from sys_menu t1
                    where t1.id = '1627612933404942337'
                    UNION ALL

                    SELECT t2.id,
                           t2.parent_id,
                           t2.menu_type,
                           t2.menu_name

                    from sys_menu t2,
                         custom_sys_menu t3
                    WHERE t2.id = t3.parent_id)
SELECT t.*
FROM custom_sys_menu t
WHERE t.id != '1627612933404942337'
;
```
