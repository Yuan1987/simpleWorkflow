/*
Navicat SQL Server Data Transfer

Source Server         : localhost_sqlserver
Source Server Version : 140000
Source Host           : localhost:1433
Source Database       : cetcDev
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 140000
File Encoding         : 65001

Date: 2018-05-28 14:18:25
*/


-- ----------------------------
-- Table structure for F_identitylink
-- ----------------------------
DROP TABLE [dbo].[F_identitylink]
GO
CREATE TABLE [dbo].[F_identitylink] (
[ID] varchar(36) NOT NULL ,
[ROLE_ID] varchar(36) NULL ,
[USER_ID] varchar(36) NULL ,
[TASK_ID] varchar(36) NULL ,
[PROC_INST_ID] varchar(36) NULL ,
[TYPE] varchar(36) NULL ,
[isdeleted] bit NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'F_identitylink', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'流程参与者信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_identitylink'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'流程参与者信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_identitylink'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'F_identitylink', 
'COLUMN', N'isdeleted')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用于转办'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_identitylink'
, @level2type = 'COLUMN', @level2name = N'isdeleted'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用于转办'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_identitylink'
, @level2type = 'COLUMN', @level2name = N'isdeleted'
GO

-- ----------------------------
-- Table structure for F_procdef
-- ----------------------------
DROP TABLE [dbo].[F_procdef]
GO
CREATE TABLE [dbo].[F_procdef] (
[ID] varchar(36) NOT NULL ,
[DEF_KEY] varchar(36) NULL ,
[definition] nvarchar(2000) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL ,
[version] int NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'F_procdef', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'流程定义表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_procdef'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'流程定义表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_procdef'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'F_procdef', 
'COLUMN', N'DEF_KEY')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'流程key'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_procdef'
, @level2type = 'COLUMN', @level2name = N'DEF_KEY'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'流程key'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'F_procdef'
, @level2type = 'COLUMN', @level2name = N'DEF_KEY'
GO

