DROP DATABASE IF EXISTS jobportal;
CREATE DATABASE IF NOT EXISTS jobportal;
USE jobportal;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS inscriptions;
DROP TABLE IF EXISTS candidates;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS profiles;

CREATE TABLE candidates(
	id INT(11) unsigned NULL AUTO_INCREMENT,
	username VARCHAR(25) COLLATE latin1_spanish_ci NOT NULL UNIQUE,
	password VARCHAR(50) COLLATE latin1_spanish_ci NOT NULL,
	email VARCHAR(40) COLLATE latin1_spanish_ci NULL,
	last_login DATETIME NULL,
	created DATETIME NOT NULL,
	modified DATETIME NOT NULL,
	PRIMARY KEY (id)	
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci 
AUTO_INCREMENT=1;

CREATE TABLE profiles(
	id INT(11) unsigned NOT NULL AUTO_INCREMENT,
	candidate_id INT(11),
	name TEXT NULL,
	surname TEXT NULL,
	curriculum TEXT NULL,
	location TEXT NULL,
	category TEXT NULL,
	training TEXT NULL,
	experience TEXT NULL,
	languages TEXT NULL,
	created DATETIME NOT NULL,
	modified DATETIME NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id) REFERENCES candidate(id)
	ON DELETE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci 
AUTO_INCREMENT=1;

CREATE TABLE companies(
	id INT(11) unsigned NOT NULL AUTO_INCREMENT,
	cif VARCHAR(8) COLLATE latin1_spanish_ci NOT NULL UNIQUE,
	name TEXT NOT NULL,
	password VARCHAR(50) COLLATE latin1_spanish_ci NOT NULL,
	last_login DATETIME NULL,
	email VARCHAR(40) COLLATE latin1_spanish_ci NULL,
	created DATETIME NOT NULL,
	modified DATETIME NOT NULL,
	PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci 
AUTO_INCREMENT=1;

CREATE TABLE jobs(
	id INT(11) unsigned NOT NULL AUTO_INCREMENT,
	company_id INT(11) UNSIGNED NOT NULL,
	company_name TEXT NOT NULL,
	title TEXT NOT NULL,
	description TEXT NULL,
	location TEXT NOT  NULL,
	category TEXT NOT NULL,
	training TEXT NULL,
	experience TEXT NULL,
	languages TEXT NULL,
	created DATETIME NOT NULL,
	modified DATETIME NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (company_id) REFERENCES companies(id)
	ON DELETE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci 
AUTO_INCREMENT=1;

CREATE TABLE inscriptions(
	id INT(11) unsigned NOT NULL AUTO_INCREMENT,
	job_id INT(11) UNSIGNED NOT NULL,
	job_title TEXT NOT NULL,
	candidate_name TEXT NOT NULL,
	candidate_id INT(11) UNSIGNED NOT NULL,
	status VARCHAR(15) COLLATE latin1_spanish_ci DEFAULT "PENDIENTE",
	created DATETIME NOT NULL,
	modified DATETIME NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id) REFERENCES candidate(id) ON DELETE CASCADE,
	FOREIGN KEY (job_id) REFERENCES jobs(id)
	ON DELETE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci 
AUTO_INCREMENT=1;