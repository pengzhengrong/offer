create database `offer`;
use `offer`;
create table `user_detail`(
	`id` int(11)  unsigned not null auto_increment,
	`name` varchar(30) not null default '',
	`age` int(3) not null default 0,
	`sex` tinyint(1) not null default 1 comment'1 男，0 女',
	`address` varchar(100) not null default '',
	`qq` char(11) not null default '',
	`mail` varchar(50) not null default '',
	`hope_salay` int(11) unsigned not null default 0,
	`tel` varchar(20) not null default '',
	`key_value` varchar(20) not null default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	primary key(`id`),
	key(`key_value`),
	key(`update_time`)
)engine=myisam default charset=utf8 auto_increment=1;

create table `user`(
	`id` int(11) unsigned not null auto_increment,
	`detail_id` int(11) unsigned not null default 0,
	`username` varchar(20) not null unique default '',
	`password` varchar(20) not null default '',
	`create_time` int(11) not null default 0,
	`update_time` int(11) not null default 0,
	`statu` tinyint(1) not null default 1 comment'1 正常 ，0 无效',
	primary key(`id`)
)engine=myisam default charset=utf8 auto_increment=1;
alter table `user` add index(`username`,`password`);
