DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details
(
    client_id               VARCHAR(255),
    client_name             VARCHAR(50),
    resource_ids            VARCHAR(255),
    client_secret           VARCHAR(255),
    scope                   VARCHAR(255),
    authorized_grant_types  VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities             VARCHAR(255),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(255)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'client唯一标识',
  `client_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '无用',
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端密码',
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端授权范围',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权码时 重定向的uri',
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权服务器用户的权限',
  `access_token_validity` int(0) NULL DEFAULT NULL COMMENT '存活时间',
  `refresh_token_validity` int(0) NULL DEFAULT NULL COMMENT '存活时间',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附加信息',
  `autoapprove` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否自动授权'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

INSERT INTO `oauth_client_details` VALUES ('web-client', '第三方前端', NULL, '$2a$10$6nunIKglEYNccmFJFIH3Aef5rqEnSsHTYeNbB3ht3g.V724Sl43du', 'todo.read,todo.write', 'authorization_code,password,refresh_token,client_credentials', 'http://localhost:8082/login/oauth2/code/web-client-auth-code', NULL, 900, 31536000, '{}', NULL);
INSERT INTO `oauth_client_details` VALUES ('admin-client', '后台管理客户端', NULL, '$2a$10$6nunIKglEYNccmFJFIH3Aef5rqEnSsHTYeNbB3ht3g.V724Sl43du', 'user.admin,client.admin', 'authorization_code,password,refresh_token,client_credentials', 'http://localhost:4001', NULL, 60, 31536000, '{}', '1');
INSERT INTO `oauth_client_details` VALUES ('ios-client', 'iOS 客户端', NULL, '$2a$10$6nunIKglEYNccmFJFIH3Aef5rqEnSsHTYeNbB3ht3g.V724Sl43du', 'todo.read,todo.write', 'authorization_code,password,refresh_token,client_credentials', 'com.example.app://action', NULL, 900, 31536000, '{}', NULL);
INSERT INTO `oauth_client_details` VALUES ('android-client', 'Android 客户端', NULL, '$2a$10$6nunIKglEYNccmFJFIH3Aef5rqEnSsHTYeNbB3ht3g.V724Sl43du', 'todo.read,todo.write', 'authorization_code,password,refresh_token,client_credentials', 'com.example.app://action', NULL, 900, 31536000, '{}', NULL);
INSERT INTO `oauth_client_details` VALUES ('todos-service', '微服务', NULL, '$2a$10$6nunIKglEYNccmFJFIH3Aef5rqEnSsHTYeNbB3ht3g.V724Sl43du', 'todo.read,todo.write', 'authorization_code,refresh_token,client_credentials', 'http://localhost:8082/authorized', NULL, 900, 31536000, '{}', '1');
