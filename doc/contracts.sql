INSERT INTO `dashboard`.`SLMContract`
(
`name`,
`description`,
`tech_lead`,
`hours_budget`,
`project_id`,
`account_manager`)
VALUES
("SES 2.0 SLA",
"SES 2.0 SLA",
"Raymond de Wit",
"36",
3007,
"Bram Lokers");


INSERT INTO `dashboard`.`SLMContract`
(
`name`,
`description`,
`tech_lead`,
`hours_budget`,
`project_id`,
`account_manager`)
VALUES
("Pioneer CM SLA",
"Pioneer CM SLA",
"David Bouwman",
"88",
1315,
"Björn Bruggink");

INSERT INTO `dashboard`.`SLMContract`
(
`name`,
`description`,
`tech_lead`,
`hours_budget`,
`project_id`,
`account_manager`)
VALUES
("Click & Claim",
"Click & Claim SLA",
"Emiel van den Herberg",
"106",
2725,
"Bram Lokers");


UPDATE  `dashboard`.`SLMContract` SET 
(`name`,
`description`,
`tech_lead`,
`hours_budget`,
`project_id`,
`account_manager`)
	VALUES
("Click & Claim",
"Click & Claim SLA",
"Emiel van den Herberg",
"106",
2725,
"Bram Lokers")
WHERE id = 1;

INSERT INTO `dashboard`.`SLMContract`
(
`name`,
`description`,
`tech_lead`,
`hours_budget`,
`project_id`,
`account_manager`)
VALUES
("APX Group CM SLA",
"APX Group CM SLA",
"Maarten Baijs",
"12",
2848,
"Bjorn Bruggink");
