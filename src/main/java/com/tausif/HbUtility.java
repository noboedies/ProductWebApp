package com.tausif;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.tausif.entity.Product;
import com.tausif.entity.User;

public class HbUtility {

	public final static SessionFactory sessionFactory;
	static {
		Configuration cfg = new Configuration();
		Properties p = new Properties();
		p.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		p.put(Environment.URL, "jdbc:mysql://localhost:3306/ProductWebAppdb?createDatabaseIfNotExist=true");
		p.put(Environment.USER, "root");
		p.put(Environment.PASS, "Your_password");
		p.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
		p.put(Environment.HBM2DDL_AUTO, "update");
		p.put(Environment.SHOW_SQL, "true");
		p.put(Environment.FORMAT_SQL, "true");
		
		cfg.setProperties(p);
		cfg.addAnnotatedClass(Product.class);
		cfg.addAnnotatedClass(User.class);
		
		sessionFactory = cfg.buildSessionFactory();
	}
}

