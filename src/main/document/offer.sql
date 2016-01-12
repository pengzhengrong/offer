create database `offer`;
use `offer`;
create table `user_detail`(
	`id` int(11)  unsigned not null auto_increment,
	`user_id` int(11) unsigned not null default 0,
	`name` varchar(30) not null default '',
	`age` int(3)  default 0,
	`sex` tinyint(1)  default 1 comment'1 男，2 女 ，0 未知',
	`address` varchar(100)  default '',
	`qq` char(11)  default '',
	`mail` varchar(50)  default '',
	`hope_salay` int(11) unsigned  default 0,
	`tel` varchar(20)  default '',
	`thumb` text default '',
	`project_name` varchar(50) default '',
	`project_desc` text default '',
	`key_value` varchar(20) not null default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	primary key(`id`),
	key(`key_value`),
	key(`update_time`)
)engine=myisam default charset=utf8 auto_increment=1;
-- alter table `user_detail` add `thumb` text default '';
-- alter table `user_detail` add `project_name` varchar(50) default '';
-- alter table `user_detail` add `project_desc` text default '';
-- alter table `user_detail` add `user_id` int(11) unsigned not null default 0;

create table `user`(
	`id` int(11) unsigned not null auto_increment,
	`username` varchar(20) not null unique default '',
	`password` varchar(20) not null default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	primary key(`id`),
	key(`username`,`password`)
)engine=myisam default charset=utf8 auto_increment=1;
-- alter table `user` add index(`username`,`password`);
-- alter table `user` drop `detail_id`;

create table `admin`(
	`id` int(11) unsigned not null auto_increment,
	`username` varchar(20) not null unique default '',
	`password` varchar(20) not null default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	`power` text default '',
	primary key(`id`),
	key(`username`,`password`)
)engine=myisam default charset=utf8 auto_increment=1;

create table `company`(
	`id` int(11) unsigned not null auto_increment,
	`username` varchar(20) not null unique default '',
	`password` varchar(20) not null default '',
	`company_name` varchar(30) not null default '',
	`desc` text default '',
	`address` varchar(100)  default '',
	`tel` varchar(20)  default '',
	`thumb` text default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	primary key(`id`),
	key(`username`,`password`)
)engine=myisam default charset=utf8 auto_increment=1;
-- alter table `company` add `desc` text default '';
-- alter table `company` add `thumb` text default '';

create table `jobs`(
	`id` int(11) unsigned not null auto_increment,
	`company_id` int(11) unsigned not null ,
	`company_name` varchar(30) not null default '',
	`name` varchar(50) not null default '',
	`desc` text not null default '',
	`address` varchar(100) default '',
	`salay` varchar(11) default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	primary key(`id`),
	key(`company_id`)
)engine=myisam default charset=utf8 auto_increment=1;

-- alter table `jobs` add `company_name` varchar(30) not null default '',

create table `user_job`(
	`id` int(11) unsigned not null auto_increment,
	`user_id` int(11) unsigned not null default 0,
	`company_name` varchar(100) not null default '',
	`user_name` varchar(30) unsigned not null ,
	`job_name` varchar(30) unsigned not null ,
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 0 comment'0 正在处理，1 处理完成，-1 拒绝',
	primary key(`id`),
	key(`company_name`,`job_name`)
)engine=myisam default charset=utf8 auto_increment=1;



