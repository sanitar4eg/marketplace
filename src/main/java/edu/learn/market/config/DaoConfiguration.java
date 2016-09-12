package edu.learn.market.config;

import edu.learn.market.domain.Item;
import edu.learn.market.repository.GenericDao;
import edu.learn.market.repository.impl.GenericDaoHibernateImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class DaoConfiguration {

    @PersistenceContext
    private EntityManager manager;

    @Bean(name = "itemRepository")
    public GenericDao<Item, Long> itemRepository() {
        GenericDaoHibernateImpl<Item, Long> itemRepository = new GenericDaoHibernateImpl<Item, Long>(Item.class);
        itemRepository.setFactory(manager.getEntityManagerFactory().unwrap(SessionFactory.class));
        return itemRepository;
    }
}
