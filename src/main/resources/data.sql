INSERT INTO `Account` (`id`,`username`,`password`) VALUES (null,'user','password');

INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_load_from','2016-10-10','Single property. Date from which the currency rates will be loaded // 1998-01-01');

INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'url_nbu_rate','http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=%s&date=%s&json','Single property. URL address of NBU service, which returns rate by date and currency code');
INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'url_nbu_rates_ongoing_date','http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json','Single property. URL address of NBU service, which returns all rates by today');

INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_loaded','USD','Multiple property. Currency for loading in db by period');
INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_loaded','EUR','Multiple property. Currency for loading in db by period');
INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_loaded','RUB','Multiple property. Currency for loading in db by period');
INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_loaded','GBP','Multiple property. Currency for loading in db by period');
INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_loaded','XAU','Multiple property. Currency for loading in db by period');
INSERT INTO `Property` (`property_id`,`prop_name`,`prop_value`,`prop_explanation`) VALUES (null,'currency_loaded','XAG','Multiple property. Currency for loading in db by period');

