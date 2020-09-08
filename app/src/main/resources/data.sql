INSERT INTO post (id, text, username) VALUES
  (1, 'lorem ipsum', 'Gupta'),
  (2, 'blablabla', 'hanswurst');

INSERT INTO comment (id, post_Id, text, username) VALUES
  (1, 1, 'Geiler Post', 'Gupta'),
  (2, 1, 'Das is doch nur blabla', 'hanswurst');