package com.mongodb.text;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class DataBase {
	public static void main(String[] args)
		throws UnknownHostException, MongoException {
		//1.建立一个Mongo的数据库连接对象
		Mongo mg = new Mongo("127.0.0.1:27017");
        //查询所有的Database
        for (String name : mg.getDatabaseNames()) {
            System.out.println("dbName: " + name);
        }
        //2.创建相关数据库的连接
        DB db = mg.getDB("tristan");
        //查询数据库所有的集合
        for (String name : db.getCollectionNames()) {
            System.out.println("collectionName: " + name);
        }

        DBCollection users = db.getCollection("persons");
        //查询所有的数据
        DBCursor cur = users.find();
        while (cur.hasNext()) {
        	DBObject object = cur.next();
        	System.out.println(object.get("name"));
        }
        System.out.println(cur.count());
        System.out.println(cur.getCursorId());
        System.out.println(JSON.serialize(cur));
    }
}