-- ----------------------------
-- Table structure for f_procinst
-- ----------------------------
DROP TABLE [dbo].[f_procinst]
GO
CREATE TABLE [dbo].[f_procinst] (
[ID] varchar(36) NOT NULL ,
[BUSINESS_ID] varchar(36) NULL ,
[PROC_DEF_ID] varchar(36) NULL ,
[START_TIME] datetime NULL ,
[END_TIME] datetime NULL ,
[PROC_DEF] varchar(2000) NULL ,
[START_ACT_KEY] varchar(100) NULL ,
[END_ACT_KEY] varchar(100) NULL ,
[START_USER_ID] varchar(36) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'f_procinst', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'流程实例表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'f_procinst'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'流程实例表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'f_procinst'
GO

-- ----------------------------
-- Table structure for F_TASK
-- ----------------------------
DROP TABLE [dbo].[F_TASK]
GO
CREATE TABLE [dbo].[F_TASK] (
[ID] varchar(36) NOT NULL ,
[NAME] nvarchar(100) NULL ,
[PROC_DEF_ID] varchar(36) NULL ,
[TASK_DEF_KEY] varchar(36) NULL ,
[PROC_INST_ID] varchar(36) NULL ,
[ASSIGNEE] varchar(36) NULL ,
[START_TIME] datetime NULL ,
[CLAIM_TIME] datetime NULL ,
[END_TIME] datetime NULL ,
[PRIORITY] int NULL ,
[isDeleted] bit NULL DEFAULT ((0)) 
)


GO

-- ----------------------------
-- Table structure for RS_Dictionary
-- ----------------------------
DROP TABLE [dbo].[RS_Dictionary]
GO
CREATE TABLE [dbo].[RS_Dictionary] (
[id] varchar(36) NOT NULL ,
[kind] varchar(255) NULL ,
[kindName] varchar(255) NULL ,
[code] varchar(255) NULL ,
[parentCode] varchar(255) NULL ,
[detail] varchar(255) NULL ,
[firstSpell] varchar(255) NULL ,
[isDeleted] bit NULL DEFAULT ((0)) 
)


GO

-- ----------------------------
-- Table structure for RS_Organization
-- ----------------------------
DROP TABLE [dbo].[RS_Organization]
GO
CREATE TABLE [dbo].[RS_Organization] (
[Id] varchar(36) NOT NULL ,
[DateTimeCreated] datetime2(7) NOT NULL DEFAULT (getdate()) ,
[DateTimeModified] datetime2(7) NOT NULL DEFAULT (getdate()) ,
[Depth] int NULL ,
[Description] nvarchar(1000) NULL ,
[DisplayName] nvarchar(255) NULL ,
[IsDeleted] bit NOT NULL DEFAULT ((0)) ,
[Name] nvarchar(255) NOT NULL ,
[Ordinal] int NOT NULL ,
[ParentId] varchar(36) NULL ,
[Path] nvarchar(1000) NULL ,
[TypeId] varchar(36) NULL ,
[ExtensionData] nvarchar(2048) NULL 
)


GO

-- ----------------------------
-- Table structure for RS_OrganizationDepth
-- ----------------------------
DROP TABLE [dbo].[RS_OrganizationDepth]
GO
CREATE TABLE [dbo].[RS_OrganizationDepth] (
[ParentId] varchar(36) NOT NULL ,
[ChildId] varchar(36) NOT NULL ,
[ParentDepth] int NULL ,
[Sn] int NOT NULL IDENTITY(1,1) ,
[ParentTypeId] varchar(36) NULL ,
[ChildDepth] int NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[RS_OrganizationDepth]', RESEED, 7739)
GO

-- ----------------------------
-- Table structure for RS_OrganizationType
-- ----------------------------
DROP TABLE [dbo].[RS_OrganizationType]
GO
CREATE TABLE [dbo].[RS_OrganizationType] (
[Id] varchar(36) NOT NULL ,
[DateTimeCreated] datetime2(7) NOT NULL ,
[DateTimeModified] datetime2(7) NOT NULL ,
[DisplayName] nvarchar(255) NULL ,
[IsDeleted] bit NOT NULL ,
[Name] nvarchar(255) NULL ,
[Ordinal] int NOT NULL 
)


GO

-- ----------------------------
-- Table structure for RS_OrganizationUser
-- ----------------------------
DROP TABLE [dbo].[RS_OrganizationUser]
GO
CREATE TABLE [dbo].[RS_OrganizationUser] (
[OrganizationId] varchar(36) NOT NULL ,
[UserId] varchar(36) NOT NULL ,
[ExtensionData] nvarchar(MAX) NULL ,
[IsPrincipal] bit NULL DEFAULT ((1)) ,
[Ordinal] int NULL ,
[Sn] int NOT NULL IDENTITY(1,1) 
)


GO
DBCC CHECKIDENT(N'[dbo].[RS_OrganizationUser]', RESEED, 5318)
GO

-- ----------------------------
-- Table structure for RS_resource
-- ----------------------------
DROP TABLE [dbo].[RS_resource]
GO
CREATE TABLE [dbo].[RS_resource] (
[id] varchar(36) NOT NULL ,
[name] varchar(100) NULL ,
[type] varchar(50) NULL ,
[url] varchar(200) NULL ,
[parent_id] varchar(36) NULL ,
[parent_ids] varchar(2000) NULL ,
[permission] varchar(100) NULL ,
[isdeleted] bit NULL DEFAULT ((0)) ,
[className] varchar(255) NULL ,
[orderNo] int NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_resource', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_resource', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'类型（菜单或按钮）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'类型（菜单或按钮）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_resource', 
'COLUMN', N'url')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'url'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'url'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_resource', 
'COLUMN', N'permission')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'permission'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'permission'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_resource', 
'COLUMN', N'className')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'菜单图标样式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'className'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'菜单图标样式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'className'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_resource', 
'COLUMN', N'orderNo')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'菜单序号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'orderNo'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'菜单序号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_resource'
, @level2type = 'COLUMN', @level2name = N'orderNo'
GO

