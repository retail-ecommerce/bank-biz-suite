-- BUILD WITH MODEL TIME 190810T1111
drop database  if exists bank;
create database bank;
-- alter  database bank  character set = utf8mb4  collate = utf8mb4_unicode_ci; -- 支持表情符号
use bank;

drop table  if exists platform_data;
create table platform_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(60)                              comment '名称',
	founder                       	varchar(12)                              comment '创始人',
	founded                       	datetime                                 comment '成立',
	description                   	varchar(60)                              comment '描述',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "平台";

drop table  if exists change_request_data;
create table change_request_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(8)                               comment '名称',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "变更请求";

drop table  if exists transaction_data;
create table transaction_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(16)                              comment '名称',
	from_account                  	varchar(48)                              comment '从账户',
	to_account                    	varchar(48)                              comment '承担责任',
	amount                        	numeric(7,2)                             comment '金额',
	type                          	varchar(8)                               comment '类型',
	change_request                	varchar(48)                              comment '变更请求',
	current_status                	varchar(64)                              comment '当前状态',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "事务";

drop table  if exists name_change_event_data;
create table name_change_event_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(32)                              comment '名称',
	account                       	varchar(48)                              comment '账户',
	change_request                	varchar(48)                              comment '变更请求',
	current_status                	varchar(64)                              comment '当前状态',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "名字更改事件";

drop table  if exists account_data;
create table account_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(16)                              comment '名称',
	balance                       	numeric(7,2)                             comment '余额',
	create_time                   	datetime                                 comment '创建时间',
	update_time                   	datetime                                 comment '更新时间',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "账户";

drop table  if exists account_change_data;
create table account_change_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(16)                              comment '名称',
	previous_balance              	numeric(8,2)                             comment '以前的平衡',
	type                          	varchar(8)                               comment '类型',
	amount                        	numeric(7,2)                             comment '金额',
	current_balance               	numeric(8,2)                             comment '当前余额',
	account                       	varchar(48)                              comment '账户',
	change_request                	varchar(48)                              comment '变更请求',
	current_status                	varchar(64)                              comment '当前状态',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "账户变更";

drop table  if exists user_domain_data;
create table user_domain_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(16)                              comment '名称',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户域";

