CREATE TABLE cra(
	mois date NOT NULL,
	idConsultant number NOT NULL,
	idMission number NOT NULL,
	idStatus number NOT NULL,
	constraint pk_cra primary key (mois,idConsultant,idMission)
);