-- ----------------------------
-- Table structure for RS_Role
-- ----------------------------
DROP TABLE [dbo].[RS_Role]
GO
CREATE TABLE [dbo].[RS_Role] (
[Id] varchar(36) NOT NULL ,
[DateTimeCreated] datetime2(7) NOT NULL DEFAULT (getdate()) ,
[DateTimeModified] datetime2(7) NOT NULL DEFAULT (getdate()) ,
[Description] nvarchar(1000) NULL ,
[DisplayName] nvarchar(255) NULL ,
[IsBuiltin] bit NOT NULL DEFAULT ((0)) ,
[IsDeleted] bit NOT NULL DEFAULT ((0)) ,
[IsEnabled] bit NOT NULL DEFAULT ((1)) ,
[Name] nvarchar(255) NULL ,
[Ordinal] int NOT NULL ,
[OrganizationId] varchar(36) NULL ,
[Sn] int NOT NULL IDENTITY(1,1) ,
[TypeId] varchar(36) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[RS_Role]', RESEED, 45)
GO

-- ----------------------------
-- Table structure for RS_RoleResource
-- ----------------------------
DROP TABLE [dbo].[RS_RoleResource]
GO
CREATE TABLE [dbo].[RS_RoleResource] (
[roleId] varchar(36) NOT NULL ,
[resourceId] varchar(36) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for RS_RoleType
-- ----------------------------
DROP TABLE [dbo].[RS_RoleType]
GO
CREATE TABLE [dbo].[RS_RoleType] (
[Id] varchar(36) NOT NULL ,
[DateTimeCreated] datetime2(7) NOT NULL ,
[DateTimeModified] datetime2(7) NOT NULL ,
[DisplayName] nvarchar(255) NULL ,
[IsDeleted] bit NOT NULL ,
[Name] nvarchar(255) NULL ,
[Ordinal] int NOT NULL ,
[Level] int NULL 
)


GO

-- ----------------------------
-- Table structure for RS_RoleUser
-- ----------------------------
DROP TABLE [dbo].[RS_RoleUser]
GO
CREATE TABLE [dbo].[RS_RoleUser] (
[RoleId] varchar(36) NOT NULL ,
[UserId] varchar(36) NOT NULL ,
[Ordinal] int NULL ,
[Sn] int NOT NULL IDENTITY(1,1) ,
[OrganizationId] varchar(36) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[RS_RoleUser]', RESEED, 3695)
GO

-- ----------------------------
-- Table structure for rs_Serial
-- ----------------------------
DROP TABLE [dbo].[rs_Serial]
GO
CREATE TABLE [dbo].[rs_Serial] (
[id] varchar(36) NOT NULL ,
[name] varchar(100) NULL ,
[code] varchar(36) NULL ,
[isdeleted] bit NULL ,
[parentid] varchar(36) NULL DEFAULT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'rs_Serial', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'rs_Serial'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'rs_Serial'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'rs_Serial'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'rs_Serial'
GO

-- ----------------------------
-- Table structure for RS_User
-- ----------------------------
DROP TABLE [dbo].[RS_User]
GO
CREATE TABLE [dbo].[RS_User] (
[Id] varchar(36) NOT NULL ,
[AccountName] nvarchar(255) NULL ,
[DateTimeCreated] datetime2(7) NOT NULL DEFAULT (getdate()) ,
[DateTimeModified] datetime2(7) NOT NULL DEFAULT (getdate()) ,
[DisplayName] nvarchar(255) NULL ,
[ExtensionData] nvarchar(255) NULL ,
[IdNumber] nvarchar(255) NULL ,
[IsDeleted] bit NOT NULL DEFAULT ((0)) ,
[IsEnabled] bit NOT NULL DEFAULT ((1)) ,
[Sex] int NULL ,
[WorkNumber] nvarchar(255) NULL ,
[password] nvarchar(255) NULL ,
[email] nvarchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_User', 
'COLUMN', N'IdNumber')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'身份证'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_User'
, @level2type = 'COLUMN', @level2name = N'IdNumber'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'身份证'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_User'
, @level2type = 'COLUMN', @level2name = N'IdNumber'
GO

-- ----------------------------
-- Table structure for RS_USER_POST_INFO
-- ----------------------------
DROP TABLE [dbo].[RS_USER_POST_INFO]
GO
CREATE TABLE [dbo].[RS_USER_POST_INFO] (
[userId] varchar(36) NULL ,
[JobsTypeId] varchar(36) NULL ,
[JobsType] varchar(50) NULL ,
[JobsGrade] varchar(50) NULL ,
[havequalificationseqID] varchar(50) NULL ,
[havequalificationseq] varchar(50) NULL ,
[havequalificationgrade] varchar(50) NULL ,
[havequalificationgradeTime] varchar(50) NULL ,
[workconditionsseq] varchar(50) NULL ,
[workconditionsgrade] varchar(50) NULL ,
[workconditionsgradeAnnual] varchar(50) NULL ,
[nuclearresultsfront] bit NULL ,
[nuclearresultsfrontAnnual] varchar(50) NULL ,
[improvementplanclosedloop] bit NULL ,
[graduateschool] varchar(50) NULL ,
[majors] varchar(50) NULL ,
[educational] varchar(50) NULL ,
[degree] varchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'RS_USER_POST_INFO', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'现职信息'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_USER_POST_INFO'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'现职信息'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'RS_USER_POST_INFO'
GO

-- ----------------------------
-- Table structure for rs_user_Serial
-- ----------------------------
DROP TABLE [dbo].[rs_user_Serial]
GO
CREATE TABLE [dbo].[rs_user_Serial] (
[id] varchar(36) NOT NULL ,
[uid] varchar(36) NULL ,
[sid] varchar(36) NULL ,
[type] varchar(10) NULL DEFAULT ('0') 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'rs_user_Serial', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'rs_user_Serial'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'rs_user_Serial'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'rs_user_Serial'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'rs_user_Serial'
GO

-- ----------------------------
-- Table structure for sys_ability
-- ----------------------------
DROP TABLE [dbo].[sys_ability]
GO
CREATE TABLE [dbo].[sys_ability] (
[ID] varchar(36) NOT NULL ,
[name] varchar(200) NULL ,
[typeId] varchar(36) NULL ,
[Serial] varchar(100) NULL ,
[TheLevel] varchar(100) NULL ,
[evidence] varchar(2000) NULL ,
[Score] float(53) NULL ,
[formula] varchar(36) NULL ,
[passmark] float(53) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL 
)


GO

-- ----------------------------
-- Table structure for sys_evaluationform
-- ----------------------------
DROP TABLE [dbo].[sys_evaluationform]
GO
CREATE TABLE [dbo].[sys_evaluationform] (
[ID] varchar(36) NOT NULL ,
[name] varchar(200) NULL ,
[Serial] varchar(100) NULL ,
[level] varchar(100) NULL ,
[version] varchar(100) NULL ,
[status] varchar(200) NULL ,
[total] float(53) NULL ,
[passmark] float(53) NULL ,
[judgesNumber] int NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL ,
[standardFile] varchar(200) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'sys_evaluationform', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态 ：草稿、已发布、作废'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_evaluationform'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态 ：草稿、已发布、作废'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'sys_evaluationform'
, @level2type = 'COLUMN', @level2name = N'status'
GO

-- ----------------------------
-- Table structure for sys_evaluationform_ability
-- ----------------------------
DROP TABLE [dbo].[sys_evaluationform_ability]
GO
CREATE TABLE [dbo].[sys_evaluationform_ability] (
[ID] varchar(36) NOT NULL ,
[evaluationformId] varchar(36) NULL ,
[abilityId] varchar(36) NULL 
)


GO

-- ----------------------------
-- Table structure for SYS_LOG
-- ----------------------------
DROP TABLE [dbo].[SYS_LOG]
GO
CREATE TABLE [dbo].[SYS_LOG] (
[ID] varchar(36) NOT NULL ,
[userId] varchar(36) NULL ,
[userName] varchar(255) NULL ,
[type] varchar(255) NULL ,
[level] varchar(10) NULL ,
[msg] varchar(4000) NULL ,
[createdTime] datetime NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'SYS_LOG', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'类别名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'SYS_LOG'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'类别名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'SYS_LOG'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'SYS_LOG', 
'COLUMN', N'level')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'日志级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'SYS_LOG'
, @level2type = 'COLUMN', @level2name = N'level'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'日志级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'SYS_LOG'
, @level2type = 'COLUMN', @level2name = N'level'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'SYS_LOG', 
'COLUMN', N'msg')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'日志信息（对应业务数据id之类的信息）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'SYS_LOG'
, @level2type = 'COLUMN', @level2name = N'msg'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'日志信息（对应业务数据id之类的信息）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'SYS_LOG'
, @level2type = 'COLUMN', @level2name = N'msg'
GO

-- ----------------------------
-- Table structure for t_ability
-- ----------------------------
DROP TABLE [dbo].[t_ability]
GO
CREATE TABLE [dbo].[t_ability] (
[ID] varchar(36) NOT NULL ,
[userId] varchar(36) NULL ,
[sys_abilityId] varchar(36) NULL ,
[name] varchar(200) NULL ,
[typeId] varchar(36) NULL ,
[Serial] varchar(100) NULL ,
[TheLevel] varchar(100) NULL ,
[evidence] varchar(2000) NULL ,
[description] varchar(4000) NULL ,
[Score] float(53) NULL ,
[formula] varchar(36) NULL ,
[scoring] float(53) NULL ,
[passmark] float(53) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL 
)


GO

-- ----------------------------
-- Table structure for t_ability_evaluate
-- ----------------------------
DROP TABLE [dbo].[t_ability_evaluate]
GO
CREATE TABLE [dbo].[t_ability_evaluate] (
[ID] varchar(36) NOT NULL ,
[abilityId] varchar(36) NULL ,
[evaluationformId] varchar(36) NULL ,
[assessorId] varchar(36) NULL ,
[assessor] varchar(36) NULL ,
[scoring] float(53) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL ,
[note] nvarchar(500) NULL ,
[abilityName] nvarchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for T_Apply
-- ----------------------------
DROP TABLE [dbo].[T_Apply]
GO
CREATE TABLE [dbo].[T_Apply] (
[id] nvarchar(50) NOT NULL ,
[No] nvarchar(50) NULL ,
[Type] nvarchar(20) NULL ,
[Seq] nvarchar(50) NULL ,
[Grade] nvarchar(50) NULL ,
[UserId] nvarchar(50) NULL ,
[UserName] nvarchar(50) NULL ,
[DeptCode] nvarchar(50) NULL ,
[DeptName] nvarchar(50) NULL ,
[Status] nvarchar(8) NULL ,
[FlowStatus] nvarchar(8) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL ,
[UnqualifiedQualifications] nvarchar(500) NULL ,
[AbnormalityReasons] nvarchar(500) NULL ,
[QualificationMatching] nvarchar(1000) NULL ,
[GraduateSchool] nvarchar(50) NULL ,
[majors] nvarchar(50) NULL ,
[Educational] nvarchar(50) NULL ,
[degree] nvarchar(50) NULL ,
[Experience] nvarchar(1000) NULL ,
[StandardsMatching] nvarchar(1000) NULL ,
[JobsType] nvarchar(50) NULL ,
[JobsGrade] nvarchar(20) NULL ,
[HaveQualificationSeq] nvarchar(50) NULL ,
[HaveQualificationGrade] nvarchar(50) NULL ,
[HaveQualificationGrade_Time] nvarchar(50) NULL ,
[WorkConditionsSeq] nvarchar(50) NULL ,
[WorkConditionsGrade] nvarchar(50) NULL ,
[WorkConditionsGrade_annual] nvarchar(20) NULL ,
[NuclearResultsFront] nvarchar(8) NULL ,
[NuclearResultsFront_annual] nvarchar(20) NULL ,
[ImprovementPlanClosedloop] bit NULL ,
[processId] varchar(36) NULL ,
[submitTime] datetime NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'No')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申请单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'No'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申请单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'No'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'Type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申请类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申请类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'Seq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'序列'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Seq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'序列'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Seq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'Grade')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Grade'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Grade'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'UserId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申请人Guid'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'UserId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申请人Guid'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'UserId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'UserName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申请人姓名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'UserName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申请人姓名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'UserName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'DeptCode')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'部门编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'DeptCode'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'部门编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'DeptCode'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'DeptName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'部门名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'DeptName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'部门名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'DeptName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'Status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态： 0： 草稿    1：已提交  2：审批完成   X：删除  T：退回'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态： 0： 草稿    1：已提交  2：审批完成   X：删除  T：退回'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'FlowStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'工作流状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'FlowStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'工作流状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'FlowStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'UnqualifiedQualifications')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'不满足的任职资格申请条件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'UnqualifiedQualifications'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'不满足的任职资格申请条件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'UnqualifiedQualifications'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'AbnormalityReasons')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'破格原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'AbnormalityReasons'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'破格原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'AbnormalityReasons'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'QualificationMatching')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申报任职资格级别典型能力满足情况'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'QualificationMatching'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申报任职资格级别典型能力满足情况'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'QualificationMatching'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'GraduateSchool')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'毕业学校'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'GraduateSchool'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'毕业学校'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'GraduateSchool'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'majors')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'专业'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'majors'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'专业'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'majors'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'Educational')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'学历'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Educational'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'学历'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Educational'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'degree')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'学位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'degree'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'学位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'degree'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'Experience')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'经历简诉'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Experience'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'经历简诉'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'Experience'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'StandardsMatching')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'社会标准满足情况'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'StandardsMatching'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'社会标准满足情况'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'StandardsMatching'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'JobsType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'岗位职种'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'JobsType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'岗位职种'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'JobsType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'JobsGrade')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'岗位层级'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'JobsGrade'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'岗位层级'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'JobsGrade'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'HaveQualificationSeq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'任职资格序列'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'HaveQualificationSeq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'任职资格序列'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'HaveQualificationSeq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'HaveQualificationGrade')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'任职资格级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'HaveQualificationGrade'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'任职资格级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'HaveQualificationGrade'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'HaveQualificationGrade_Time')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'取得资格时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'HaveQualificationGrade_Time'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'取得资格时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'HaveQualificationGrade_Time'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'WorkConditionsSeq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上岗条件序列'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'WorkConditionsSeq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上岗条件序列'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'WorkConditionsSeq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'WorkConditionsGrade')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上岗条件层级'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'WorkConditionsGrade'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上岗条件层级'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'WorkConditionsGrade'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'WorkConditionsGrade_annual')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上岗条件年度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'WorkConditionsGrade_annual'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上岗条件年度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'WorkConditionsGrade_annual'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'NuclearResultsFront')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'取得资格时间后年度考核结果是否有优秀或排序靠前   0:否 1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'NuclearResultsFront'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'取得资格时间后年度考核结果是否有优秀或排序靠前   0:否 1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'NuclearResultsFront'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'NuclearResultsFront_annual')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'取得资格时间后年度考核结果是否有优秀或排序靠前的年度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'NuclearResultsFront_annual'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'取得资格时间后年度考核结果是否有优秀或排序靠前的年度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'NuclearResultsFront_annual'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'ImprovementPlanClosedloop')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'改进计划是否闭环  0：否 1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'ImprovementPlanClosedloop'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'改进计划是否闭环  0：否 1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'ImprovementPlanClosedloop'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'processId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'流程id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'processId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'流程id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'processId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply', 
'COLUMN', N'submitTime')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'提交时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'submitTime'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'提交时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply'
, @level2type = 'COLUMN', @level2name = N'submitTime'
GO

-- ----------------------------
-- Table structure for T_Apply_CheckDetail
-- ----------------------------
DROP TABLE [dbo].[T_Apply_CheckDetail]
GO
CREATE TABLE [dbo].[T_Apply_CheckDetail] (
[id] nvarchar(50) NOT NULL ,
[applyId] nvarchar(50) NULL ,
[nodeName] nvarchar(50) NULL ,
[result] bit NULL ,
[opinion] nvarchar(500) NULL ,
[createdTime] varchar(36) NULL ,
[userId] nvarchar(50) NULL ,
[userName] nvarchar(50) NULL ,
[taskId] nvarchar(50) NULL ,
[question1] nvarchar(500) NULL ,
[question2] nvarchar(500) NULL ,
[question3] nvarchar(500) NULL ,
[nodeCode] nvarchar(50) NULL ,
[isback] bit NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply_CheckDetail', 
'COLUMN', N'applyId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申请id或测评表Id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'applyId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申请id或测评表Id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'applyId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply_CheckDetail', 
'COLUMN', N'result')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批结果 agree: 同意    disagree：不同意'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'result'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批结果 agree: 同意    disagree：不同意'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'result'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply_CheckDetail', 
'COLUMN', N'opinion')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批意见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'opinion'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批意见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'opinion'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Apply_CheckDetail', 
'COLUMN', N'isback')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否是退回'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'isback'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否是退回'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Apply_CheckDetail'
, @level2type = 'COLUMN', @level2name = N'isback'
GO

