package com.apress.springrecipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

	private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void purchase(String isbn, String username) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ? ", Integer.class, isbn);
				getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ? ", isbn);
				getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ? ", price, username);
			}
		});
		
//		TransactionDefinition def = new DefaultTransactionDefinition();
//		TransactionStatus status = transactionManager.getTransaction(def);
//
//		try {
//			int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ? ", Integer.class, isbn);
//			getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ? ", isbn);
//			getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ? ", price, username);
//			transactionManager.commit(status);
//		} catch (DataAccessException e) {
//			transactionManager.rollback(status);
//			throw e;
//		}
	}

	@Override
	public void increaseStock(String isbn, int stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int checkStock(String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}

}
