CREATE TABLE consultant_role (
  id_consultant INTEGER NOT NULL,
  id_role    INTEGER NOT NULL,
  CONSTRAINT consultant_role_PK PRIMARY KEY (id_consultant, id_role),
  CONSTRAINT consultant_role_consultant_FK FOREIGN KEY (id_consultant) REFERENCES consultant (id_consultant),
  CONSTRAINT consultant_role_role_FK FOREIGN KEY (id_role) REFERENCES role (id_role)
);
