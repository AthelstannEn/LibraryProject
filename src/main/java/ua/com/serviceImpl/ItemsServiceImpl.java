package ua.com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.CRUD.dao.ItemsDao;
import ua.com.entity.Items;
import ua.com.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	@Autowired
	ItemsDao itemsDao;

	@Override
	public void save(Items item) {
		itemsDao.save(item);
	}

	@Override
	public List<Items> findAll() {
		return itemsDao.findAll();
	}

	@Override
	public Items findOne(int id) {
		return itemsDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		itemsDao.delete(id);
	}

	@Override
	public List<Items> findByUserName(String username) {
		return itemsDao.findByUserName(username);
	}

	@Override
	public List<Items> findByPurchaseStatus(int status) {
		return itemsDao.findByPurchaseStatus(status);
	}

	@Override
	public List<Items> findByUserNameAndPurchaseStatus(String username,
			int status) {
		return itemsDao.findByUserNameAndPurchaseStatus(username, status);
	}

}