-- ----------------------------
-- Table structure for T_Attachment_File
-- ----------------------------
DROP TABLE [dbo].[T_Attachment_File]
GO
CREATE TABLE [dbo].[T_Attachment_File] (
[Guid] nvarchar(50) NOT NULL ,
[UserGuid] nvarchar(50) NULL ,
[IsFolder] int NULL ,
[PId] nvarchar(50) NULL ,
[FileName] nvarchar(50) NULL ,
[FriendlyFileName] nvarchar(80) NULL ,
[FileContentType] nvarchar(80) NULL ,
[FileSize] int NULL ,
[FileAccessPath] nvarchar(300) NULL ,
[FilePhysicalPath] nvarchar(300) NULL ,
[CreateTime] datetime NULL ,
[CreateUserId] nvarchar(50) NULL ,
[IsDeleted] int NULL ,
[description] nvarchar(2000) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'UserGuid')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户Guid'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'UserGuid'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户Guid'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'UserGuid'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'IsFolder')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否文件夹  0：否  1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'IsFolder'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否文件夹  0：否  1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'IsFolder'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'PId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'父节点  根节点为null'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'PId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'父节点  根节点为null'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'PId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'FileName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'唯一文件名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'唯一文件名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'FriendlyFileName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'真实文件名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FriendlyFileName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'真实文件名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FriendlyFileName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'FileContentType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'文件类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileContentType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'文件类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileContentType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'FileSize')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'文件大小'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileSize'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'文件大小'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileSize'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'FileAccessPath')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'文件访问路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileAccessPath'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'文件访问路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FileAccessPath'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'FilePhysicalPath')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'文件物理路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FilePhysicalPath'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'文件物理路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'FilePhysicalPath'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'T_Attachment_File', 
'COLUMN', N'IsDeleted')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'0: 正常  1：删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'IsDeleted'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'0: 正常  1：删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'T_Attachment_File'
, @level2type = 'COLUMN', @level2name = N'IsDeleted'
GO