drop table  if exists user_white_list_data;
create table user_white_list_data (
	id                            	varchar(48)          not null            comment 'ID',
	user_identity                 	varchar(40)                              comment '用户身份',
	user_special_functions        	varchar(200)                             comment '用户特殊功能',
	domain                        	varchar(48)                              comment '域',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户白名单";

drop table  if exists sec_user_data;
create table sec_user_data (
	id                            	varchar(48)          not null            comment 'ID',
	login                         	varchar(20)                              comment '登录',
	mobile                        	varchar(11)                              comment '手机号码',
	email                         	varchar(76)                              comment '电子邮件',
	pwd                           	varchar(64)                              comment '密码',
	weixin_openid                 	varchar(128)                             comment '微信openid',
	weixin_appid                  	varchar(128)                             comment '微信Appid',
	access_token                  	varchar(128)                             comment '访问令牌',
	verification_code             	int                                      comment '验证码',
	verification_code_expire      	datetime                                 comment '验证码过期',
	last_login_time               	datetime                                 comment '最后登录时间',
	domain                        	varchar(48)                              comment '域',
	blocking                      	varchar(48)                              comment '屏蔽',
	current_status                	varchar(28)                              comment '当前状态',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "安全用户";

drop table  if exists sec_user_blocking_data;
create table sec_user_blocking_data (
	id                            	varchar(48)          not null            comment 'ID',
	who                           	varchar(52)                              comment '谁',
	block_time                    	datetime                                 comment '块时间',
	comments                      	varchar(96)                              comment '评论',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户屏蔽";

drop table  if exists user_app_data;
create table user_app_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(300)                             comment '标题',
	sec_user                      	varchar(48)                              comment '安全用户',
	app_icon                      	varchar(36)                              comment '应用程序图标',
	full_access                   	tinyint                                  comment '完全访问',
	permission                    	varchar(16)                              comment '许可',
	object_type                   	varchar(100)                             comment '访问对象类型',
	object_id                     	varchar(40)                              comment '对象ID',
	location                      	varchar(48)                              comment '位置',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户应用程序";

drop table  if exists list_access_data;
create table list_access_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	internal_name                 	varchar(200)                             comment '内部名称',
	read_permission               	tinyint                                  comment '读权限',
	create_permission             	tinyint                                  comment '创建权限',
	delete_permission             	tinyint                                  comment '删除权限',
	update_permission             	tinyint                                  comment '更新权限',
	execution_permission          	tinyint                                  comment '执行权限',
	app                           	varchar(48)                              comment '应用程序',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "访问列表";

drop table  if exists object_access_data;
create table object_access_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(28)                              comment '名称',
	object_type                   	varchar(112)                             comment '访问对象类型',
	list1                         	varchar(80)                              comment '列表1',
	list2                         	varchar(80)                              comment '列表2',
	list3                         	varchar(80)                              comment '列表3',
	list4                         	varchar(80)                              comment '列表4',
	list5                         	varchar(80)                              comment '列表5',
	list6                         	varchar(80)                              comment '列表6',
	list7                         	varchar(80)                              comment '列表7',
	list8                         	varchar(80)                              comment '列表8',
	list9                         	varchar(80)                              comment '列表9',
	app                           	varchar(48)                              comment '应用程序',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "对象访问";

drop table  if exists login_history_data;
create table login_history_data (
	id                            	varchar(48)          not null            comment 'ID',
	login_time                    	datetime                                 comment '登录时间',
	from_ip                       	varchar(44)                              comment '来自IP',
	description                   	varchar(16)                              comment '描述',
	sec_user                      	varchar(48)                              comment '安全用户',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "登录历史";

drop table  if exists generic_form_data;
create table generic_form_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(20)                              comment '标题',
	description                   	varchar(48)                              comment '描述',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "通用的形式";

drop table  if exists form_message_data;
create table form_message_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(24)                              comment '标题',
	form                          	varchar(48)                              comment '形式',
	level                         	varchar(28)                              comment '水平',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单信息";

drop table  if exists form_field_message_data;
create table form_field_message_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(16)                              comment '标题',
	parameter_name                	varchar(16)                              comment '参数名称',
	form                          	varchar(48)                              comment '形式',
	level                         	varchar(28)                              comment '水平',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单字段的信息";

drop table  if exists form_field_data;
create table form_field_data (
	id                            	varchar(48)          not null            comment 'ID',
	label                         	varchar(12)                              comment '标签',
	locale_key                    	varchar(44)                              comment '语言环境的关键',
	parameter_name                	varchar(16)                              comment '参数名称',
	type                          	varchar(36)                              comment '类型',
	form                          	varchar(48)                              comment '形式',
	placeholder                   	varchar(48)                              comment '占位符',
	default_value                 	varchar(12)                              comment '默认值',
	description                   	varchar(48)                              comment '描述',
	field_group                   	varchar(16)                              comment '字段组',
	minimum_value                 	varchar(60)                              comment '最小值',
	maximum_value                 	varchar(72)                              comment '最大值',
	required                      	tinyint                                  comment '要求',
	disabled                      	tinyint                                  comment '禁用',
	custom_rendering              	tinyint                                  comment '自定义渲染',
	candidate_values              	varchar(12)                              comment '候选人的价值观',
	suggest_values                	varchar(12)                              comment '建议值',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单字段";

drop table  if exists form_action_data;
create table form_action_data (
	id                            	varchar(48)          not null            comment 'ID',
	label                         	varchar(8)                               comment '标签',
	locale_key                    	varchar(16)                              comment '语言环境的关键',
	action_key                    	varchar(24)                              comment '行动的关键',
	level                         	varchar(28)                              comment '水平',
	url                           	varchar(168)                             comment 'url',
	form                          	varchar(48)                              comment '形式',
	version                       	int                                      comment '版本',
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单动作";




insert into platform_data values
	('P000001','凯思俱乐部医药行业数据管理平台','朱义平','2019-07-22 14:43:33','凯思俱乐部医药行业数据管理平台','1'),
	('P000002','凯思俱乐部医药行业数据管理平台','朱义平','2019-07-30 14:46:00','凯思俱乐部医药行业数据管理平台','1'),
	('P000003','凯思俱乐部医药行业数据管理平台','朱义平','2019-08-02 08:53:28','凯思俱乐部医药行业数据管理平台','1');

insert into change_request_data values
	('CR000001','存款','2019-07-25 10:57:38','P000001','1'),
	('CR000002','转账','2019-08-01 15:56:31','P000002','1'),
	('CR000003','取款','2019-08-05 23:22:44','P000003','1');

insert into transaction_data values
	('T000001','存款交易','A000001','A000001','113.77','存款',NULL,'CHANGE_REQUESTED','1'),
	('T000002','存款交易','A000001','A000001','99.03','存款',NULL,'CHANGE_REQUESTED0002','1'),
	('T000003','取款交易','A000002','A000002','107.74','取款',NULL,'CHANGE_REQUESTED0003','1'),
	('T000004','存款交易','A000003','A000003','112.35','存款',NULL,'CHANGE_REQUESTED0004','1');

insert into name_change_event_data values
	('NCE000001','new name','A000001',NULL,'CHANGE_REQUESTED','1'),
	('NCE000002','new name0002','A000001',NULL,'CHANGE_REQUESTED0002','1'),
	('NCE000003','new name0003','A000002',NULL,'CHANGE_REQUESTED0003','1'),
	('NCE000004','new name0004','A000003',NULL,'CHANGE_REQUESTED0004','1');

insert into account_data values
	('A000001','张三账户','96.98','2019-08-03 05:37:17','2019-07-23 00:36:40','P000001','1'),
	('A000002','李四账户','91.18','2019-08-07 06:48:16','2019-07-22 17:46:56','P000002','1'),
	('A000003','银行账户','112.87','2019-07-22 05:41:07','2019-08-04 10:39:34','P000003','1');

insert into account_change_data values
	('AC000001','存款交易','965.06','存款','86.11','1078.04','A000001',NULL,'CHANGE_REQUESTED','1'),
	('AC000002','存款交易','1016.03','存款','112.29','1095.95','A000001',NULL,'CHANGE_REQUESTED0002','1'),
	('AC000003','取款交易','1092.85','取款','113.72','1157.83','A000002',NULL,'CHANGE_REQUESTED0003','1'),
	('AC000004','存款交易','998.59','存款','106.56','1222.05','A000003',NULL,'CHANGE_REQUESTED0004','1');

insert into user_domain_data values
	('UD000001','用户区域','1');

insert into user_white_list_data values
	('UWL000001','clariones','tester;ios-spokesperson','UD000001','1'),
	('UWL000002','13808188512','tester;ios-spokesperson0002','UD000001','1');

insert into sec_user_data values
	('SU000001','login','13900000001','','C183EC89F92A462CF45B95504792EC4625E847C90536EEFE512D1C9DB8602E95','wx123456789abcdefghijklmn','wxapp12098410239840','jwt_token_12345678','0','2019-08-10 01:56:50','2019-08-01 05:00:28','UD000001',NULL,'BLOCKED','1'),
	('SU000002','login0002','13900000002','suddy_chang@163.com','AC2F95628244C6975EB2C36942EA879ED93D93F5895EF3157733E4629FA86B92','wx123456789abcdefghijklmn0002','wxapp120984102398400002','jwt_token_123456780002','9999999','2019-07-28 02:08:04','2019-08-09 05:48:07','UD000001',NULL,'BLOCKED0002','1');

insert into sec_user_blocking_data values
	('SUB000001','currentUser()','2019-07-26 18:00:16','这个用户多次发送违反社区的帖子，现在把他给屏蔽了','1');

insert into user_app_data values
	('UA000001','审车平台','SU000001','users','1','MXWR','CarInspectionPlatform','CIP000001','/link/to/app','1'),
	('UA000002','账户管理','SU000001','bank','1','MXWR','UserDomain','UD000001','/link/to/app0002','1'),
	('UA000003','接车公司','SU000001','wechat','1','MXWR','CarReceivingServiceCompany','CRSC000001','/link/to/app0003','1'),
	('UA000004','审车公司','SU000002','bar-chart','1','MXWR','CarInspectionServiceCompany','CISC000001','/link/to/app0004','1'),
	('UA000005','维修公司','SU000002','user','1','MXWR','CarRepairingServiceCompany','CRSC000001','/link/to/app0005','1'),
	('UA000006','顾客','SU000002','users','1','MXWR','CustomerInfo','CI000001','/link/to/app0006','1');

insert into list_access_data values
	('LA000001','列表','levelOneCategoryList','1','1','1','1','1','UA000001','1'),
	('LA000002','列表0002','levelOneCategoryList0002','1','1','1','1','1','UA000001','1'),
	('LA000003','列表0003','levelOneCategoryList0003','1','1','1','1','1','UA000002','1'),
	('LA000004','列表0004','levelOneCategoryList0004','1','1','1','1','1','UA000003','1'),
	('LA000005','列表0005','levelOneCategoryList0005','1','1','1','1','1','UA000004','1'),
	('LA000006','列表0006','levelOneCategoryList0006','1','1','1','1','1','UA000004','1'),
	('LA000007','列表0007','levelOneCategoryList0007','1','1','1','1','1','UA000005','1'),
	('LA000008','列表0008','levelOneCategoryList0008','1','1','1','1','1','UA000006','1');

insert into object_access_data values
	('OA000001','控制访问列表1','FranchiseeStoreCountryCenter','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','UA000001','1'),
	('OA000002','控制访问列表10002','AccountSet','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','UA000001','1'),
	('OA000003','控制访问列表10003','FranchiseeStoreCountryCenter','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','UA000002','1'),
	('OA000004','控制访问列表10004','AccountSet','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','UA000003','1'),
	('OA000005','控制访问列表10005','FranchiseeStoreCountryCenter','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','UA000004','1'),
	('OA000006','控制访问列表10006','AccountSet','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','UA000004','1'),
	('OA000007','控制访问列表10007','FranchiseeStoreCountryCenter','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','catalogList','UA000005','1'),
	('OA000008','控制访问列表10008','AccountSet','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','levelOneCategoryList','UA000006','1');

insert into login_history_data values
	('LH000001','2019-08-03 20:10:26','192.168.1.1','登陆成功','SU000001','1'),
	('LH000002','2019-07-25 01:35:53','192.168.1.2','登陆成功0002','SU000001','1'),
	('LH000003','2019-07-30 07:16:36','192.168.1.1','登陆成功0003','SU000002','1'),
	('LH000004','2019-07-22 07:32:53','192.168.1.2','登陆成功0004','SU000002','1');

insert into generic_form_data values
	('GF000001','登记输入单','姓名就是你身份证上的名字','1');

insert into form_message_data values
	('FM000001','字段组合错误','GF000001','success','1'),
	('FM000002','字段组合错误0002','GF000001','info','1');

insert into form_field_message_data values
	('FFM000001','输入错误','name','GF000001','success','1'),
	('FFM000002','输入错误0002','name0002','GF000001','info','1');

insert into form_field_data values
	('FF000001','姓名','name','name','text','GF000001','姓名就是你身份证上的名字','李一一','姓名就是你身份证上的名字','基础信息','maybe any value','a value expression','1','1','1','','','1'),
	('FF000002','年龄','age','name0002','longtext','GF000001','姓名就是你身份证上的名字0002','李一一0002','姓名就是你身份证上的名字0002','扩展信息','maybe any value0002','a value expression0002','1','1','1','','','1'),
	('FF000003','出生地','birth_place','name0003','date','GF000001','姓名就是你身份证上的名字0003','李一一0003','姓名就是你身份证上的名字0003','基础信息','maybe any value0003','a value expression0003','1','1','1','','','1'),
	('FF000004','国籍','country','name0004','date_time','GF000001','姓名就是你身份证上的名字0004','李一一0004','姓名就是你身份证上的名字0004','扩展信息','maybe any value0004','a value expression0004','1','1','1','男,女','男,女','1');

insert into form_action_data values
	('FA000001','功能','name','save','default','genericFormManager/name/name0002/name0003/','GF000001','1'),
	('FA000002','功能0002','name0002','update','warning','genericFormManager/name/name0002/name0003/0002','GF000001','1');



/*
Mysql innodb's foreign key has index automatically

*/

create unique index idx_platform_version on platform_data(id, version);

create unique index idx_change_request_version on change_request_data(id, version);

alter table change_request_data add constraint change_request4platform_fk
	foreign key(platform) references platform_data(id) on delete cascade on update cascade;
create unique index idx_transaction_version on transaction_data(id, version);

alter table transaction_data add constraint transaction4from_account_fk
	foreign key(from_account) references account_data(id) on delete cascade on update cascade;
alter table transaction_data add constraint transaction4to_account_fk
	foreign key(to_account) references account_data(id) on delete cascade on update cascade;
alter table transaction_data add constraint transaction4change_request_fk
	foreign key(change_request) references change_request_data(id) on delete cascade on update cascade;
create unique index idx_name_change_event_version on name_change_event_data(id, version);

alter table name_change_event_data add constraint name_change_event4account_fk
	foreign key(account) references account_data(id) on delete cascade on update cascade;
alter table name_change_event_data add constraint name_change_event4change_request_fk
	foreign key(change_request) references change_request_data(id) on delete cascade on update cascade;
create unique index idx_account_version on account_data(id, version);

alter table account_data add constraint account4platform_fk
	foreign key(platform) references platform_data(id) on delete cascade on update cascade;
create unique index idx_account_change_version on account_change_data(id, version);

alter table account_change_data add constraint account_change4account_fk
	foreign key(account) references account_data(id) on delete cascade on update cascade;
alter table account_change_data add constraint account_change4change_request_fk
	foreign key(change_request) references change_request_data(id) on delete cascade on update cascade;
create unique index idx_user_domain_version on user_domain_data(id, version);

create unique index idx_user_white_list_version on user_white_list_data(id, version);

alter table user_white_list_data add constraint user_white_list4domain_fk
	foreign key(domain) references user_domain_data(id) on delete cascade on update cascade;
create unique index idx_sec_user_version on sec_user_data(id, version);

alter table sec_user_data add constraint sec_user4domain_fk
	foreign key(domain) references user_domain_data(id) on delete cascade on update cascade;
alter table sec_user_data add constraint sec_user4blocking_fk
	foreign key(blocking) references sec_user_blocking_data(id) on delete cascade on update cascade;
create unique index idx_sec_user_blocking_version on sec_user_blocking_data(id, version);

create unique index idx_user_app_version on user_app_data(id, version);

alter table user_app_data add constraint user_app4sec_user_fk
	foreign key(sec_user) references sec_user_data(id) on delete cascade on update cascade;
create unique index idx_list_access_version on list_access_data(id, version);

alter table list_access_data add constraint list_access4app_fk
	foreign key(app) references user_app_data(id) on delete cascade on update cascade;
create unique index idx_object_access_version on object_access_data(id, version);

alter table object_access_data add constraint object_access4app_fk
	foreign key(app) references user_app_data(id) on delete cascade on update cascade;
create unique index idx_login_history_version on login_history_data(id, version);

alter table login_history_data add constraint login_history4sec_user_fk
	foreign key(sec_user) references sec_user_data(id) on delete cascade on update cascade;
create unique index idx_generic_form_version on generic_form_data(id, version);

create unique index idx_form_message_version on form_message_data(id, version);

alter table form_message_data add constraint form_message4form_fk
	foreign key(form) references generic_form_data(id) on delete cascade on update cascade;
create unique index idx_form_field_message_version on form_field_message_data(id, version);

alter table form_field_message_data add constraint form_field_message4form_fk
	foreign key(form) references generic_form_data(id) on delete cascade on update cascade;
create unique index idx_form_field_version on form_field_data(id, version);

alter table form_field_data add constraint form_field4form_fk
	foreign key(form) references generic_form_data(id) on delete cascade on update cascade;
create unique index idx_form_action_version on form_action_data(id, version);

alter table form_action_data add constraint form_action4form_fk
	foreign key(form) references generic_form_data(id) on delete cascade on update cascade;
-- create extra index for time, number and mobile phone

create index platform4founded_idx on platform_data(founded);
create index platform4version_idx on platform_data(version);
create index change_request4create_time_idx on change_request_data(create_time);
create index change_request4version_idx on change_request_data(version);
create index transaction4amount_idx on transaction_data(amount);
create index transaction4version_idx on transaction_data(version);
create index name_change_event4version_idx on name_change_event_data(version);
create index account4balance_idx on account_data(balance);
create index account4create_time_idx on account_data(create_time);
create index account4update_time_idx on account_data(update_time);
create index account4version_idx on account_data(version);
create index account_change4previous_balance_idx on account_change_data(previous_balance);
create index account_change4amount_idx on account_change_data(amount);
create index account_change4current_balance_idx on account_change_data(current_balance);
create index account_change4version_idx on account_change_data(version);
create index user_domain4version_idx on user_domain_data(version);
create index user_white_list4version_idx on user_white_list_data(version);
create index sec_user4mobile_idx on sec_user_data(mobile);
create index sec_user4verification_code_idx on sec_user_data(verification_code);
create index sec_user4verification_code_expire_idx on sec_user_data(verification_code_expire);
create index sec_user4last_login_time_idx on sec_user_data(last_login_time);
create index sec_user4version_idx on sec_user_data(version);
create index sec_user_blocking4block_time_idx on sec_user_blocking_data(block_time);
create index sec_user_blocking4version_idx on sec_user_blocking_data(version);
create index user_app4object_id_idx on user_app_data(object_id);
create index user_app4version_idx on user_app_data(version);
create index list_access4version_idx on list_access_data(version);
create index object_access4version_idx on object_access_data(version);
create index login_history4login_time_idx on login_history_data(login_time);
create index login_history4version_idx on login_history_data(version);
create index generic_form4version_idx on generic_form_data(version);
create index form_message4version_idx on form_message_data(version);
create index form_field_message4version_idx on form_field_message_data(version);
create index form_field4version_idx on form_field_data(version);
create index form_action4version_idx on form_action_data(version);










delete from list_access_data ;
delete from object_access_data ;
delete from user_app_data ;
delete from login_history_data ;
delete from sec_user_data ;
delete from user_domain_data ;
insert into user_domain_data values ('UD000001','用户区域','1');



insert into sec_user_data values('SU000001','User000001','13900000001','1000001@qq.com','24327F1C00D22210298A18D0DB9AA6C4C22DEAC4BEAE7C02E616442CA7764246', 'weixin_openid_000001', 'weixin_appid_000001', 'jwt_token_000001' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',NULL,'INIT',1);
insert into user_app_data values('UA000001','平台','SU000001','at',1,'MXWR','Platform','P000001','/link/to/app','1');
insert into user_app_data values('UA000002','我的账户','SU000001','lock',1,'MXWR','SecUser','SU000001','/link/to/app','1');
insert into sec_user_data values('SU000002','User000002','13900000002','1000002@qq.com','BB5210DAE99659C7164D7DBCFC51FB2D167D0DA372D58EF26A9F8533EEA2967C', 'weixin_openid_000002', 'weixin_appid_000002', 'jwt_token_000002' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',NULL,'INIT',1);
insert into user_app_data values('UA000003','账户','SU000002','address-book',1,'MXWR','Account','A000001','/link/to/app','1');
insert into user_app_data values('UA000004','我的账户','SU000002','lock',1,'MXWR','SecUser','SU000002','/link/to/app','1');
insert into sec_user_data values('SU000003','User000003','13900000003','1000003@qq.com','9D4104DF2774FDEAAE074CA35B052D8F664F4F99064C7BEAB0B589C2605C4EDA', 'weixin_openid_000003', 'weixin_appid_000003', 'jwt_token_000003' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',NULL,'INIT',1);
insert into user_app_data values('UA000005','用户域','SU000003','user',1,'MXWR','UserDomain','UD000001','/link/to/app','1');
insert into user_app_data values('UA000006','我的账户','SU000003','lock',1,'MXWR','SecUser','SU000003','/link/to/app','1');
insert into sec_user_data values('SU000004','User000004','13900000004','1000004@qq.com','9B223EBD008D7B544A3A640739EBE47459D3A4C5296DDA00F594FAF60FE88B28', 'weixin_openid_000004', 'weixin_appid_000004', 'jwt_token_000004' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',NULL,'INIT',1);
insert into user_app_data values('UA000007','安全用户','SU000004','user',1,'MXWR','SecUser','SU000001','/link/to/app','1');
insert into user_app_data values('UA000008','我的账户','SU000004','lock',1,'MXWR','SecUser','SU000004','/link/to/app','1');
insert into sec_user_data values('SU000005','User000005','13900000005','1000005@qq.com','AE5F93F319636A96963C06D035B97F004D18E61D80129EFEA331784A6E21DC5C', 'weixin_openid_000005', 'weixin_appid_000005', 'jwt_token_000005' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',NULL,'INIT',1);
insert into user_app_data values('UA000009','用户应用程序','SU000005','user',1,'MXWR','UserApp','UA000001','/link/to/app','1');
insert into user_app_data values('UA000010','我的账户','SU000005','lock',1,'MXWR','SecUser','SU000005','/link/to/app','1');

/* ------------------------------------------------------------------------ */


select id,pwd from sec_user_data;

/*
| 角色        | 用户名           | 密码         |
| ------------- |:-------------:|:-------------------:|
|平台|13900000001|DoubleChain!y1|
|账户|13900000002|DoubleChain!y1|
|用户域|13900000003|DoubleChain!y1|
|安全用户|13900000004|DoubleChain!y1|
|用户应用程序|13900000005|DoubleChain!y1|


*/



/* start with data patch */
/* The sql file is not found from: /Users/Philip/githome/web-code-generator/sky/data-patch/bank.sql */


/*

+----------+---------------------------------+---------------------+--------+
| Charset  | Description                     | Default collation   | Maxlen |
+----------+---------------------------------+---------------------+--------+
| gb2312   | GB2312 Simplified Chinese       | gb2312_chinese_ci   |      2 |
| gbk      | GBK Simplified Chinese          | gbk_chinese_ci      |      2 |
| utf8mb4  | UTF-8 Unicode                   | utf8mb4_general_ci  |      4 |
| utf32    | UTF-32 Unicode                  | utf32_general_ci    |      4 |
| gb18030  | China National Standard GB18030 | gb18030_chinese_ci  |      4 |
+----------+---------------------------------+---------------------+--------+

*/

