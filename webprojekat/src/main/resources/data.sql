INSERT INTO GLEDAOCI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('pera_peric', '1234', 'Pera', 'Peric', '06123456', 'pera.peric@gmail.com', '05.05.1999.', 'gledaoc', true);
INSERT INTO GLEDAOCI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('m_markovic', '4321', 'Marko', 'Markovic', '0612233','m.marko@gmail.com', '10.02.1999.', 'gledaoc', true);
INSERT INTO GLEDAOCI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('anicana', '5678', 'Ana', 'Anic', '062123456', 'anic_ana@gmail.com', '01.03.1999.', 'gledaoc', true);
INSERT INTO GLEDAOCI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('mirkam', '8765', 'Mirka', 'Miric', '062654321', 'm.miric@gmail.com','15.06.1999.', 'gledaoc', true);

INSERT INTO MENADZERI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('pavle.pavlic', '1357', 'Pavle', 'Pavlic', '063123456', 'p.pavlic@gmail.com', '15.12.1988.', 'menadzer', true);
INSERT INTO MENADZERI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('ancian', '7531', 'Anika', 'Anikic', '063654321', 'a.anikic@gmail.com', '17.02.1991.', 'menadzer', true);
INSERT INTO MENADZERI (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('milemikic', '2468', 'Mile', 'Mikic', '064123456', 'mile.m@gmail.com', '23.10.1989.', 'menadzer', true);

INSERT INTO ADMINISTRATOR (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('milos1', '8642', 'Milos', 'Milosevic', '064654321', 'mmilos@gmail.com', '30.09.1987.', 'admin', true);
INSERT INTO ADMINISTRATOR (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('milicamilic', '3579', 'Milica', 'Milic', '065123456', 'milicmilica@gmail.com', '03.01.1990.', 'admin', true);
INSERT INTO ADMINISTRATOR (korisnicko, lozinka, ime, prezime, telefon, mail, datum, uloga, aktivan) VALUES ('stefanovic', '9753', 'Stefan', 'Stefanovic', '065654321', 'sstef@gmail.com', '26.09.1989.', 'admin', true);

INSERT INTO BIOSKOP (naziv, adresa, telefon, mail, menadzer_id) VALUES ('Arena Cineplex', 'Bul. Mihajla Pupina 3', 521942, 'office@arenacineplex.com', 2);
INSERT INTO BIOSKOP (naziv, adresa, telefon, mail, menadzer_id) VALUES ('CINEPLEXX Promenada', 'Bul. Oslobodjenja 119',383999, 'cineplexx.promenada@gmail.com',3);
INSERT INTO BIOSKOP (naziv, adresa, telefon, mail, menadzer_id) VALUES ('CineStar BIG', 'Sentandrejski put 11',433554, 'cinestar.b@gmail.com',1);

INSERT INTO FILM (naziv, opis, zanr, trajanje, ocena) VALUES ('Perdona si te llamo amor', 'Uspesan, atraktivan i inteligentan direktor marketinga pronasao je emocionalnu stabilnost u svom zivotu. Nakon sto ga devojka odbije njegov zivot se menja pojavom nove mlade dame.', 'Ljubavni/Drama', 125, 7.7);
INSERT INTO FILM (naziv, opis, zanr, trajanje, ocena) VALUES ('Taken 1', 'Penzionisani CIA agent putuje kroz Evropu i oslanja se na svoje stare vestine kako bi spasao svoju cerku koja je kidnapovana tokom putovanja u Pariz.', 'Akcioni/Triler', 93, 8.9);
INSERT INTO FILM (naziv, opis, zanr, trajanje, ocena) VALUES ('Focus', 'Will Smith je premazani prevarant odlazi u restoran gde srece neiskusnu prevarantkinju Margot Robbie koja ga zavodi.', 'Kriminalisticka komedija', 105, 8.5);
INSERT INTO FILM (naziv, opis, zanr, trajanje, ocena) VALUES ('Crazy Rich Asians ', 'Ova savremena romanticna komedija, zasnovana na globalnom bestseleru, prati Njujorcanku Rachel Chu u Singapur kako bi upoznala porodicu svog decka.', 'Ljubavni/Komedija', 100, 8.2);

INSERT INTO OCENE (film_id, ocena) VALUES (1, 8.8);
INSERT INTO OCENE (film_id, ocena) VALUES (2, 9.2);
INSERT INTO OCENE (film_id, ocena) VALUES (3, 8.2);
INSERT INTO OCENE (film_id, ocena) VALUES (4, 7.8);

INSERT INTO ODGLEDANI_FILMOVI (id_gledaoca, odgledan_id) VALUES (1, 2);
INSERT INTO ODGLEDANI_FILMOVI (id_gledaoca, odgledan_id) VALUES (2, 3);
INSERT INTO ODGLEDANI_FILMOVI (id_gledaoca, odgledan_id) VALUES (3, 1);
INSERT INTO ODGLEDANI_FILMOVI (id_gledaoca, odgledan_id) VALUES (4, 2);

INSERT INTO OCENJENI_FILMOVI (id_gledaoca, ocenjeni_id) VALUES (1, 2);
INSERT INTO OCENJENI_FILMOVI (id_gledaoca, ocenjeni_id) VALUES (2, 3);
INSERT INTO OCENJENI_FILMOVI (id_gledaoca, ocenjeni_id) VALUES (3, 1);

INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id) VALUES (100, 'A1', 1);
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id) VALUES (80, 'A2', 1);
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id) VALUES (50, 'B1', 2);
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id) VALUES (30, 'B2', 3);

INSERT INTO TERMINI (film_id, datum, vreme, cena, rezervisano, sale_id) VALUES (1,'2020-07-07', '19:00', 150, 50, 2);
INSERT INTO TERMINI (film_id, datum, vreme, cena, rezervisano, sale_id) VALUES (2,'2020-07-07', '21:00', 150, 20, 2);
INSERT INTO TERMINI (film_id, datum, vreme, cena, rezervisano, sale_id) VALUES (3,'2020-07-07', '23:00', 150, 90, 1);

INSERT INTO PROJEKCIJE (bioskop_id, termini_id) VALUES (1, 1);
INSERT INTO PROJEKCIJE (bioskop_id, termini_id) VALUES (2, 2);
INSERT INTO PROJEKCIJE (bioskop_id, termini_id) VALUES (3, 3);

INSERT INTO REZERVISANI_FILMOVI (id_gledaoca, rezervisani_id) VALUES (1,2);
INSERT INTO REZERVISANI_FILMOVI (id_gledaoca, rezervisani_id) VALUES (2,3);
INSERT INTO REZERVISANI_FILMOVI (id_gledaoca, rezervisani_id) VALUES (3,1);