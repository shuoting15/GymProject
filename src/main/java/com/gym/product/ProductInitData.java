package com.gym.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;


import product.model.ProductBean;

public class ProductInitData {
	
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
    
	@Autowired
	static
	SessionFactory factory;
	public static void main(String[] args) {
		String line = "";
		int count = 0;
		
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();

		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory factory = metadata.getSessionFactoryBuilder().build();
		System.out.println(factory);
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			File file = new File("data/product.dat");
			// 2. 由"data/book.dat"逐筆讀入Book表格內的初始資料，然後依序新增
			// 到Book表格中
			try (
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);
			) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\,");
					ProductBean product = new ProductBean();

					product.setProductName(token[0]);
					product.setProductCategory(token[1]);
					product.setProductPrice(Double.parseDouble(token[2].trim()));
					product.setDiscount(Double.parseDouble(token[3].trim()));
					product.setProductInStock(Integer.parseInt(token[4].trim()));
					product.setProductDate(Date.valueOf(token[5].trim()));
					product.setProductDescription(token[6]);
					// 讀取圖片檔
					Blob blob = fileToBlob(token[7].trim());
					product.setCoverImage(blob);
					product.setFileName(extractFileName(token[7].trim()));
					
					session.save(product);
					System.out.println("新增一筆product紀錄成功");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("Book資料新增成功");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        tx.commit();
	} catch (Exception e) {
		System.err.println("新建表格時發生例外: " + e.getMessage());
		e.printStackTrace();
		tx.rollback();
	} 
    factory.close();
	}
	
	
	public static Blob fileToBlob(String imageFileName) throws IOException, SQLException {
		File imageFile = new File(imageFileName);
		long size = imageFile.length();
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		try (FileInputStream fis = new FileInputStream(imageFile);) {
			fis.read(b);
			sb = new SerialBlob(b);
		}
		return sb;
	}
	
	public static String extractFileName(String pathName) throws IOException, SQLException {
		return pathName.substring(pathName.lastIndexOf("/") + 1);
	}

}
