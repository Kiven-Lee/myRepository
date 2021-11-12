# myRepository
----查看数据库中有哪些序列 
--r =普通表， i =索引，S =序列，v =视图，m =物化视图， c =复合类型，t = TOAST表，f =外部表 
select * from pg_class where relkind='S' 
--查询数据库设置
select * from pg_settings where name like '%isolation%';
--只读模式状态修改
show default_transaction_read_only;
alter system set default_transaction_read_only=off;
select pg_reload_conf();
--安装gin索引插件
set search_path=public;
create extension pg_trgm;
grant all on schema s1 to u1;
grant all on all functions in schema s1 to u1;

--查看用户权限
select * from information_schema.table_privileges where table_schema='s1' and table_name='t1';
--查看用户
select * from pg_user;

--1 查找锁表的pid
select pid from pg_locks l join pg_class t on l.relation = t.oid where t.relkind = 'r' and t.relname = 'lockedtable';
--2 查找锁表的语句
select pid,state,usename,query,query_start from pg_stat_activity where pid in (select pid from pg_locks l join pg_class t on l.relation = t.oid and t.relkind = 'r' where t.relname = 'lockedtable');
--3 查找所有活动的被锁的表
select pid, state, usename, query, query_start 
from pg_stat_activity 
where pid in (
  select pid from pg_locks l 
  join pg_class t on l.relation = t.oid 
  and t.relkind = 'r' 
);
--4 解锁
SELECT pg_cancel_backend(pid);

--json数据查询
select * from t1 where c1::json->>'id' in ('$id');

--linux
--https://blog.csdn.net/wngpenghao/article/details/83022185
--“> Log.log 2>&1” ：表示将 stdout 和 stderr 合并后重定向到 Log.log
nohup java -jar xxx.jar >xxx.log 2>&1 &
--运行jar包后需要输入密钥
--运行jar包步骤
--java -jar xxx.jar
--输入密钥
--Ctrl+Z
--bg 任务号(1) 

--复制文件夹
cp -r dir1 dir2

--1、根据端口号查进程pid
lsof -i:port
netstat -nap|grep port
--2、根据进程号查端口
netstat -nap|grep pid
--3、根据进程pid查看服务路径
ps -ef|grep java 查看进程pid
ll /proc/pid/cwd 查看服务安装路径

--统计当前目录大小 并安大小 排序
du -sm * | sort -n 
--打包
zip -q -r dir.zip dir/

