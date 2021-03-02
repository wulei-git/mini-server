CREATE TABLE `refresh_table` (
`id_refresh_table` int(4) NOT NULL AUTO_INCREMENT,
`name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
`status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
`service_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
`updated_by` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
`updated_date` timestamp NULL DEFAULT NULL,
`created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
`created_date` timestamp NULL DEFAULT NULL,
PRIMARY KEY (`id_refresh_table`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
