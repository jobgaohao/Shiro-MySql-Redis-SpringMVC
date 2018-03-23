package com.sojson.blog.spider;

import java.util.List;

public class TestSpider {

	public static void main(String[] args) {
		ExtractService extractService=new ExtractService();
		Rule rule=new Rule(
				"http://www.cnblogs.com",
				new String[]{},new String[]{},
				"div#post_list div.post_item", 
				Rule.SELECTION, 
				Rule.GET);
		List<LinkTypeData> extracts=extractService.extractCnblog(rule);
		for(LinkTypeData linkTypeData:extracts){
			System.out.println(linkTypeData.toString());
		}
	}	
}