-- ----------------------------
-- Table structure for t_Attachment_File_relation
-- ----------------------------
DROP TABLE [dbo].[t_Attachment_File_relation]
GO
CREATE TABLE [dbo].[t_Attachment_File_relation] (
[id] varchar(36) NOT NULL ,
[ywId] varchar(36) NOT NULL ,
[fileId] varchar(36) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for t_evaluateform_opinion
-- ----------------------------
DROP TABLE [dbo].[t_evaluateform_opinion]
GO
CREATE TABLE [dbo].[t_evaluateform_opinion] (
[ID] varchar(36) NOT NULL ,
[evaluationformId] varchar(36) NULL ,
[assessorId] varchar(36) NULL ,
[assessor] varchar(36) NULL ,
[opinion] varchar(2000) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL 
)


GO

-- ----------------------------
-- Table structure for t_evaluationform
-- ----------------------------
DROP TABLE [dbo].[t_evaluationform]
GO
CREATE TABLE [dbo].[t_evaluationform] (
[ID] varchar(36) NOT NULL ,
[userId] varchar(36) NULL ,
[sysFormId] varchar(36) NOT NULL ,
[name] varchar(200) NULL ,
[Serial] varchar(100) NULL ,
[level] varchar(100) NULL ,
[version] varchar(100) NULL ,
[status] nvarchar(10) NULL ,
[total] float(53) NULL ,
[scoring] float(53) NULL ,
[passmark] float(53) NULL ,
[assessorNumber] int NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL ,
[standardFile] varchar(200) NULL ,
[applyId] varchar(36) NULL ,
[submitTime] datetime NULL ,
[processId] varchar(36) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_evaluationform', 
'COLUMN', N'applyId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'申报ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_evaluationform'
, @level2type = 'COLUMN', @level2name = N'applyId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'申报ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_evaluationform'
, @level2type = 'COLUMN', @level2name = N'applyId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_evaluationform', 
'COLUMN', N'processId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'流程id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_evaluationform'
, @level2type = 'COLUMN', @level2name = N'processId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'流程id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_evaluationform'
, @level2type = 'COLUMN', @level2name = N'processId'
GO

-- ----------------------------
-- Table structure for t_evaluationform_ability
-- ----------------------------
DROP TABLE [dbo].[t_evaluationform_ability]
GO
CREATE TABLE [dbo].[t_evaluationform_ability] (
[ID] varchar(36) NOT NULL ,
[evaluationformId] varchar(36) NULL ,
[abilityId] varchar(36) NULL 
)


GO

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE [dbo].[t_exam]
GO
CREATE TABLE [dbo].[t_exam] (
[id] nvarchar(50) NOT NULL ,
[name] nvarchar(20) NULL ,
[kind] nvarchar(50) NULL ,
[date] nvarchar(50) NULL ,
[score] nvarchar(50) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL ,
[seq] nvarchar(50) NULL ,
[level] nvarchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_exam', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'考试人姓名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'考试人姓名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_exam', 
'COLUMN', N'kind')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'考试类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'kind'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'考试类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'kind'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_exam', 
'COLUMN', N'date')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'考试时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'date'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'考试时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'date'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_exam', 
'COLUMN', N'score')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'考试成绩'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'score'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'考试成绩'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_exam'
, @level2type = 'COLUMN', @level2name = N'score'
GO

