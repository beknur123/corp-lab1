CREATE TABLE ticket (
  id            uuid PRIMARY KEY,
  business_key  text NOT NULL UNIQUE,
  status        text NOT NULL,
  title         text NOT NULL,
  CONSTRAINT ticket_status_known    CHECK (status IN ('OPEN', 'ASSIGNED', 'CLOSED'))
);