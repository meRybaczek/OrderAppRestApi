INSERT INTO order_pdf VALUES(NEXTVAL('order_pdf_seq') ,'Arch1', '78454545', '2012-12-12',5.0);
INSERT INTO order_file VALUES(NEXTVAL('order_file_seq'),3, 297.0, 594.0, 'C:\test','Rys1','true','true',CURRVAL('order_pdf_seq'));