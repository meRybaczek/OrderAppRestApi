INSERT INTO client VALUES(NEXTVAL('client_seq'),'archi@archi.com', 'Archi1',5.0, '779-232-84-28');
INSERT INTO order_pdf VALUES(NEXTVAL('order_pdf_seq'), '2012-12-12',CURRVAL('client_seq'));
INSERT INTO order_file VALUES(NEXTVAL('order_file_seq'),3, 297.0, 594.0, 'C:\test','Rys1','true','true',CURRVAL('order_pdf_seq'));