-- ----------------------------
-- Table structure for t_markSetting
-- ----------------------------
DROP TABLE [dbo].[t_markSetting]
GO
CREATE TABLE [dbo].[t_markSetting] (
[id] varchar(36) NOT NULL ,
[level] int NULL 
)


GO

-- ----------------------------
-- Table structure for t_plan
-- ----------------------------
DROP TABLE [dbo].[t_plan]
GO
CREATE TABLE [dbo].[t_plan] (
[id] nvarchar(50) NOT NULL ,
[name] nvarchar(50) NULL ,
[type] nvarchar(50) NULL ,
[serial] nvarchar(50) NULL ,
[level] nvarchar(50) NULL ,
[sYear] int NULL ,
[status] nvarchar(50) NULL ,
[starttime] nvarchar(50) NULL ,
[endtime] nvarchar(50) NULL ,
[DateTimeCreated] datetime NULL ,
[DateTimeModified] datetime NULL ,
[isDeleted] bit NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_plan', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'计划名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_plan'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'计划名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_plan'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Function structure for DicDecode
-- ----------------------------
DROP FUNCTION [dbo].[DicDecode]
GO
CREATE FUNCTION [dbo].[DicDecode]
( @kind AS varchar(100) ,
  @code AS varchar(100) 
)
RETURNS NVARCHAR(100)
WITH RETURNS NULL ON NULL INPUT 
AS
BEGIN
	Declare @detail NVARCHAR(100)

	if (@kind='sbxl')
		Select @detail=name from rs_Serial where id=@code;
	else 
	  Select @detail=detail from RS_Dictionary where kind=@kind and code=@code
  RETURN @detail
END







GO

-- ----------------------------
-- Indexes structure for table F_identitylink
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table F_identitylink
-- ----------------------------
ALTER TABLE [dbo].[F_identitylink] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table F_procdef
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table F_procdef
-- ----------------------------
ALTER TABLE [dbo].[F_procdef] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table f_procinst
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table f_procinst
-- ----------------------------
ALTER TABLE [dbo].[f_procinst] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table F_TASK
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table F_TASK
-- ----------------------------
ALTER TABLE [dbo].[F_TASK] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table RS_Dictionary
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_Dictionary
-- ----------------------------
ALTER TABLE [dbo].[RS_Dictionary] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table RS_Organization
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_Organization
-- ----------------------------
ALTER TABLE [dbo].[RS_Organization] ADD PRIMARY KEY ([Id])
GO

-- ----------------------------
-- Indexes structure for table RS_OrganizationDepth
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_OrganizationDepth
-- ----------------------------
ALTER TABLE [dbo].[RS_OrganizationDepth] ADD PRIMARY KEY ([ParentId], [ChildId])
GO

-- ----------------------------
-- Indexes structure for table RS_OrganizationType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_OrganizationType
-- ----------------------------
ALTER TABLE [dbo].[RS_OrganizationType] ADD PRIMARY KEY ([Id])
GO

-- ----------------------------
-- Indexes structure for table RS_OrganizationUser
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_OrganizationUser
-- ----------------------------
ALTER TABLE [dbo].[RS_OrganizationUser] ADD PRIMARY KEY ([OrganizationId], [UserId])
GO

-- ----------------------------
-- Indexes structure for table RS_resource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_resource
-- ----------------------------
ALTER TABLE [dbo].[RS_resource] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table RS_Role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_Role
-- ----------------------------
ALTER TABLE [dbo].[RS_Role] ADD PRIMARY KEY ([Id])
GO

-- ----------------------------
-- Indexes structure for table RS_RoleResource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_RoleResource
-- ----------------------------
ALTER TABLE [dbo].[RS_RoleResource] ADD PRIMARY KEY ([roleId], [resourceId])
GO

-- ----------------------------
-- Indexes structure for table RS_RoleType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_RoleType
-- ----------------------------
ALTER TABLE [dbo].[RS_RoleType] ADD PRIMARY KEY ([Id])
GO

-- ----------------------------
-- Indexes structure for table RS_RoleUser
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table RS_RoleUser
-- ----------------------------
ALTER TABLE [dbo].[RS_RoleUser] ADD PRIMARY KEY ([RoleId], [UserId], [OrganizationId])
GO

-- ----------------------------
-- Indexes structure for table rs_Serial
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table rs_Serial
-- ----------------------------
ALTER TABLE [dbo].[rs_Serial] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Uniques structure for table rs_Serial
-- ----------------------------
ALTER TABLE [dbo].[rs_Serial] ADD UNIQUE ([code] ASC)
GO

-- ----------------------------
-- Indexes structure for table RS_User
-- ----------------------------
CREATE UNIQUE INDEX [user_accountname_index] ON [dbo].[RS_User]
([AccountName] ASC) 
WITH (IGNORE_DUP_KEY = ON)
GO

-- ----------------------------
-- Primary Key structure for table RS_User
-- ----------------------------
ALTER TABLE [dbo].[RS_User] ADD PRIMARY KEY ([Id])
GO

-- ----------------------------
-- Indexes structure for table rs_user_Serial
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table rs_user_Serial
-- ----------------------------
ALTER TABLE [dbo].[rs_user_Serial] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table sys_ability
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table sys_ability
-- ----------------------------
ALTER TABLE [dbo].[sys_ability] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table sys_evaluationform
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table sys_evaluationform
-- ----------------------------
ALTER TABLE [dbo].[sys_evaluationform] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table sys_evaluationform_ability
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table sys_evaluationform_ability
-- ----------------------------
ALTER TABLE [dbo].[sys_evaluationform_ability] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table SYS_LOG
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table SYS_LOG
-- ----------------------------
ALTER TABLE [dbo].[SYS_LOG] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table t_ability
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_ability
-- ----------------------------
ALTER TABLE [dbo].[t_ability] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table t_ability_evaluate
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_ability_evaluate
-- ----------------------------
ALTER TABLE [dbo].[t_ability_evaluate] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table T_Apply
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_Apply
-- ----------------------------
ALTER TABLE [dbo].[T_Apply] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table T_Attachment_File
-- ----------------------------
CREATE INDEX [NonClusteredIndex-20170918-085903] ON [dbo].[T_Attachment_File]
([UserGuid] ASC) 
GO

-- ----------------------------
-- Primary Key structure for table T_Attachment_File
-- ----------------------------
ALTER TABLE [dbo].[T_Attachment_File] ADD PRIMARY KEY ([Guid])
GO

-- ----------------------------
-- Indexes structure for table t_Attachment_File_relation
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_Attachment_File_relation
-- ----------------------------
ALTER TABLE [dbo].[t_Attachment_File_relation] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_evaluateform_opinion
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_evaluateform_opinion
-- ----------------------------
ALTER TABLE [dbo].[t_evaluateform_opinion] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table t_evaluationform
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_evaluationform
-- ----------------------------
ALTER TABLE [dbo].[t_evaluationform] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table t_evaluationform_ability
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_evaluationform_ability
-- ----------------------------
ALTER TABLE [dbo].[t_evaluationform_ability] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table t_exam
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_exam
-- ----------------------------
ALTER TABLE [dbo].[t_exam] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_markSetting
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_markSetting
-- ----------------------------
ALTER TABLE [dbo].[t_markSetting] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_plan
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_plan
-- ----------------------------
ALTER TABLE [dbo].[t_plan] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[RS_OrganizationDepth]
-- ----------------------------
ALTER TABLE [dbo].[RS_OrganizationDepth] ADD FOREIGN KEY ([ChildId]) REFERENCES [dbo].[RS_Organization] ([Id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[RS_OrganizationDepth] ADD FOREIGN KEY ([ParentId]) REFERENCES [dbo].[RS_Organization] ([Id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[RS_OrganizationUser]
-- ----------------------------
ALTER TABLE [dbo].[RS_OrganizationUser] ADD FOREIGN KEY ([OrganizationId]) REFERENCES [dbo].[RS_Organization] ([Id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[RS_OrganizationUser] ADD FOREIGN KEY ([UserId]) REFERENCES [dbo].[RS_User] ([Id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[sys_evaluationform_ability]
-- ----------------------------
ALTER TABLE [dbo].[sys_evaluationform_ability] ADD FOREIGN KEY ([abilityId]) REFERENCES [dbo].[sys_ability] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[sys_evaluationform_ability] ADD FOREIGN KEY ([evaluationformId]) REFERENCES [dbo].[sys_evaluationform] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[t_evaluationform_ability]
-- ----------------------------
ALTER TABLE [dbo].[t_evaluationform_ability] ADD FOREIGN KEY ([abilityId]) REFERENCES [dbo].[t_ability] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
ALTER TABLE [dbo].[t_evaluationform_ability] ADD FOREIGN KEY ([evaluationformId]) REFERENCES [dbo].[t_evaluationform] ([ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
