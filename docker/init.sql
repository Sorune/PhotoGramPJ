CREATE DATABASE IF NOT EXISTS photogram;
Create user IF NOT EXISTS 'gallery'@'%' IDENTIFIED by 'photo';
grant all privileges on photogram.* to 'gallery'@'%';
flush privileges;
