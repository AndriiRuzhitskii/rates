CREATE TABLE IF NOT EXISTS `Account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `PASSWORD` varchar(200) NOT NULL,
    `USERNAME` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `Rate` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `EXCHANGEDATE` varchar(200) NOT NULL,
    `RATE` varchar(200) NOT NULL,
    `CURRENCY_ID` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `Currency` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `rate_id` bigint(20),
    `cc` varchar(200),
    `r030` smallint NOT NULL,
    `txt` varchar(200) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY(`rate_id`) REFERENCES Rate(`id`)
);
CREATE TABLE IF NOT EXISTS `Property` (
    `property_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `prop_name` varchar(200) NOT NULL,
    `prop_value` varchar(200) NOT NULL,
    PRIMARY KEY (`property_id`)
);