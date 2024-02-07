INSERT INTO GUARDIAN (guardian_id, ID, password, name, phone_number, organization, registration_code)
VALUES
  (1, 'guardian1', 'password1', '이름1', '01012345678', '기관1', 'ABC123'),
  (2, 'guardian2', 'password2', '이름2', '01087654321', '기관2', 'DEF456'),
  (3, 'guardian3', 'password3', '이름3', '01011112222', '기관3', 'GHI789');

INSERT INTO MEMBER (guardian_id, name, phone_number, gender, age, address, depression_index)
VALUES
  (1, '멤버1', '01011111111', 'MALE', 25, '주소1', 0.0),
  (2, '멤버2', '01022222222', 'FEMALE', 30, '주소2', 0.0),
  (3, '멤버3', '01033333333', 'MALE', 35, '주소3', 0.0);

INSERT INTO CONVERSATION (member_id, question, response, datetime)
VALUES
  (1, '질문1', '답변1', '2024-02-06'),
  (2, '질문2', '답변2', '2024-02-06'),
  (3, '질문3', '답변3', '2024-02-06');

INSERT INTO EMOTION (conversation_id, anger, sad, anxious, happy, neutral)
VALUES
  (1, 0.75, 0.25, 0.50, 0.80, 0.20),
  (2, 0.50, 0.75, 0.20, 0.60, 0.40),
  (3, 0.20, 0.30, 0.40, 0.90, 0.10);