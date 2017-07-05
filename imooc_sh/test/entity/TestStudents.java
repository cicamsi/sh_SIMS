package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TestStudents {
	@Test
	public void testSchemaExport(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建sessionFactory
		//SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建session对象
		//Session session = sessionFactory.getCurrentSession();
		//创建SchemaExport对象
//		MetadataImplementor metadataImplementor = (MetadataImplementor)new MetadataSources(serviceRegistry).buildMetadata();
//		SchemaExport export =new SchemaExport(serviceRegistry, metadataImplementor); 
		
//		SchemaExport export =new SchemaExport(config); 
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

		MetadataImplementor metadataImplementor = (MetadataImplementor)new MetadataSources(serviceRegistry).buildMetadata();

		SchemaExport export = new SchemaExport(serviceRegistry, metadataImplementor);
		
		export.create(true, true);
	}
	
	@Test
	public void testSaveStudents(){
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Students s1 = new Students("S0000001", "张三丰", "男", new Date(), "武当山");
		Students s2 = new Students("S0000002", "郭靖", "男", new Date(), "桃花岛");
		Students s3 = new Students("S0000003", "黄蓉", "女", new Date(), "桃花岛");
		
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		
		tx.commit();
		sessionFactory.close();
	}
	
}
